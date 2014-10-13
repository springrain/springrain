package org.springrain.weixin.plugin;


public class TestPlugin extends AbstractPlugin {

    @Override
	public void run(Object... obj) {
		System.out.println("plugin runing");
	}
}
