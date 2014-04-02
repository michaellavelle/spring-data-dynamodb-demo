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

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is a clone of the Reply class in the AmazonDB examples at
 * http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/
 * JavaQueryScanORMModelExample.html
 * 
 * The Reply table has a composite primary key ( hash and range). For
 * spring-data-dynamodb to support datatypes with composite keys, the composite
 * keys must be encapuslated in a separate class, resulting in a single primary
 * key attribute annotated with @Id.
 * 
 * This class has therefore been modified slightly - the id and replyDateTime
 * attributes were properties of this class, but they are now part of the
 * ReplyId composite id class which is referenced. Setters and getters must be
 * still provided for the Amazon databinding to function - these are now
 * delegator methods for the attributes of the referenced composite id.
 * 
 * @author Michael Lavelle
 */
@DynamoDBTable(tableName = "Reply")
public class Reply {

	@Id
	private ReplyId replyId;
	@NotNull
	private String message;
	private String postedBy;

	@DynamoDBHashKey(attributeName = "Id")
	@DynamoDBMarshalling(marshallerClass=ThreadIdMarshaller.class)
	public ThreadId getThreadId() {
		return replyId != null ? replyId.getThreadId() : null;
	}

	public void setThreadId(ThreadId id) {

		if (replyId == null) {
			replyId = new ReplyId();
		}
		replyId.setThreadId(id);

	}

	@DynamoDBRangeKey(attributeName = "ReplyDateTime")
	public String getReplyDateTime() {
		return replyId != null ? replyId.getReplyDateTime() : null;
	}

	public void setReplyDateTime(String replyDateTime) {

		if (replyId == null) {
			replyId = new ReplyId();
		}
		replyId.setReplyDateTime(replyDateTime);

	}

	@DynamoDBAttribute(attributeName = "Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@DynamoDBAttribute(attributeName = "PostedBy")
	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
}