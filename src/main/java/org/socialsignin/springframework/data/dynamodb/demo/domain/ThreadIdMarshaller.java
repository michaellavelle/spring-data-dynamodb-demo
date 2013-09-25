package org.socialsignin.springframework.data.dynamodb.demo.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;

public class ThreadIdMarshaller implements DynamoDBMarshaller<ThreadId> {

	@Override
	public String marshall(ThreadId getterReturnResult) {
		return getterReturnResult == null ?  null : getterReturnResult.getForumName() + "#" + (getterReturnResult.getSubject() == null ? "" : getterReturnResult.getSubject());
	}

	@Override
	public ThreadId unmarshall(Class<ThreadId> clazz, String obj) {
				
		if (obj == null) return null;
		String[] parts = obj.split("#");
		ThreadId nameAndSubject = new ThreadId();

		if (parts.length == 2)
		{
		nameAndSubject.setForumName(parts[0]);
		nameAndSubject.setSubject(parts[1]);
		}
		else
		{
			nameAndSubject.setForumName(obj);

		}
		return nameAndSubject;
	}

}
