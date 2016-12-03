package org.springrain.frame.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaUtils  {
	//private static  Logger logger = LoggerFactory.getLogger(CaptchaUtils.class);
	// 随机产生的字符串
	private static final String RANDOM_STRS = "4569ABHKMNTUVWXYZ";

	private static final String FONT_NAME = "Times New Roman";//字体
	private static final int FONT_SIZE = 24;//字体大小
	private static final int width = 85;// 图片宽
	private static final int height =25;// 图片高
	private static final int lineNum = 25;// 干扰线数量
	private static final int strNum = 4;// 随机产生字符数量

	private static Random random = new Random();



	/**
	 * 生成随机图片
	 */
	public static BufferedImage genRandomCodeImage(StringBuffer randomCode) {
		// BufferedImage类是具有缓冲区的Image类
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取Graphics对象,便于对图像进行各种绘制操作
		Graphics g = image.getGraphics();
		
		//背景色
		Color c = getRandColor(200, 250);
		g.setColor(c);
		g.fillRect(0, 0, width, height);
		//g.setColor(new Color()); 
		//g.drawRect(0,0,width-1,height-1); 
		g.setColor(getRandColor(160, 200));

		// 绘制随机字符
		g.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
		
		for (int i = 0; i < strNum; i++) {
			randomCode.append(drowString(g, i));
		}

		// 绘制干扰线
		for (int i = 0; i <= lineNum; i++) {
			drowLine(g);
		}
		
		// 添加噪点
		
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
         
        shear(g, width, height, c);// 使图片扭曲
 
        g.setColor(getRandColor(100, 160));
		
		
		g.dispose();
		return image;
	}

	/**
	 * 给定范围获得随机颜色
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 绘制字符串
	 */
	private static String drowString(Graphics g, int i) {
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		String rand = String.valueOf(getRandomString(random.nextInt(RANDOM_STRS
				.length())));
	//	g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 20 * i + 4, 19);
		return rand;
	}

	/**
	 * 绘制干扰线
	 */
	private static void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x0 = random.nextInt(12);
		int y0 = random.nextInt(12);
		g.drawLine(x, y, x + x0, y + y0);
	}

	/**
	 * 获取随机的字符
	 */
	private static String getRandomString(int num) {
		return String.valueOf(RANDOM_STRS.charAt(num));
	}
	
	
	private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }
     
    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }
 
	
	 private static void shear(Graphics g, int w1, int h1, Color color) {
	        shearX(g, w1, h1, color);
	        shearY(g, w1, h1, color);
	    }
	     
	    private static void shearX(Graphics g, int w1, int h1, Color color) {
	 
	        int period = random.nextInt(2);
	 
	        boolean borderGap = true;
	        int frames = 1;
	        int phase = random.nextInt(2);
	 
	        for (int i = 0; i < h1; i++) {
	            double d = (double) (period >> 1)
	                    * Math.sin((double) i / (double) period
	                            + (6.2831853071795862D * (double) phase)
	                            / (double) frames);
	            g.copyArea(0, i, w1, 1, (int) d, 0);
	            if (borderGap) {
	                g.setColor(color);
	                g.drawLine((int) d, i, 0, i);
	                g.drawLine((int) d + w1, i, w1, i);
	            }
	        }
	 
	    }
	 
	    private static void shearY(Graphics g, int w1, int h1, Color color) {
	 
	        int period = random.nextInt(5) + 5; // 10;
	 
	        boolean borderGap = true;
	        int frames = 5;
	        int phase = 2;
	        for (int i = 0; i < w1; i++) {
	            double d = (double) (period >> 1)
	                    * Math.sin((double) i / (double) period
	                            + (6.2831853071795862D * (double) phase)
	                            / (double) frames);
	            g.copyArea(i, 0, 1, h1, 0, (int) d);
	            if (borderGap) {
	                g.setColor(color);
	                g.drawLine(i, (int) d, i, 0);
	                g.drawLine(i, (int) d + h1, i, h1);
	            }
	 
	        }
	 
	    }
	
	
/*
	public static void main(String[] args) {
		CaptchaUtils tool = new CaptchaUtils();
		StringBuffer code = new StringBuffer();
		BufferedImage image = tool.genRandomCodeImage(code);
		try {
			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "JPEG", new FileOutputStream(new File(
					"random-code.jpg")));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

	}
	*/
}

