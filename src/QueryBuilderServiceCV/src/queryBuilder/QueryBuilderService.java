package queryBuilder;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import templates.TemplateLibrary;
import config.ServiceProperties;
import coredataset.CD2CDMNEW;
import coredataset.CD2CDMNEWResponse;
import coredataset.CoreDatasetServiceStub;

public class QueryBuilderService {

	public static String serverDirectory;
	private static String serverIndex = "WEB-INF";

	public static String configFile;

	static String LOG;
	static String log4jConfig;
	public static Logger log;
	static CoreDatasetServiceStub client;

	static ArrayList<ArrayList<String>> template_structure;
	static public String template_dir;

	static {
		init_service();
	}

	private static void init_service () {

		//Get execution directory
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		int index = classLoader.getResource("").getFile().indexOf(serverIndex);
		serverDirectory = classLoader.getResource("").getFile().substring(0, index);

		configFile = serverDirectory + "config" + File.separator + "properties.config";

		LOG = ServiceProperties.getLOGmessage(configFile);
		Date date = new Date();
		System.out.println(date + " - [" + LOG + "].Initializing service");

		//Log
		log4jConfig = serverDirectory + ServiceProperties.getLogConfig(configFile);
		PropertyConfigurator.configure(log4jConfig);  
		log = Logger.getLogger(QueryBuilderService.class); 

		//CoreDataset Service client
		try {
			client = new CoreDatasetServiceStub();
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		template_dir = serverDirectory + ServiceProperties.getTemplateDir(configFile);
		template_structure = TemplateLibrary.makeTemplateStructure(template_dir);
	}


	public static String getContextualizedTemplate (String classes, String[] list) {
		String originalConcept = list[1];

		// LOG
		log.info("[Get Contextualized Template]"); 
		log.info("[List size] - " + list.length);
		log.info("[Class] - " + classes);

		//Person template
		if ((classes.equals("Entity"))&&(list[0].equals("code"))&&(list[1].equals("337915000"))) {
			classes="Person";
			//Enrollment template
		} else if ((list[0].equals("code"))&&(list[1].equals("185923000"))) {
			classes="Enrollment";
		}

		ArrayList<Triples> list_copy = new ArrayList<Triples>();
		list_copy = config.Util.convertToArray(classes, list);

		//Check whether there is target, method, and/or entity in the given list
		boolean isTargetSite = false; 
		boolean isMethod = false;
		boolean isEntity = false;
		for (Triples triples : list_copy) {
			if (triples.getAttribute().equals("targetSiteCode")) {
				isTargetSite=true;
			} 
			else if (triples.getAttribute().equals("methodCode")) {
				isMethod = true;
			}
			else if (triples.getAttribute().equals("entity")) {
				isEntity = true;
			}
		}

		// LOG
		for (String name: list) {
			log.info("[List:"+list.length+"--"+list_copy.size()+"] - " + name);
		}

		for (int i=0; i<list_copy.size(); i++) {
			//Focus Concept
			if (i==0) {	
				try {
					//Get normal form
					CD2CDMNEW concept = new CD2CDMNEW();
					concept.setTerm(list_copy.get(i).getId());
					CD2CDMNEWResponse res = client.cD2CDMNEW(concept);
					JSONParser parser = new JSONParser();
					String json = res.get_return().getJSON();
					Object obj;
					obj = parser.parse(json);
					JSONObject jsonObject = (JSONObject) obj;
					JSONObject principal = (JSONObject) jsonObject.get("code");
					String codigo = (String) principal.get("code");
					Triples nodeNormalized = new Triples(list_copy.get(i).getClasses(), list_copy.get(i).getAttribute(), codigo);
					list_copy.set(i, nodeNormalized);

					//Add targetSite, method, entity if needed and it's possible
					if (!isTargetSite) {
						JSONArray relaciones = (JSONArray) jsonObject.get("relationshipPairs");
						@SuppressWarnings("unchecked")
						Iterator<JSONObject> iterator_r = relaciones.iterator();
						while (iterator_r.hasNext()) {
							JSONObject relacion = iterator_r.next();
							JSONObject value = (JSONObject) relacion.get("concept");
							String relation = value.toJSONString();
							if (relation.contains("targetSiteCode")) {
								Triples nodeTargetSite = new Triples((String) value.get("table"), (String) value.get("attribute"), (String) value.get("code"));
								list_copy.add(nodeTargetSite);
							}
						}
					}
					if (!isMethod) {
						JSONArray relaciones = (JSONArray) jsonObject.get("relationshipPairs");
						@SuppressWarnings("unchecked")
						Iterator<JSONObject> iterator_r = relaciones.iterator();
						while (iterator_r.hasNext()) {
							JSONObject relacion = iterator_r.next();
							JSONObject value = (JSONObject) relacion.get("concept");
							String relation = value.toJSONString();
							if (relation.contains("methodCode")) {
								Triples nodeMethod = new Triples((String) value.get("table"), (String) value.get("attribute"), (String) value.get("code"));
								list_copy.add(nodeMethod);
							}
						}
					}

					if (!isEntity) {
						JSONArray relaciones = (JSONArray) jsonObject.get("entities");
						@SuppressWarnings("unchecked")
						Iterator<JSONObject> iterator_r = relaciones.iterator();
						while (iterator_r.hasNext()) {
							JSONObject relacion = iterator_r.next();
							JSONObject value = (JSONObject) relacion.get("concept");
							String relation = value.toJSONString();
							if (relation.contains("code")) {
								Triples nodeEntity = new Triples("Entity", "code", (String) value.get("code"));
								list_copy.add(nodeEntity);
							}
						}
					}

				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			String[] binding_template = config.Util.convertFromArray(list_copy);
			//Get template
			String template = TemplateLibrary.getNewTemplate(binding_template, template_structure);
			template = template.replace("%concept%", originalConcept);
			String normal_form= new String();
			int j=0;
			for (String string : binding_template) {
				switch (j) {
				case 0:
					if (normal_form.isEmpty()) {
						normal_form=string;
						j++;
						break;
					} else {
						normal_form=normal_form + " | " + string;
						j++;
						break;
					}
				case 1:
					normal_form=normal_form + "_" + string;
					j++;
					break;
				case 2:
					normal_form=normal_form + " : " + string;
					j=0;
					break;
				default:
					break;
				}

			}
			int z =0;
			for (String searchingEntity : list) {
				if (searchingEntity.equals("entity")) {
					template = template.replace("%Entity_code%", list[z+1]);
				}
				z=z+1;
			}

			template = template.replace("%normalform%", normal_form);
			//System.out.println();
			return template;
		}

		return null;
	}

	public static Collection<String> getUncontextualizedTemplate (String concept) {

		//LOG
		log.info("[getUncontextualizedTemplate]");
		log.info("[CONCEPT GIVEN] - " + concept);

		ArrayList<String> resultList = new ArrayList<String>(); 
		String query = new String();

		try {
			//Get concept normal form
			CD2CDMNEW cd2cdm = new CD2CDMNEW();
			cd2cdm.setTerm(concept);
			CD2CDMNEWResponse res = client.cD2CDMNEW(cd2cdm);
			JSONParser parser = new JSONParser();
			String json = res.get_return().getJSON();
			Object obj;
			obj = parser.parse(json);
			JSONObject jsonObject = (JSONObject) obj;
			ArrayList<String> lista = new ArrayList<String>();
			JSONObject principal = (JSONObject) jsonObject.get("code");
			String table = (String) principal.get("table");
			lista.add(table);
			String attribute = (String) principal.get("attribute");
			lista.add(attribute);
			String code = (String) principal.get("code");
			lista.add(code);

			//New relationships from normalization
			JSONArray relaciones = (JSONArray) jsonObject.get("relationshipPairs");
			Iterator<JSONObject> iterator_r = relaciones.iterator();
			while (iterator_r.hasNext()) {
				JSONObject relacion = iterator_r.next();
				JSONObject value = (JSONObject) relacion.get("concept");
				String relation = value.toJSONString();
				if (!(relation.contains("Unkown"))) {
					lista.add((String) value.get("table"));
					lista.add((String) value.get("attribute"));
					lista.add((String) value.get("code"));
				}
			}

			//New entities from normalization
			JSONArray entidades = (JSONArray) jsonObject.get("entities");
			Iterator<JSONObject> iterator_e = entidades.iterator();
			while (iterator_e.hasNext()) {
				JSONObject entidad = iterator_e.next();
				JSONObject valueE = (JSONObject) entidad.get("concept");
				lista.add("Entity");
				lista.add("code");
				lista.add((String) valueE.get("code"));
			}

			String[] binding_list = new String[lista.size()];
			int pos_unk=binding_list.length; 
			for (int i=0; i<binding_list.length; i++) {
				binding_list[i]=lista.get(i);
			}

			//Person template
			if (concept.equals("337915000")) {
				binding_list[0]="Person";
			}

			//Get principal template
			query = TemplateLibrary.getNewTemplate(binding_list, template_structure);
			query = query.replace("%concept%", concept);
			String normal_form= new String();
			int i=0;
			for (String string : binding_list) {
				switch (i) {
				case 0:
					if (normal_form.isEmpty()) {
						normal_form=string;
						i++;
						break;
					} else {
						normal_form=normal_form + " | " + string;
						i++;
						break;
					}
				case 1:
					normal_form=normal_form + "_" + string;
					i++;
					break;
				case 2:
					normal_form=normal_form + " : " + string;
					i=0;
					break;
				default:
					break;
				}

			}
			query = query.replace("%normalform%", normal_form);
			resultList.add(query);

			//Get alternatives templates
			JSONArray msg2 = (JSONArray) jsonObject.get("alternatives");
			Iterator<JSONObject> iterator2 = msg2.iterator();
			while (iterator2.hasNext()) {
				JSONObject alternativa = iterator2.next();
				String[] binding_alternativa = new String[3];
				binding_alternativa[0]=(String) alternativa.get("table");
				binding_alternativa[1]=(String) alternativa.get("attribute");
				binding_alternativa[2]=(String) alternativa.get("code");
				query = TemplateLibrary.getNewTemplate(binding_alternativa, template_structure);
				query = query.replace("%concept%", concept);
				normal_form= new String();
				i=0;
				for (String string : binding_alternativa) {
					switch (i) {
					case 0:
						if (normal_form.isEmpty()) {
							normal_form=string;
							i++;
							break;
						} else {
							normal_form=normal_form + " | " + string;
							i++;
							break;
						}
					case 1:
						normal_form=normal_form + "_" + string;
						i++;
						break;
					case 2:
						normal_form=normal_form + " : " + string;
						i=0;
						break;
					default:
						break;
					}

				}
				query = query.replace("%normalform%", normal_form);
				resultList.add(query);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// LOG
		log.info("[Result getTemplateAuto] - " + query + "\n------------------------------------------------------------------------------");

		return resultList;
	}

}
