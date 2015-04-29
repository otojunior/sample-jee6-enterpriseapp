/**
 * 
 */
package org.otojunior.sample.enterpriseapp.user.bean;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.otojunior.sample.enterpriseapp.facade.UserFacade;
import org.otojunior.sample.enterpriseapp.user.dto.UserListDto;
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
	private UserFacade userFacade;
		
	@Inject
	private UserListDto dto;
	
	public String listUsers() {
		dto.setUsers(userFacade.findAll());
		return "users/usersList";
	}
	
	public String search() {
		LOG.info("search");
		dto.setUsers(userFacade.find(dto.getLogin(), dto.getName()));
		return null;
	}
	
	/**
	 * @return the dto
	 */
	public UserListDto getDto() {
		return dto;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(UserListDto dto) {
		this.dto = dto;
	}
}
