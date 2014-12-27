package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;



/**
 * График в заголовке
 * @author ilya
 *
 */
public class Graphik {
	
	/**
	 * Буфферизированная картинка бэкграунда графика
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла графика
	 */
	public int graphik_x;
	
	/**
	 * Координата y левого верхнего угла графика
	 */
	public int graphik_y;
	
	/**
	 *  Высота графика
	 */
	public int graphik_height;
	
	/**
	 * Ширина графика
	 */
	public int graphik_width;
	
	/**
	 * Конструктор
	 */
	public Graphik()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("graphik_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/graphik.gif"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		
		graphik_x = Integer.parseInt(Settings.settings.get("graphik_x"));
		graphik_y = Integer.parseInt(Settings.settings.get("graphik_y"));
		graphik_height = Integer.parseInt(Settings.settings.get("graphik_height"));
		graphik_width = Integer.parseInt(Settings.settings.get("graphik_width"));
	}
	
	/**
	 * Отрисовка графика
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawImage(image, graphik_x, graphik_y, graphik_x + graphik_width, graphik_y + graphik_height, 0, 0, image.getWidth(), image.getHeight(), null);
	}

}
