package org.springrain.frame.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

public class PropertyUtils {
	private Properties propertys;

	public Properties getPropertys() {
		return propertys;
	}

	@Autowired
	public void setPropertys(Properties propertys) {
		this.propertys = propertys;
	}

	public String getProperty(String id) {
		return propertys.getProperty(id);
	}

}
