package scaits.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(value = "mySqlTransactionManager")
public class BulkImporterService {
	
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier(value = "mySqlEntityManager")
	public EntityManagerFactory emf;

	public BulkImporterService(EntityManagerFactory emf) {
		Assert.notNull(emf, "EntityManagerFactory must not be null");
		this.emf = emf;
	}

	public <T> List<T> bulkEntityManagerWithMerge(List<T> items) {
		EntityManager entityManager = emf.createEntityManager();
		try{
		entityManager.getTransaction().begin();
		items.forEach(item -> entityManager.merge(item));
		entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
		    if ( entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
		    	entityManager.getTransaction().rollback();
		    }
		    throw e;
		} finally {
		    if (entityManager != null) {
		        entityManager.close();
		    }
		}

		return items;
	}

	public <T> List<T> bulkEntityManagerWithPersist(List<T> items) {
		EntityManager entityManager = emf.createEntityManager();
		try{
		entityManager.getTransaction().begin();
		items.forEach(item -> entityManager.persist(item));
		entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
		    if ( entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
		    	entityManager.getTransaction().rollback();
		    }
		    throw e;
		} finally {
		    if (entityManager != null) {
		        entityManager.close();
		    }
		}

		return items;
	}
	
	public <T> List<T> bulkEntityManagerWithMergeSize(List<T> items) {
		EntityManager entityManager = emf.createEntityManager();
		try{
		int batchSize=Integer.valueOf(env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
		entityManager.getTransaction().begin();
		int i=1;
		for (T t : items) {
			entityManager.merge(t);
			if (i % batchSize == 0) {
				entityManager.flush();
				entityManager.clear();
				
            }
			i++;
		}
		entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
		    if ( entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
		    	entityManager.getTransaction().rollback();
		    }
		    throw e;
		} finally {
		    if (entityManager != null) {
		        entityManager.close();
		    }
		}

		return items;
	}

	public <T> List<T> bulkEntityManagerWithPersistSize(List<T> items) {
		EntityManager entityManager = emf.createEntityManager();
		try{
		int batchSize=Integer.valueOf(env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
		entityManager.getTransaction().begin();
		int i=1;
		for (T t : items) {
			entityManager.persist(t);
			if (i % batchSize == 0) {
				entityManager.flush();
				entityManager.clear();
            }
			i++;
		}
		entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
		    if ( entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
		    	entityManager.getTransaction().rollback();
		    }
		    throw e;
		} finally {
		    if (entityManager != null) {
		        entityManager.close();
		    }
		}


		return items;
	}
}
