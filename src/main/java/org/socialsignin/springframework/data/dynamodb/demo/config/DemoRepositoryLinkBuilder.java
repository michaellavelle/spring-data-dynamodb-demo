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
package org.socialsignin.springframework.data.dynamodb.demo.config;

import java.net.URI;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.socialsignin.springframework.data.dynamodb.demo.domain.ReplyId;
import org.socialsignin.springframework.data.dynamodb.demo.domain.ThreadId;
import org.socialsignin.springframework.data.dynamodb.demo.domain.ThreadIdMarshaller;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.support.RepositoryLinkBuilder;

/**
 * A custom RepositoryLinkBuilder which can render Amazon's textual ids to and
 * from url-safe strings
 * 
 * @author Michael Lavelle
 * 
 */
public class DemoRepositoryLinkBuilder extends RepositoryLinkBuilder {

	public static DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public DemoRepositoryLinkBuilder(ResourceMetadata metadata, URI baseUri) {
		super(metadata, baseUri);
	}

	@SuppressWarnings("deprecation")
	@Override
	public RepositoryLinkBuilder slash(Object object) {

		if (object instanceof ReplyId) {
			ReplyId replyId = (ReplyId) object;
			try {
				ThreadIdMarshaller threadIdMarshaller = new ThreadIdMarshaller();
				return slash(DATE_FORMAT.parse(replyId.getReplyDateTime())
						.getTime()
						+ "-"
						+ URLEncoder.encode(threadIdMarshaller.marshall(replyId.getThreadId())));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (object instanceof ThreadId) {
			ThreadId threadId = (ThreadId) object;
			return slash(URLEncoder.encode(threadId.getForumName()) + "_"
					+ URLEncoder.encode(threadId.getSubject()));

		}

		return super.slash(object);
	}

}
