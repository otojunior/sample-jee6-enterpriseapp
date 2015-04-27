/**
 * 
 */
package org.otojunior.sample.enterpriseapp.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.otojunior.sample.enterpriseapp.dao.UserDao;
import org.otojunior.sample.enterpriseapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User Application Service.
 * @author 01456231650
 */
@Stateless
public class UserService {
	private static Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@EJB
	private UserDao userDao;
	
	/**
	 * find all Users.
	 * @return All users.
	 */
	public List<User> findAll() {
		LOG.debug("UserService:findAll");
		return userDao.findAll();
	}
}
