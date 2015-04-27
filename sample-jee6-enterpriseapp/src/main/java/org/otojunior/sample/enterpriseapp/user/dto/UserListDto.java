/**
 * 
 */
package org.otojunior.sample.enterpriseapp.user.dto;

import java.util.List;

import org.otojunior.sample.enterpriseapp.entity.User;

/**
 * @author 01456231650
 *
 */
public class UserListDto {
	private String login;
	private String name;
	private List<User> users;
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	/**
	 * User list size.
	 * @return
	 */
	public Integer getUsersListSize() {
		Integer result = null;
		if (users != null) {
			int intResult = users.size();
			result = Integer.valueOf(intResult);
		} else {
			result = Integer.valueOf(0);
		}
		return result;
	}
}
