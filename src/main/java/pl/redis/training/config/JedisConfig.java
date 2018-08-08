package pl.redis.training.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import pl.redis.training.model.Person;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    @Value("${jedis.host}")
    private String jedisHost;

    @Value("${jedis.port}")
    private int jedisPort;

    @SuppressWarnings("deprecation")
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setUsePool(true);
        connectionFactory.setHostName(jedisHost);
        connectionFactory.setPort(jedisPort);

        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Person> redisTemplate(){
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setEnableTransactionSupport(true);

        return template;
    }
}
