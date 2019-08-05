package scaits.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


@Component
@CacheConfig
public class QbReportsControllerService {
	
	@Autowired
	@Qualifier(value = "mySqlEntityManager")
	private EntityManagerFactory emf;

	
	@SuppressWarnings("unchecked")
	@Cacheable(value="report")
	public List<Object[]> getReport(String sql) {
		EntityManager em = emf.createEntityManager();
		List<Object[]> list;
		try {
			Query query = em.createNativeQuery(sql);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	


}
