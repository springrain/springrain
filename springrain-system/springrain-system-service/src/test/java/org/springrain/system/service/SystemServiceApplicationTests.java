package org.springrain.system.service;

import org.springrain.system.manager.entity.Fwlog;
import org.springrain.system.manager.service.IFwlogService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SystemServiceApplicationTests {
	// @Resource
	private IFwlogService fwlogService;

	// @Test
	public void contextLoads() throws Exception {

		Fwlog entity = new Fwlog();

		fwlogService.save(entity);

	}

}
