/*
 * Copyright 2015 Oto Soares Coelho Junior
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.otojunior.sample.enterpriseapp.entity;

import java.io.Serializable;

/**
 * General Interface for Entity 
 * @author Oto Junior (otojunior@gmail.com)
 */
public interface IEntity extends Serializable {
	/**
	 * Get the entity ID.
	 * @return the Entity ID.
	 */
	public Long getId();
	
	/**
	 * Get the optimistic lock version.
	 * @return the version.
	 */
	public Long getVersion();
}
