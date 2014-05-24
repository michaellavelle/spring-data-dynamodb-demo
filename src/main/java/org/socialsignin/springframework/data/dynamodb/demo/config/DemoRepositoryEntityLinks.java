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

import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.hateoas.LinkBuilder;

/**
 * Allows us to configure a custom To RepositoryLinkBuilder which can render
 * Amazon's textual ids to and from url-safe strings
 * 
 * @author Michael Lavelle
 * 
 */
public class DemoRepositoryEntityLinks extends RepositoryEntityLinks {

	private ResourceMappings resourceMappings;
	private RepositoryRestConfiguration config;


	public DemoRepositoryEntityLinks(Repositories repositories,
			ResourceMappings mappings, RepositoryRestConfiguration config, HateoasPageableHandlerMethodArgumentResolver resolver) {
		super(repositories, mappings, config, resolver);
		this.resourceMappings = mappings;
		this.config = config;
	}

	@Override
	public LinkBuilder linkFor(Class<?> type) {
		ResourceMetadata metadata = resourceMappings.getMappingFor(type);
		return new DemoRepositoryLinkBuilder(metadata, config.getBaseUri());
	}

}
