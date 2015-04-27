/**
 * 
 */
package org.otojunior.sample.enterpriseapp.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.otojunior.sample.enterpriseapp.dao.UserDao;
import org.otojunior.sample.enterpriseapp.entity.User;
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
		
	@Inject
	private User userDto;
	
	public String find() {
		LOG.info(userDto.toString());
		return null;
	}

	/**
	 * @return the userDto
	 */
	public User getUserDto() {
		return userDto;
	}

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
	
	/**
	 * @param userDto the userDto to set
	 */
	public void setUserDto(User userDto) {
		this.userDto = userDto;
	}
}
