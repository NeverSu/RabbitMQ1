package com.bfxy.base.rabbit.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 *  $BrokerMessageConfiguration
 *  帮助执行脚本
 *  帮助进行数据表结构的创建
 * @author Kylin Su
 * @date 2021/2/19 12:47 AM
 */
@Configuration
public class BrokerMessageConfiguration {

    @Autowired
    private DataSource rabbitProducerDataSource;

    @Value("classpath:rabbit-producer-message-schema.sql")
    private Resource schemaScript;

    @Bean
    public DataSourceInitializer initDataSourceInitializer() {
        System.err.println("------------------rabbitProducerDataSource-------------: " + rabbitProducerDataSource);
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(rabbitProducerDataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }
}
