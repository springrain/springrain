package org.springrain.frame.util;
import java.awt.image.BufferedImage;

import org.springrain.frame.util.patchca.service.Captcha;
import org.springrain.frame.util.patchca.service.ConfigurableCaptchaService;

public class CaptchaUtils  {
	private static  ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
   

	/**
	 * 生成随机图片
	 */
	public static BufferedImage genRandomCodeImage(StringBuffer randomCode) {
        Captcha captcha = cs.getCaptcha();
        randomCode.append(captcha.getChallenge());
        return captcha.getImage();
		
		
	}

	
/*
	public static void main(String[] args) {
	
		StringBuffer code = new StringBuffer();
		BufferedImage image = CaptchaUtils.genRandomCodeImage(code);
		try {
			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "png", new FileOutputStream(new File(
					"random-code.png")));
		} catch (Exception e) {
			System.out.println(e);
		}

	}
*/
	
	
}

