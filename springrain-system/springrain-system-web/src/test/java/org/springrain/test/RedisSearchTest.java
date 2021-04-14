package org.springrain.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springrain.frame.util.RedisSearchUtils;

import io.redisearch.Schema;
import io.redisearch.client.Client;
import io.redisearch.client.IndexDefinition;

/**
 * redisearch Test
 * @copyright 
 * @author AngeryFeather
 * @version 2021年4月14日 下午5:44:36
 * @see org.springrain.test.RedisSearchTest
 */
@SpringBootTest
public class RedisSearchTest {
	
	@Test
	public void createIndexTest() {
		Client client = RedisSearchUtils.getClient();
		
		Schema schema = new Schema().addTextField("first", 1.0).addTextField("last", 1.0).addNumericField("age");
		IndexDefinition definition = new IndexDefinition()
	          .setPrefixes(new String[] {"student:", "pupil:"});
		System.out.println(client.createIndex(schema, Client.IndexOptions.defaultOptions().setDefinition(definition)));
	}
	
}
