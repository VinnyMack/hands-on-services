package br.com.synchro.handson.test.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Test;

import br.com.synchro.handson.domain.Address;
import br.com.synchro.handson.test.util.HibernateUtil;

/**
 * Teste
 */

public class HibernateAddressTest {

    /**
     * 
     */
    @After
	//@Test
    public void tearDown() {
	final Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	
	final Address address = (Address) session.createCriteria(Address.class)
		.add(Restrictions.eq("street", "Rua Samuel Morse")).uniqueResult();
	
	session.delete(address);
	session.getTransaction().commit();
    }
 
	@Test
	public void testHibernateCfgAdnPersist(){
		final Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			final Address address = new Address();
			address.setStreet("Rua Samuel Morse");
			address.setNumber(74);
			address.setCity("SP");
			address.setDistrict("SP");
			address.setZipcode("04576-060");

			
			session.persist(address);
			
			session.getTransaction().commit();
			
		}catch (final Exception ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
	}

}
