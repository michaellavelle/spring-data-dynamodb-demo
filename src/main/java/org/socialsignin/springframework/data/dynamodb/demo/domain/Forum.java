/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.socialsignin.springframework.data.dynamodb.demo.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * This is a clone of the Forum class in the AmazonDB examples at
 * http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/
 * JavaQueryScanORMModelExample.html
 * 
 * @author Michael Lavelle
 */
@DynamoDBTable(tableName = "Forum")
public class Forum {
	private String name;
	private String category;
	private int threads;

	@DynamoDBHashKey(attributeName = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName = "Category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@DynamoDBAttribute(attributeName = "Threads")
	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}
}
