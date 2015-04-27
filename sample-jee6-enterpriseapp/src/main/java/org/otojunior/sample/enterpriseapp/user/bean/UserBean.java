/**
 * 
 */
package org.otojunior.sample.enterpriseapp.user.bean;

import javax.annotation.PostConstruct;
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
	
	@PostConstruct
	public void start() {
		LOG.info("NULL ? " + (dto.getUsers() == null));
		if (dto.getUsers() == null) {
			findAll();
		}
	}
	
	public String find() {
		LOG.info("Find");
		return null;
	}
	
	private void findAll() {
		dto.setUsers(userFacade.findAll());
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
