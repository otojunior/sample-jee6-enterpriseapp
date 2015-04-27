/**
 * 
 */
package org.otojunior.sample.enterpriseapp.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.otojunior.sample.enterpriseapp.entity.User;
import org.otojunior.sample.enterpriseapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User Session Facade.
 * @author 01456231650
 */
@Stateless
public class UserFacade {
	private static Logger LOG = LoggerFactory.getLogger(UserFacade.class);
	
	@EJB
	private UserService userService;
	
	/**
	 * find all Users.
	 * @return All users.
	 */
	public List<User> findAll() {
		LOG.debug("UserFacade:findAll");
		return userService.findAll();
	}
}
