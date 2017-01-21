package test;

import org.junit.Test;
import org.springrain.frame.util.InputSafeUtils;

public class StringSafeTest {

	
	@Test
	public void test1(){
		String str="<script></script>;() >";
		System.out.println(InputSafeUtils.filterTextContent(str));
	}
	
	
	
	
}
