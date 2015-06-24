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

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Abstract Base Entity.
 * @author Oto Junior (otojunior@gmail.com)
 */
@MappedSuperclass
public abstract class AbstractEntity implements IEntity {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique identification (Primary Key).
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Version. Optimistic Lock ORM Control.
	 */
	@Version
	private Long version;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEquals = true;
		if (this == obj) {
			isEquals = true;
		} else if (obj == null) {
			isEquals = false;
		} else if (getClass() != obj.getClass()) {
			isEquals = false;
		} else {
			AbstractEntity other = (AbstractEntity) obj;
			if (id == null) {
				if (other.id != null) {
					isEquals = false;
				}
			} else if (!id.equals(other.id)) {
				isEquals = false;
			}
		}
		return isEquals;
	}
	
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
