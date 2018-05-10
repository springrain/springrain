package org.springrain.frame.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * openoffice工具类 wupeilei
 *
 */
@Component
public class OpenOfficeKit {

	private static final Logger logger = LoggerFactory.getLogger(OpenOfficeKit.class);

	private static String openOfficeHome;
	private static String host;
	private static String port;
	private static Process pro = null;
	private static OpenOfficeConnection connection = null;

	/**
	 * 启动openoffice连接
	 * 
	 * @return
	 * @throws Exception
	 */
	private static void createOpenOfficeConnection() {
		try {
			if (connection == null || (!connection.isConnected())) {
				if (StringUtils.isNotBlank(openOfficeHome) && StringUtils.isNotBlank(host)
						&& StringUtils.isNotBlank(port)) {
					String command = openOfficeHome + " -headless -accept=\"socket,host=" + host + ",port=" + port
							+ ";urp;\"";
					pro = Runtime.getRuntime().exec(command);
					connection = new SocketOpenOfficeConnection(host, Integer.parseInt(port));
					connection.connect();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 关闭openoffice连接
	 * 
	 * @throws Exception
	 */
	public static void closeOpenOfficeConnection() {
		try {
			if (connection != null && connection.isConnected()) {
				connection.disconnect();
			}
			if (pro != null) {
				pro.destroy();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void cvtXls(File inputFile, File outputFile) throws Exception {
		createOpenOfficeConnection();
		if (connection != null && connection.isConnected()) {
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
		} else {
			throw new Exception();
		}
	}

	@Value("${springrain.openoffice.home}")
	public  void setOpenOfficeHome(String value) {
		openOfficeHome = value;
		createOpenOfficeConnection();
	}

	@Value("${springrain.openoffice.host}")
	public  void setHost(String value) {
		host = value;
	}

	@Value("${springrain.openoffice.port}")
	public  void setPort(String value) {
		port = value;
	}

}
