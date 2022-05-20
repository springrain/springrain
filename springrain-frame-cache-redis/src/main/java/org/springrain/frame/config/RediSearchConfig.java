package org.springrain.frame.config;


import com.redis.lettucemod.RedisModulesClient;
import com.redis.lettucemod.api.StatefulRedisModulesConnection;
import com.redis.lettucemod.api.sync.RedisModulesCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置RediSearch客户端
 */
@Configuration("configuration-RediSearchConfig")
public class RediSearchConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    /**
     * 使用redisModulesCommands,操作redisearch
     *
     * @return
     */
    @Bean("redisModulesCommands")
    public RedisModulesCommands<String, String> redisModulesCommands() {
        String uri="redis://"+host+":"+port;
        RedisModulesClient client = RedisModulesClient.create(uri); // (1)
        StatefulRedisModulesConnection<String, String> connection = client.connect(); // (2)
        RedisModulesCommands<String, String> commands = connection.sync(); // (3)
        //redisModulesCommands.create("beers", Field.text("name").build(), Field.numeric("ibu").build()); // (4)
        //SearchResults<String, String> results = redisModulesCommands.search("beers", "chou*"); // (5)
        //System.out.println(results);
        return commands;
    }
}
