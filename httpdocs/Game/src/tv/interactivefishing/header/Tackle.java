package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;



/**
 * Монитор Удочка - отображается в заголовке
 * @author ilya
 *
 */
public class Tackle {

	/**
	 * Буфферизированная картинка бэкграунда графика
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла монитора удочки
	 */
	public int tackle_x;
	
	/**
	 * Координата y левого верхнего угла монитора удочки
	 */
	public int tackle_y;
	
	/**
	 * Угол удочки в плоскости XY, радианы
	 */
	public double tackle_angle_xy = 0;
	
	/**
	 * Угол удочки в плоскости YZ, радианы
	 */
	public double tackle_angle_yz = 0;
	
	/**
	 * Конструктор
	 */
	public Tackle()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("tackle_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/tackle.png"));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		tackle_x = Integer.parseInt(Settings.settings.get("tackle_x"));
		tackle_y = Integer.parseInt(Settings.settings.get("tackle_y"));
	}
	
	/**
	 * Отрисовка удочки
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		int height = Integer.parseInt(Settings.settings.get("header_height")) - 4;
		
		g2.drawImage(image, tackle_x, tackle_y, tackle_x + height+18, tackle_y + height, 0, 0, image.getWidth(), image.getHeight(), null);
	
		/**
		 * Координата x центра сканера
		 */
		int xc = tackle_x + (height+18)/2;
		
		/**
		 * Координата y центра сканера
		 */
		int yc = tackle_y+height-8;
		g2.drawLine(xc, yc, (int)(xc + Math.sin(tackle_angle_xy)*Math.sin(tackle_angle_yz)*height/2), (int)(yc - Math.cos(tackle_angle_xy)*Math.cos(tackle_angle_yz)*height/2));

	}
}
