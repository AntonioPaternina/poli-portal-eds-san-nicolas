package co.com.estacionsannicolas.configuration;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("co.com.estacionsannicolas.repositories")
public class PersistenceSpringConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceSpringConfiguration.class);

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/edsapp");
        } catch (NamingException e) {
            LOGGER.error("Ocurrió un error recuperando DataSource usando JNDI");
        }
        return dataSource;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("co.com.estacionsannicolas.*");
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }

}
