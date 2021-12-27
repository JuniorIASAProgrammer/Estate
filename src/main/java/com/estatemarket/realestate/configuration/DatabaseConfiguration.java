package com.estatemarket.realestate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;


@Configuration
public class DatabaseConfiguration {

    @Resource
    private Environment environment;

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public DriverManagerDataSource dataSource(StringRedisTemplate stringRedisTemplate){
        String mysqlUsername = stringRedisTemplate.opsForValue().get(environment.getProperty("spring.datasource.username"));
        String mysqlPassword = stringRedisTemplate.opsForValue().get(environment.getProperty("spring.datasource.password"));
        DriverManagerDataSource mysqlInstance = new DriverManagerDataSource();
        mysqlInstance.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
        mysqlInstance.setUrl(environment.getProperty("spring.datasource.url"));
        mysqlInstance.setUsername(mysqlUsername);
        mysqlInstance.setPassword(mysqlPassword);
        return mysqlInstance;
    }
}