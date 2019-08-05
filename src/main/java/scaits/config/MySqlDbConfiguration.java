package scaits.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mysql.jdbc.Driver;

@Configuration
@EnableJpaRepositories(basePackages = "scaits.repository", entityManagerFactoryRef = "mySqlEntityManager", transactionManagerRef = "mySqlTransactionManager")
public class MySqlDbConfiguration {

	@Autowired
	private Environment env;

	@Bean(name = "mySqlDataSource")
	@Primary
	public DataSource mysqlDataSource() {
		java.sql.Driver driver;
		try {
			driver = new Driver();
			return new SimpleDriverDataSource(driver, env.getProperty("mysql.datasource.url"),
					env.getProperty("mysql.datasource.username"), env.getProperty("mysql.datasource.password"));
		} catch (SQLException e) {
			return null;
		}
	}

	@Primary
	@Bean(name = "mySqlEntityManager")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(false);
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		HibernateJpaDialect jpd = new HibernateJpaDialect();
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaDialect(jpd);
		factoryBean.setDataSource(mysqlDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan("scaits");
		factoryBean.setJpaProperties(hibernateProperties());
		return factoryBean;
	}

	@Primary
	  @Bean(name = "mySqlTransactionManager")
	  public PlatformTransactionManager transactionManager(
	    @Qualifier("mySqlEntityManager") EntityManagerFactory 
	    entityManagerFactory
	  ) {
	    return new JpaTransactionManager(entityManagerFactory);
	  }


	private Properties hibernateProperties() {
		Properties info = new Properties();
		info.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
		info.put("hibernate.cache.use_second_level_cache", "false");
		info.put("hibernate.cache.use_query_cache", "true");
		info.put("cache.provider_class", "org.hibernate.cache.EhCacheProvider");
		info.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		info.put("hibernate.enable_lazy_load_no_trans","true");
		return info;
	}

}
