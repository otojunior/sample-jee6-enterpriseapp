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
package org.otojunior.sample.enterpriseapp.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Address Entity.
 *
 * @author Oto Junior (otojunior@gmail.com)
 * @version $Id: $Id
 */
@Embeddable
public class Address {
	private static final int COL_LENGTH_ADDR = 50;
	private static final int COL_LENGTH_NUMBER = 6;
	private static final int COL_LENGTH_ZIPCODE = 8;
	private static final int COL_LENGTH_CITY = 20;
	private static final int COL_LENGTH_STATE = 2;
	
	@NotNull
	@Column(length=COL_LENGTH_ADDR, nullable=false)
	private String address;
	
	@NotNull
	@Column(length=COL_LENGTH_NUMBER, nullable=false)
	private String number;
	
	@Column(length=COL_LENGTH_ZIPCODE, nullable=true)
	private String zipCode;
	
	@NotNull
	@Column(length=COL_LENGTH_CITY, nullable=false)
	private String city;

	@NotNull
	@Size(min=2, max=2)
	@Column(length=COL_LENGTH_STATE, nullable=false, columnDefinition="char(2)")
	private String state;
	
	/**
	 * <p>Getter for the field <code>address</code>.</p>
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * <p>Getter for the field <code>city</code>.</p>
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * <p>Getter for the field <code>number</code>.</p>
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * <p>Getter for the field <code>state</code>.</p>
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * <p>Getter for the field <code>zipCode</code>.</p>
	 *
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * <p>Setter for the field <code>address</code>.</p>
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * <p>Setter for the field <code>city</code>.</p>
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * <p>Setter for the field <code>number</code>.</p>
	 *
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * <p>Setter for the field <code>state</code>.</p>
	 *
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * <p>Setter for the field <code>zipCode</code>.</p>
	 *
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Address other = (Address) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}
}
