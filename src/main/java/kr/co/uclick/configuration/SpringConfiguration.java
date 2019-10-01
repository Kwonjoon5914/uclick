package kr.co.uclick.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ignite.cache.hibernate.HibernateRegionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration//java config를 위한 annotation
@ImportResource(locations = "classpath:applicationContext-ignite.xml")
@ComponentScan({ "kr.co.uclick.service" })//특정 패키지 안의 클래스들을 스캔하고, @component annotation이 있는 클래스에 대하여 bean을 생성한다. 
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)//java config를 위한 annotation
@EnableSpringConfigured
@EnableJpaRepositories(basePackages = "kr.co.uclick.repository")//jpa Auditing을 활성화 한다.
public class SpringConfiguration {

	@Bean
	@Primary//같은 우선순위로 있는 클래스가 여러개 있을시 그 중 가장먼저 우선순위로 주입할 클래스 타입을 선택
	public DataSource dataSource() {//dataSource 설정
		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//dataSource.setUrl("jdbc:mariadb://127.0.0.1:3306/polytech");
		dataSource.setUrl("jdbc:mysql://192.168.23.107:3306/testdb?serverTimezone=UTC");
//		dataSource.setUsername("root");
		dataSource.setUsername("kjoon");
//		dataSource.setPassword("cafe2413");
		dataSource.setPassword("dcsw01");
		return dataSource;
	}

	//Properties를 이용해서 Hibernate property를 설정합니다.
	@Bean
	@DependsOn("igniteSystem")//(applicationContext-ignite.xml안에 있음)우선순위
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {//sessionFactoryBean과 동일한 역할을 담당하는 FactoryBean이다.
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();//EntityManagerFactory를 생성하는 FactoryBean형태이다.
		em.setDataSource(dataSource());
		em.setPackagesToScan("kr.co.uclick.entity");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();//jpa규약과 hibernate간의 adapter이다. 특별히 설정할 내용은 존재하지 않고
		em.setJpaVendorAdapter(vendorAdapter);							//showSQL정도의 설정만이 존재한다.
		em.setSharedCacheMode(SharedCacheMode.ALL);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {//Transaction을 사용하기 위한 PlatformTransactionManager interface의 구현체를 등록.
		JpaTransactionManager transactionManager = new JpaTransactionManager();//JPA를 지원하는 TransactionManager 등록
		transactionManager.setEntityManagerFactory(emf);//datasource와 entitymanagerFactory를 등록시켜주면 된다.(위에 등록함)
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();//@Repository 클래스들에 대해 자동으로 예외를 Spring의 DataAccessException으로 일괄 변환해준다.
	}

	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");//mapped entity class를 분석하여  schema를 자동으로 생성(development환경에서는 update, production 환경에서는 none 사용)
		properties.setProperty(AvailableSettings.FORMAT_SQL, Boolean.TRUE.toString());//SQL 정렬해서 보기
		properties.setProperty(AvailableSettings.SHOW_SQL, Boolean.TRUE.toString());//SQL 보기
		properties.setProperty(AvailableSettings.USE_SQL_COMMENTS, Boolean.TRUE.toString());
		properties.setProperty(AvailableSettings.DIALECT, MySQL5Dialect.class.getName());//방언 설정

		properties.setProperty(AvailableSettings.STATEMENT_BATCH_SIZE, "1000");//최대 JDBC 배치 크기

		properties.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, Boolean.TRUE.toString());//2level cache를 사용하겠다
		properties.setProperty(AvailableSettings.USE_QUERY_CACHE, Boolean.TRUE.toString());//query cache를 사용하겠다
		properties.setProperty(AvailableSettings.GENERATE_STATISTICS, Boolean.TRUE.toString());//통계 수집 사용
		properties.setProperty(AvailableSettings.CACHE_REGION_FACTORY, HibernateRegionFactory.class.getName());//RegionFactory 구현 클래스

		properties.setProperty("org.apache.ignite.hibernate.ignite_instance_name", "cafe-grid");//ignite instance 이름 설정 
		properties.setProperty("org.apache.ignite.hibernate.default_access_type", "NONSTRICT_READ_WRITE");//default 접근 방법 : 객체 동시 수정 등에 대한 고려를 하지 않고 캐싱을 한다.(엄격하지 않은 읽기/쓰기)

		properties.setProperty(AvailableSettings.PHYSICAL_NAMING_STRATEGY,//PhysicalNamingStrategy사용
				CustomPhysicalNamingStrategyStandardImpl.class.getName());
		return properties;
	}

}
