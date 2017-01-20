package org.springrain.frame.util.property;

import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertyFile {
	private PropertyResourceBundle propertyResourceBundle;

	public PropertyFile(String fileName) {
		propertyResourceBundle = (PropertyResourceBundle) ResourceBundle
				.getBundle(fileName);
	}

	/**
	 * 根据key获得对应的value
	 * 
	 * @param strPropertyName
	 *            key
	 * @return String
	 */
	public String getString(String strPropertyName) {
		try {
			return propertyResourceBundle.getString(strPropertyName);
		} catch (Exception e) {
			return strPropertyName;
		}
	}

	public String getString(String strPropertyName, Object... obj) {
		String str = propertyResourceBundle.getString(strPropertyName);
		if (str == null) {
			return strPropertyName;
		}
		return MessageFormat.format(str, obj);
	}

	public PropertyResourceBundle getBundle() {
		return propertyResourceBundle;
	}

	public static void main(String[] args) {
		PropertyFile cerpTab = new PropertyFile("db.properties");
		System.out.print(cerpTab.getString("mail.userName"));
	}

}
