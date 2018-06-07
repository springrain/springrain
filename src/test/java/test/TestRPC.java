package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springrain.SpringrainApplication;
import org.springrain.frame.cache.RedisOperation;
import org.springrain.system.entity.Fwlog;
import org.springrain.system.service.IFwlogService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringrainApplication.class)
public class TestRPC {
    
    
    @Resource
    private RedisOperation redisOperation;
    
    @Test
    public void test() throws Exception {
        IFwlogService fwlogService=redisOperation.getRemoteService(IFwlogService.class);
        
        Fwlog findFwlogById = fwlogService.findFwlogById("12bde4ee1fe14d67986e3fac71ee1477");
        System.out.println(findFwlogById);
    }
}
