/**
 * 
 */
package org.otojunior.sample.enterpriseapp.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author 01456231650
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements IEntity {
	/**
	 * 
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
	 * @return the id
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
	 * @return the version
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
