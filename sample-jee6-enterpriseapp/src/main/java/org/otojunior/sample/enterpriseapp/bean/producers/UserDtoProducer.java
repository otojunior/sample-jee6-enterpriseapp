/**
 * 
 */
package org.otojunior.sample.enterpriseapp.bean.producers;

import javax.enterprise.inject.Produces;

import org.otojunior.sample.enterpriseapp.entity.UserDto;

/**
 * @author 01456231650
 *
 */
public class UserDtoProducer {
	/**
	 * Procuces a UserDto.
	 * @return {@link UserDto}
	 */
	//@Produces
	public UserDto produces() {
		return new UserDto();
	}
}
