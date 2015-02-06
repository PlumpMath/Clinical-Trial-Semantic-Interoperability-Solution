package templates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import config.ServiceProperties;
import queryBuilder.QueryBuilderService;

public class TemplateLibrary {


	public static ArrayList<ArrayList<String>> makeTemplateStructure (String template_dir) {
		ArrayList<ArrayList<String>> template_structure = new ArrayList<ArrayList<String>>();

		//Templates folder
		File dir = new File(template_dir);
		String[] templates = dir.list();
		if (templates!=null){
			for (int i=0; i<templates.length; i++) {

				SAXBuilder builder = new SAXBuilder();
				File xmlFile = new File(template_dir+"/"+templates[i]);

				Document document;
				try {
					//Create document through xml file
					document = (Document) builder.build( xmlFile );
					//Root node
					Element rootNode = document.getRootElement();

					//Child rimClasses
					Element rim_classes = rootNode.getChild("rimClasses");
					List rim_rel = rim_classes.getChildren("rimRelationship");
					ArrayList<String> lista_individual = new ArrayList<String>();
					//rimClasses childs
					for ( int j = 0; j < rim_rel.size(); j++ ) {
						//Table 
						Element rim_rel_node = (Element)rim_rel.get(j);
						List lista_campos = rim_rel_node.getChildren();
						for (int z=0; z<lista_campos.size(); z++) {
							Element node = (Element)lista_campos.get(z);
							String aux = node.getText();
							lista_individual.add(aux);
						}
					}
					template_structure.add(lista_individual);
				} catch (JDOMException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}			 
			}
		}
		return template_structure;
	}

	private static ArrayList<ArrayList<String>> orderTemplateStructure (ArrayList<ArrayList<String>> structure) {
		ArrayList<ArrayList<String>> order_structure = new ArrayList<ArrayList<String>>();
		for (int i=0; i<structure.size(); i++) {
			ArrayList<String> aux = new ArrayList<String>();
			aux = (ArrayList<String>) structure.get(i).clone();
			order_structure.add(i, aux);
			Collections.sort(order_structure.get(i));
		}

		return order_structure;
	}

	private static ArrayList<String> orderBindings (String[] list) {

		// Order binding_list
		ArrayList<String> res = new ArrayList<String>();
		int count=1;
		for (int i=0; i<list.length; i++) {
			if (count==3) {
				count=1;
			} else {
				res.add(list[i]);
				count++;
			}
		}
		Collections.sort(res);

		return res;
	}

	private static String getFileName (ArrayList<ArrayList<String>> structure, ArrayList<ArrayList<String>> order_structure, ArrayList<String> list) {
		String template_dir = QueryBuilderService.template_dir;
		String file_name = template_dir+"/";
		int found = 0; 
		for (int i=0; i<order_structure.size(); i++) {
			if (list.equals(order_structure.get(i))) {
				found = 1;

				for (int j=0; j<structure.get(i).size(); j++) {
					if (j==0) {
						file_name=file_name+structure.get(i).get(j);
					} else {
						file_name=file_name+"_"+structure.get(i).get(j);
					}
				}
			} 
		}
		if (found==0) {	
			file_name="ERROR";
		}
		file_name=file_name+".xml";

		return file_name;
	}

	private static String fileToString(File file){
		StringBuffer buffer = new StringBuffer();
		String line;
		FileReader fReader;
		BufferedReader bReader;

		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			while ((line = bReader.readLine()) != null){
				buffer.append(line +"\n");
			}
			bReader.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}

	private static String replaceText (String text, String[] bindings_concepts) {

		String output=text;
		int bind_count = 0;
		String concepts = "";
		for (int i=0; i<bindings_concepts.length; i++) {
			if (bind_count==2) {
				if (output.contains("%_" + bindings_concepts[i-2].charAt(0) + bindings_concepts[i-1].charAt(0))) {
					concepts = "), isAnySubclassOf(" + bindings_concepts[i] + "%_" + bindings_concepts[i-2].charAt(0) + bindings_concepts[i-1].charAt(0) + ")";
					output=output.replace("%_" + bindings_concepts[i-2].charAt(0) + bindings_concepts[i-1].charAt(0) + ")", concepts);
				}
				else {
					concepts = bindings_concepts[i] + "%_" + bindings_concepts[i-2].charAt(0) + bindings_concepts[i-1].charAt(0);
					output=output.replace("%"+bindings_concepts[i-2]+"_"+bindings_concepts[i-1]+"%", concepts);
				}
				bind_count=0;
			} else {
				bind_count++;
			}
		}

		output = output.replaceAll("%_..", "");

		return output;
	}

