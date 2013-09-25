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
package org.socialsignin.springframework.data.dynamodb.demo.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.springframework.data.dynamodb.demo.domain.Forum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository to manage {@link Forum} instances.
 * 
 * Assumes we do not need paging or sorting for Forums, so we use CrudRepository here.
 * Could change to PagingAndSortingRepository if we did need this funtionality.
 * 
 * Requires @EnableScan so that find by non-hash key (by scanning) is enabled, as scanning
 * is disabled by default as it is an expensive operation if large datasets.
 * 
 * @author Michael Lavelle
 */
@EnableScan
public interface ForumRepository extends CrudRepository<Forum, String> {
	
	public List<Forum> findByCategory(@Param("category") String category);
	public List<Forum> findByThreads(@Param("threads") Integer threads);

}
