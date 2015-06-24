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
package org.otojunior.sample.enterpriseapp.entity.customer;

import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.otojunior.sample.enterpriseapp.entity.AbstractEntity;
import org.otojunior.sample.enterpriseapp.entity.common.Address;

/**
 * Pet Owner Entity.
 * @author Oto Junior (otojunior@gmail.com)
 */
@Entity
@NamedQueries({ 
	@NamedQuery(
		name = Customer.QUERY_FIND_ALL, 
		query = "select new org.otojunior.sample.enterpriseapp.entity.customer.Customer(p.id, p.name) from Customer p"),
})
public class Customer extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "customer.query.findAll";
	
	private static final int COL_LENGTH_NAME = 50;

	@NotNull
	@Column(length=COL_LENGTH_NAME, nullable=false)
	private String name;
	
	@Basic(fetch=FetchType.LAZY)
	@Lob
	private Blob photo;
	
	@Valid
	@Embedded
	private Address address;
	
	/**
	 * Default constructor. 
	 */
	public Customer() {
		
	}
	
	/**
	 * Alternative constructor.
	 * @param id Entity ID.
	 * @param name Pet's owner name.
	 */
	public Customer(Long id, String name) {
		setId(id);
		this.name = name;
	}

	/**
	 * Get the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the photo.
	 * @return the photo
	 */
	public Blob getPhoto() {
		return photo;
	}

	/**
	 * Set the photo.
	 * @param photo the photo to set
	 */
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	/**
	 * Get the address.
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Set the address.
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
