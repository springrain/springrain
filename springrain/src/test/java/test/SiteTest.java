package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.cms.base.entity.CmsSite;
import org.springrain.cms.base.service.ICmsSiteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SiteTest {
	@Resource
	private ICmsSiteService cmsSiteService;
	
	@Test
	public void addSite() throws Exception{
		CmsSite cmsSite=new CmsSite();
		cmsSite.setName("测试网站");
		cmsSite.setLogo("");
		cmsSite.setLookcount(1);
		cmsSite.setPhone("138");
		cmsSite.setQq("33333");
		cmsSite.setUserId("admin");
		cmsSite.setSiteType(0);
		cmsSite.setState(1);
		cmsSite.setFooter("footer");
		cmsSite.setContacts("contacts");
		cmsSite.setDescription("description");
		cmsSite.setDomainurl("http://www.baidu.com");
		cmsSite.setTitle("title");
		cmsSiteService.saveCmsSite(cmsSite);
	}
	
	
	
	
	
}
