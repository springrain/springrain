package org.springrain.system.service;

import org.springrain.system.core.entity.Fwlog;
import org.springrain.system.core.service.IFwlogService;

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
