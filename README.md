spring-data-dynamodb-demo
=========================

Demo of AmazonDB Spring-Data repositories using Spring-Data-Rest
----------------------------------------------------------------

To get started, create and populate Amazon DynamoDB tables as described in Amazon's example tutorials:

http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/JavaQueryScanORMModelExample.html

Populate the src/main/resources/environment.properties file with your DynamoDB endpoint, and AWS credentials.

mvn jetty:run

Access http://localhost:8080 and explore a simple REST crud API for the Reply,Thread and Forum objects.

Uses spring-data-rest to export DynamoDB spring-data repositories implemented in the spring-data-dynamodb project

