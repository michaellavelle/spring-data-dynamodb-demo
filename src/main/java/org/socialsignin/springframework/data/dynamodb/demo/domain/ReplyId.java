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

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

/**
 * Composite id for the Reply entity. For spring-data-dynamodb to be able to
 * identify which attribute is the hash key and which is the range key the
 * methods must be annotated with @DynamoDBHashKey or @DynamoDBRangeKey
 * 
 * 
 * @author Michael Lavelle
 */
public class ReplyId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ThreadId threadId;
	private String replyDateTime;

	@DynamoDBRangeKey
	public String getReplyDateTime() {
		return replyDateTime;
	}

	public void setReplyDateTime(String replyDateTime) {
		this.replyDateTime = replyDateTime;
	}

	@DynamoDBHashKey
	public ThreadId getThreadId() {
		return threadId;
	}

	public void setThreadId(ThreadId id) {
		this.threadId = id;
	}

}
