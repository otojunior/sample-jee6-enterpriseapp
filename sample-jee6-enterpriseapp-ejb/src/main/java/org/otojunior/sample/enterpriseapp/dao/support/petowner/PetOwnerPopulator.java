package org.otojunior.sample.enterpriseapp.dao.support.petowner;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.otojunior.sample.enterpriseapp.dao.support.AbstractPopulator;
import org.otojunior.sample.enterpriseapp.entity.common.Address;
import org.otojunior.sample.enterpriseapp.entity.petowner.PetOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Populate a initial data in database to demonstrate the application.
 * @author 01456231650
 */
@Singleton
@Startup
public class PetOwnerPopulator extends AbstractPopulator {
	private static final Logger LOG = LoggerFactory.getLogger(PetOwnerPopulator.class);
	
	/**
	 * Start callback method.
	 */
	@PostConstruct
	@Override
	public void start() {
		LOG.trace("PetOwnerPopulator:start");
		
		for (int i = 0; i < 10; i++) {
			Address a = new Address();
			a.setAddress(randomAddr());
			a.setNumber(String.valueOf(RandomUtils.nextInt(1, 10000)));
			a.setCity(randomStr());
			a.setState("XY");
			
			PetOwner p = new PetOwner();
			p.setName(randomStr());
			p.setAddress(a);
			
			getEntityManager().persist(p);
			LOG.info(p.toString());
		}
	}
	
	/**
	 * Generate a random string for name.
	 * @return Random name.
	 */
	private static String randomStr() {
		int length = RandomUtils.nextInt(5, 10);
		String randomStr = RandomStringUtils.randomAlphabetic(length);
		return StringUtils.capitalize(randomStr.toLowerCase());
	}
	
	/**
	 * Generate a random string for address.
	 * @return Random address name.
	 */
	private static String randomAddr() {
		int type = RandomUtils.nextInt(1, 100000);
		StringBuilder addr = new StringBuilder((type%2==0) ? "Rua " : "Av. ");
		addr.append(StringUtils.capitalize(randomStr().toLowerCase()));
		return addr.toString();
	}
}
