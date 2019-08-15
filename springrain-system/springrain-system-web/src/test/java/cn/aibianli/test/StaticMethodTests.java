package cn.aibianli.test;

import org.junit.Test;
import org.springrain.frame.util.SecUtils;

public class StaticMethodTests {

    @Test
    public void randomIntegrTest(){
        System.out.println(SecUtils.randomIntegr(1000));
    }
    @Test
    public void getTimeNOTest(){
        System.out.println(SecUtils.getTimeNO());
    }
}