	public static String addingInformationOnQuery (String original_template, String filtro) {

		String resulting_template = null;

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new StringReader(original_template));
			Element rootNode = doc.getRootElement();
			String query = original_template.substring(original_template.indexOf("<sqlQuery>"), original_template.indexOf("</sqlQuery>"));
			
			String optionalAttributes = null, optionalQueries = null, optionalFilters = null;
			
			//Optionals chidls
			Element optionals = rootNode.getChild("optionals");
			//Optional structures list
			List optional_list = optionals.getChildren("OptionalStructure");
			
			for ( int j = 0; j < optional_list.size(); j++ ) {
				Element optional_node = (Element)optional_list.get(j);
				//Add filter if needed
				if (optional_node.getAttributeValue("id").equals(filtro)) {
					//Obtain optionals parameters (OptionalHeaderAttributes, optionalTable, OptionalFilter)
					List optionalParameters = optional_node.getChildren();
					for (int z=0; z<optionalParameters.size(); z++) {
						Element node = (Element)optionalParameters.get(z);
						//Add optional attributes
						if (node.getAttributeValue("tag").equals("$$optionalAttributes$$")) {
							optionalAttributes = node.getValue();
						//Add optional table
						} else if (node.getAttributeValue("tag").equals("$$OptionalStructures$$")) {
							optionalQueries = node.getValue();
						}
						//Add optional filter
						else if (node.getAttributeValue("tag").equals("$$OptionalFilters$$")) {
							optionalFilters = node.getValue();
						}
					}
				}
			}
			String original_query = query;
			query = query.replace("$$optionalAttributes$$", optionalAttributes);
			query = query.replace("$$OptionalStructures$$", optionalQueries);
			query = query.replace("$$OptionalFilters$$", optionalFilters);
			query = query.replaceAll("(?m)^[ \t]*\r?\n", "");
			resulting_template = original_template.replace(original_query, query);
			
			return resulting_template;
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getNewTemplate (String[] bindings_concepts, ArrayList<ArrayList<String>> template_structure) {

		Logger log = QueryBuilderService.log;

		ArrayList<ArrayList<String>> order_structure = orderTemplateStructure (template_structure);

		String[] new_binding_concepts = new String [3];
		for (int i=0; i<3; i++) {
			new_binding_concepts[i] = bindings_concepts[i];
		}

		//LOG
		ArrayList<String> binding_order = orderBindings(new_binding_concepts);
		for (String name: new_binding_concepts) {
			log.info("[gT-binding] - " + name);
		}

		String file_name = getFileName(template_structure, order_structure, binding_order);

		log.info("[Resulting Template] - " + file_name + "\n------------------------------------------------------------------------------");

		ArrayList<String> filters = new ArrayList<String>();
		int i=0;
		for (String name: bindings_concepts) {
			if ((i%3==1) && !(name.equals("code"))) {
				filters.add(name); 
			}
			if ((i != 0) && (name.equals("Entity"))) {
				filters.add("entity");
			}
			i++;
		}

		if (file_name.contains("ERROR.xml")) {
			return "TEMPLATE NOT FOUND";
		} else {
			String output = fileToString(new File(file_name));
			String res = replaceText(output, bindings_concepts);
			int entityFilter = 0;
			for (String filtro : filters) {
				// only one entity filter for the moment 				
				if (filtro.equals("entity")&&(entityFilter==0)) {
					res = addingInformationOnQuery(res, filtro);
					entityFilter=1;
				} else if (!(filtro.equals("entity"))) {
					res = addingInformationOnQuery(res, filtro);
				} else {
					entityFilter=1;
				}
			}
			return res;
		}

	}


}
