package com.example.demo.datasource

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = ["com.example.demo.repository"],
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
class DataSourceConfigure {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    fun datasource(): DataSource =
            DataSourceBuilder.create().build()

    @Bean("entityManagerFactory")
    fun entityManagerFactory(_builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return _builder.dataSource(datasource())
                .persistenceUnit("default-PU")
                .packages("com.example.demo.entity")
                .build()
    }

    @Bean("transactionManager")
    fun transactionManager(_entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            entityManagerFactory = _entityManagerFactory
        }
    }

}