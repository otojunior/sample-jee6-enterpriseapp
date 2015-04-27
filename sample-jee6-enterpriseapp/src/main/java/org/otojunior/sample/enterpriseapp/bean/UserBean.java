/**
 * 
 */
package org.otojunior.sample.enterpriseapp.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.otojunior.sample.enterpriseapp.dao.UserDao;
import org.otojunior.sample.enterpriseapp.entity.User;
import org.otojunior.sample.enterpriseapp.entity.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 01456231650
 *
 */
@Model
public class UserBean {
	private static final Logger LOG = LoggerFactory.getLogger(UserBean.class);
	
	@EJB
	private UserDao dao;
	
	private String login;
	private String name;
	
	@Inject
	private UserDto userDto;
	
	/**
	 * Persistence test.
	 * @return Forward action.
	 */
	public String persistenceTest() {
		LOG.info("persistenceTest() called");
		List<User> all = dao.findAll();
		LOG.info("Size: " + all.size());
		LOG.info("Content: " + all.toString());
		return null;
	}
	
	public String find() {
		LOG.info("Searching by: " + login + " and " + name);
		LOG.info("User Null? " + String.valueOf(userDto == null));
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
}
