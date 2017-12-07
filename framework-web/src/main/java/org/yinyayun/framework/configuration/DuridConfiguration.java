/**
 * 
 */
package org.yinyayun.framework.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author yinyayun
 * 
 *         Durid以及mybatis相关配置
 */
// Configuration注解对应的类，会有很多Bean的创建
@Configuration
// mapper扫描
@MapperScan(basePackages = "org.yinyayun.framework.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DuridConfiguration {
	@Value("${spring.mybatis.mapperxml.path}")
	private String mapperxml;

	@Primary
	@Bean(name = "dataSource")
	// properties配置中以spring.datasource开头的配置项作为构建DruidDataSource的参数
	// 当然采用这种方式后，上面的配置注入也是可以省略的
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
	}

	@Primary
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * 绑定mapper xml路径
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Primary
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperxml);
		if (resources != null && resources.length > 0) {
			sqlSessionFactoryBean.setMapperLocations(resources);
		}
		return sqlSessionFactoryBean.getObject();
	}
}
