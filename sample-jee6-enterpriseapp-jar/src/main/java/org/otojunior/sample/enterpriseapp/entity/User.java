package org.otojunior.sample.enterpriseapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * User Entity - Example entity to demonstrate the archetype.
 * @author [Author name]
 */
@Entity
@NamedQueries({ 
	@NamedQuery(
		name = User.QUERY_FIND_ALL, 
		query = "select new User(u.id, u.login, u.name) from User u"),
	@NamedQuery(
		name = User.QUERY_FIND_BY_LOGIN, 
		query = "select new User(u.id, u.login, u.name) from User u where u.login = :_login")
})
public class User {
	private static final int COL_LENGTH_NAME = 50;
	private static final int COL_LENGTH_LOGIN = 20;
	private static final int COL_LENGTH_SHA1PWD = 40;
	
	public static final String QUERY_FIND_ALL = "user.query.findAll";
	public static final String QUERY_FIND_BY_LOGIN = "user.query.findByLogin";
	
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
	
	@NotNull
	@Column(length=COL_LENGTH_NAME, nullable=false)
	private String name;
	
	/**
	 * Login name.
	 */
	@NotNull
	@Column(length=COL_LENGTH_LOGIN, nullable=false)
	private String login;
	
	/**
	 * SHA-1 hash password.
	 */
	@Size(min=COL_LENGTH_SHA1PWD, max=COL_LENGTH_SHA1PWD)
	@Column(length=COL_LENGTH_SHA1PWD)
	private String sha1Password;
	
	/**
	 * Plaint text password. Used before save.
	 */
	@Transient
	private String password;

	/**
	 * Default constructor
	 */
	public User() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param login
	 * @param name
	 */
	public User(Long id, String login, String name) {
		this.id = id;
		this.login = login;
		this.name = name;
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
			User other = (User) obj;
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
	 * Get the ID.
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the login.
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Get the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the pasword.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Get the SHA-1 Password.
	 * @return the sha1Password
	 */
	public String getSha1Password() {
		return sha1Password;
	}

	/**
	 * Get the version.
	 * @return the version
	 */
	public Long getVersion() {
		return version;
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
	 * Pre-Persist method.
	 */
	@PrePersist
	public void prePersist() {
		if (password != null) {
			sha1Password = DigestUtils.sha1Hex(password);
		}
	}
	
	/**
	 * Set the login.
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set the name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the password.
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
