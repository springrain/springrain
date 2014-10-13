package org.springrain.weixin.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springrain.weixin.plugin.PluginManager;

/**
 * 插件测试
 * @author Administrator
 *
 */
public class PluginTest {

	public static void main(String[] args) throws Exception {
		PluginManager manager = PluginManager.getInstance();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cmd = br.readLine();
		while (!cmd.equals("bye")) {
			if (cmd.startsWith("do")) {
				String pluginName = cmd.split(" ")[1];
				manager.run(pluginName);
			}
			if (cmd.startsWith("install")) {
				String pluginName = cmd.split(" ")[1];
				manager.installPlugin(pluginName);
			}
			if (cmd.startsWith("uninstall")) {
				String pluginName = cmd.split(" ")[1];
				manager.uninstallPlugin(pluginName);
			}
			cmd = br.readLine();
		}
	}
}
