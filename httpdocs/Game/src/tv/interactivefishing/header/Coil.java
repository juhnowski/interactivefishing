package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;



/**
 * Катушка - отображает в заголовке
 * @author ilya
 *
 */
public class Coil {

	/**
	 * Буфферизированная картинка бэкграунда графика
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла катушки
	 */
	public int coil_x;
	
	/**
	 * Координата y левого верхнего угла катушки
	 */
	public int coil_y;
	
	/**
	 * Угол положения катушки, радиан
	 */
	public double coil_angle = 0;
	
	public Coil()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("coil_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/coil.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		coil_x = Integer.parseInt(Settings.settings.get("coil_x"));
		coil_y = Integer.parseInt(Settings.settings.get("coil_y"));
	}
	
	/**
	 * Отрисовка катушки
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		int height = Integer.parseInt(Settings.settings.get("header_height")) - 4;
		g2.drawImage(image, coil_x, coil_y, coil_x + height, coil_y + height, 0, 0, image.getWidth(), image.getHeight(), null);
		
		/**
		 * Координата x центра сканера
		 */
		int xc = coil_x + height/2;
		
		/**
		 * Координата y центра сканера
		 */
		int yc = coil_y + height/2;
		g2.drawLine(xc, yc, (int)(xc + Math.sin(coil_angle)*height/2), (int)(yc - Math.cos(coil_angle)*height/2));
		
	}
}
