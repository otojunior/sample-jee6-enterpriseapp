/**
 * 
 */
package org.otojunior.sample.enterpriseapp.entity.petowner;

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
 * @author 01456231650
 *
 */
@Entity
@NamedQueries({ 
	@NamedQuery(
		name = PetOwner.QUERY_FIND_ALL, 
		query = "select new org.otojunior.sample.enterpriseapp.entity.petowner.PetOwner(p.id, p.name) from PetOwner p"),
})
public class PetOwner extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "petOwner.query.findAll";
	
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
	 * 
	 */
	public PetOwner() {
		
	}
	
	/**
	 * 
	 */
	public PetOwner(Long id, String name) {
		setId(id);
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the photo
	 */
	public Blob getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
