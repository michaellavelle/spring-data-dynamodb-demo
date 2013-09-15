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

import org.socialsignin.springframework.data.dynamodb.demo.domain.Reply;
import org.socialsignin.springframework.data.dynamodb.demo.domain.ReplyCompositeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository to manage {@link Reply} instances.
 * 
 * @author Michael Lavelle
 */
public interface ReplyRepository extends PagingAndSortingRepository<Reply, ReplyCompositeId> {
	
	public Page<Reply> findByReplyId(@Param("replyId") String replyId,Pageable pageable);
	public Page<Reply> findByReplyIdOrderByReplyDateTimeDesc(@Param("replyId") String replyId,Pageable pageable);
	public Page<Reply> findByMessage(@Param("message") String message,Pageable pageable);
	public Page<Reply> findByReplyDateTime(@Param("replyDateTime") String replyDateTime,Pageable pageable);
	public Page<Reply> findByReplyIdAndReplyDateTimeAfterOrderByReplyDateTimeDesc(@Param("replyId") String replyId,@Param("replyDateTime") String replyDateTime,Pageable pageable);

	
}
