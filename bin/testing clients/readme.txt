Three different jar files are provided to test the services.

- clientDataPush.jar

	Usage java -jar clientDataPush.jar HL7 MESSAGE
	
	Used to test the Data Push Service. It receives a HL7 message in a xml file and invokes the Data Push Service to store the given message into the CDM.
	
- clientQueryNormalization.jar

	Usage java -jar clientQueryNormalization.jar CONCEPT
	
	Used to test the Query Normalization Service. It recieves a Core Data set concept and invokes the Query Normalition Service to provide the query templates that can be used to query the given concept.
	
- clientQueryExecution.jar

	Usage java -jar clientQueryExecution.jar QUERY
	
	Used to test the QueryExecutionService. It receives a query in a sql file and invokes the Query Execution Service to provide the result set of the given query.