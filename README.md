spring-data-dynamodb-demo
=========================

Demo of AmazonDB Spring-Data repositories using Spring-Data-Rest
----------------------------------------------------------------

To get started

1. Create and populate Amazon DynamoDB tables as described in Amazon's example tutorials:

http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/JavaQueryScanORMModelExample.html

2. Populate the src/main/resources/environment.properties file with your DynamoDB endpoint, and AWS credentials.

3. mvn jetty:run

4. Access http://localhost:8080 and explore a simple REST CRUD API for the Reply,Thread and Forum objects.

Demos use of spring-data-rest to export DynamoDB spring-data repositories using the spring-data-dynamodb project

(https://github.com/michaellavelle/spring-data-dynamodb)

