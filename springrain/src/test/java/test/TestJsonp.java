package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TestJsonp {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("https://gd.122.gov.cn/m/login?winzoom=1").get();
	    //System.out.println(doc.toString());
		
		//根据ID 得到登录表单的内容,jsonp有很多种选择器,具体查看文档,可以认为是java版的jquery,
		Element elementById = doc.getElementById("gryhdlHeader");
		
		System.out.println(elementById.toString());
		
		
		
	}

}
