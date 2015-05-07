/**
 * 
 */
package org.otojunior.sample.enterpriseapp.entity;

import java.io.Serializable;

/**
 * @author 01456231650
 *
 */
public interface IEntity extends Serializable {
	public Long getId();
	public Long getVersion();
}
