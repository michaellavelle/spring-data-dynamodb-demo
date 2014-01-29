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

import java.net.URLDecoder;
import java.util.Date;

import org.socialsignin.springframework.data.dynamodb.demo.domain.Forum;
import org.socialsignin.springframework.data.dynamodb.demo.domain.Reply;
import org.socialsignin.springframework.data.dynamodb.demo.domain.ReplyId;
import org.socialsignin.springframework.data.dynamodb.demo.domain.ThreadId;
import org.socialsignin.springframework.data.dynamodb.demo.domain.ThreadIdMarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.EntityLinks;

/**
 * Custom RepositoryRestMvcConfiguration which is imported instead of the
 * regular RepositoryRestMvcConfiguration for the following purposes:
 * 
 * 1. To allow us to define how we wish composite ids to be converted to and
 * from urls 2. To specify that we would like to return a JSON body when
 * creating/updating entities 3. To configure a custom RepositoryLinkBuilder
 * which can render Amazon's textual ids to and from url-safe strings
 * 
 * 
 * @author Michael Lavelle
 */
@Configuration
public class DemoRestMvcConfiguration extends RepositoryRestMvcConfiguration {

	@Override
	protected void configureConversionService(
			ConfigurableConversionService conversionService) {
		conversionService.addConverter(stringToThreadIdConverter());
		conversionService.addConverter(stringToReplyIdConverter());
		super.configureConversionService(conversionService);
	}

	@Override
	public RepositoryRestConfiguration config() {
		return super.config().setReturnBodyOnCreate(true)
				.setReturnBodyOnUpdate(true).exposeIdsFor(Reply.class,Thread.class,Forum.class);
	}

	/**
	 * A special {@link org.springframework.hateoas.EntityLinks} implementation
	 * that takes repository and current configuration into account when
	 * generating links.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public EntityLinks entityLinks() {

		return new DemoRepositoryEntityLinks(repositories(),
				resourceMappings(), config(),pageableResolver());

	}

	public Converter<String, ReplyId> stringToReplyIdConverter() {
		return new Converter<String, ReplyId>() {

			@SuppressWarnings("deprecation")
			@Override
			public ReplyId convert(String source) {
				ReplyId id = new ReplyId();
				ThreadIdMarshaller threadIdMarshaller = new ThreadIdMarshaller();
				String[] parts = source.split("-");
				id.setThreadId(threadIdMarshaller.unmarshall(ThreadId.class,URLDecoder.decode(parts[1])));
				String replyDateTime = DemoRepositoryLinkBuilder.DATE_FORMAT
						.format(new Date(Long.parseLong(parts[0])));
				id.setReplyDateTime(replyDateTime);
				return id;
			}

		};

	}

	public Converter<String, ThreadId> stringToThreadIdConverter() {
		return new Converter<String, ThreadId>() {


			@SuppressWarnings("deprecation")
			@Override
			public ThreadId convert(String source) {
				ThreadId id = new ThreadId();
				String[] parts = source.split("_");
				id.setForumName(URLDecoder.decode(parts[0]));
				id.setSubject(URLDecoder.decode(parts[1]));
				return id;
			}

		};

	}

}
