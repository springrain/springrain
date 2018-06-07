package test;

import org.junit.Test;
import org.springrain.frame.util.SecUtils;

public class TestBase64 {
    
    @Test
    public void test1(){
        
        String str="我是中国人 ，im is china 。";
        
        String base64=SecUtils.encoderByBase64(str);
        
        System.out.println(base64);
        
        System.out.println(SecUtils.decoderByBase64(base64));
        
        
    }
    

}
