package org.springrain.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 测试RedisSearch,使用RedisModulesClient
 */
@SpringBootTest
public class RedisSearchTest {

    @Test
    public void rediSearchTest() {

        /*
        RedisModulesClient client = RedisModulesClient.create("redis://127.0.0.1:6379"); // (1)
        StatefulRedisModulesConnection<String, String> connection = client.connect(); // (2)
        RedisModulesCommands<String, String> search = connection.sync(); // (3)
        search.create("beers", Field.text("name").build(), Field.numeric("ibu").build()); // (4)
        SearchResults<String, String> results = search.search("beers", "chou*"); // (5)
        System.out.println(results);
         */

    }

}
