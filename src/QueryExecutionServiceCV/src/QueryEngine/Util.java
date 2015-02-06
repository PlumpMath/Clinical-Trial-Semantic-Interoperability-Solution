package QueryEngine;

import java.io.StringWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import coredataset.ExpandQuery;
import coredataset.ExpandQueryResponse;

public class Util {

	static Logger log = SemanticInteroperabilityLayer.log;

	String ConvertToJson(ResultSet rs) throws SQLException, JSONException {

		JSONArray json = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		int numColumns = rsmd.getColumnCount();

		while(rs.next()) {

			JSONObject obj = new JSONObject();

			for (int i=1; i<numColumns+1; i++) {

				String column_name = rsmd.getColumnLabel(i);
				if (!obj.isNull(column_name)) {
					column_name += "_" + rsmd.getTableName(i);
				}

				if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
					obj.put(column_name, rs.getArray(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
					obj.put(column_name, rs.getInt(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
					obj.put(column_name, rs.getBoolean(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
					obj.put(column_name, rs.getBlob(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
					obj.put(column_name, rs.getDouble(i)); 
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
					obj.put(column_name, rs.getFloat(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
					obj.put(column_name, rs.getInt(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
					obj.put(column_name, rs.getNString(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
					obj.put(column_name, rs.getString(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
					obj.put(column_name, rs.getInt(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
					obj.put(column_name, rs.getInt(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
					obj.put(column_name, rs.getDate(i));
				}
				else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
					obj.put(column_name, rs.getTimestamp(i));   
				}
				else{
					obj.put(column_name, rs.getObject(i));
				}
			}

			json.put(obj);
		}

		return json.toString(2);
	}

	String ConvertToXML(ResultSet rs) {
		StringWriter sw = new StringWriter();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder        = factory.newDocumentBuilder();
			Document doc                   = builder.newDocument();

			Element results = doc.createElement("Results");
			doc.appendChild(results);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount           = rsmd.getColumnCount();

			Element header = doc.createElement("Header");
			results.appendChild(header);	

			for (int i = 1; i <= colCount; i++) {
				Element column = doc.createElement("column");
				header.appendChild(column);
				Attr attr = doc.createAttribute("name");
				attr.setValue(rsmd.getColumnLabel(i));
				column.setAttributeNode(attr);
				attr = doc.createAttribute("type");
				attr.setValue(rsmd.getColumnTypeName(i));
				column.setAttributeNode(attr);
			}


			while (rs.next()) {
				Element row = doc.createElement("Row");
				results.appendChild(row);

				for (int i = 1; i <= colCount; i++) {
					String columnName = rsmd.getColumnLabel(i);
					Object value      = rs.getObject(i);
					if (value != null) {
						Element node      = doc.createElement(columnName);
						node.appendChild(doc.createTextNode(value.toString()));
						row.appendChild(node);
					}
				}
			}

			DOMSource domSource = new DOMSource(doc);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return sw.toString();
	}

	String SerializeResult(ResultSet rs, int opt) {
		String result = "";
		if (opt == 0) 
			result = ConvertToXML(rs);
		else if (opt == 1) {
			try {
				result = ConvertToJson(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	String QueryResult(String query) {

		String result = "";
		String cquery = query;
		String query_final = query;
		// Query expansion is needed
		try {
			if (query.indexOf("isAnySubclassOf")>0) {
				while(cquery.contains("isAnySubclassOf")){
					// Find the concept (only the number, without SCT_)		
					String aux = cquery.substring(cquery.indexOf("isAnySubclassOf"));
					String concept = aux.substring(aux.indexOf("isAnySubclassOf")+16, aux.indexOf(")"));
					//The "isAnySubclassOf" is removed from the string in order to find the next one
					cquery=cquery.substring(cquery.indexOf("isAnySubclassOf")+16);
					coredataset.CoreDatasetServiceStub client = new coredataset.CoreDatasetServiceStub();
					ExpandQuery request = new ExpandQuery();
					request.setConcept(concept);
					ExpandQueryResponse res = client.expandQuery(request);

					String[] response ;
					if (res.get_return()==null) {
						response = new String[1];
						response[0]=concept;
					} else {
						response = res.get_return();
					}

					//Add expansion to the query
					String expansion = "";
					for (String string : response) {
						if (!concept.equals(string))
							expansion += ", '" + string + "'";
					}
					String conceptOrig = concept;
					concept = "'" + concept + "'";
					concept += expansion;
					query_final = query_final.replace("isAnySubclassOf(" + conceptOrig + ")", concept);
				}
			}

			//LOG
			System.out.println(query_final);
			log.info("[EXPANDED QUERY]" + "\n---------------------------------------" + "\n" + query_final + "\n---------------------------------------");


			//Connect to database
			String server_db = SemanticInteroperabilityLayer.server;
			String name_db = SemanticInteroperabilityLayer.database;
			String user_db = SemanticInteroperabilityLayer.user;
			String pass_db = SemanticInteroperabilityLayer.pass;
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName(server_db);
			dataSource.setDatabaseName(name_db);
			dataSource.setUser(user_db);
			dataSource.setPassword(pass_db);
			Connection con = dataSource.getConnection();

			ResultSet rs = con.prepareStatement(query_final).executeQuery();

			//Convert result to XML or JSON
			result = SerializeResult(rs, 0);

			rs.next();
			con.close();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}  catch (SQLException e1) {
			e1.printStackTrace();
		}

		return result;
	}


}
