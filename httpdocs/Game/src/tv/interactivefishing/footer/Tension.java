package tv.interactivefishing.footer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;

/**
 * Сканер натяжения леск
 * @author ilya
 *
 */
public class Tension {

	/**
	 * Значение натяжения лески в %
	 */
	public double tension = 0;
	
	/**
	 * Интернационализированная строка натяжение лески
	 */
	public String i18n_tension;
	
	/**
	 * Буфферизированная картинка бэкгроунд сканера натяжение лески
	 */
	private BufferedImage image;
	
	/**
	 * Координата X левого верхнего угла бэкграунда сканера
	 */
	public int tension_x;
	
	/**
	 * Координата X текста сканера
	 */
	public int tension_text_x;

	/**
	 * Координата Y левого верхнего угла бэкграунда сканера
	 */
	public int tension_y;
	
	/**
	 * Координата Y текста сканера
	 */
	public int tension_text_y;
	
	/**
	 * Ширина бэкграунда сканера
	 */
	public int tension_width;
	
	/**
	 * Высота бэкграунда сканера
	 */
	public int tension_height;
	
	public Tension()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("tension_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/tension.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		tension_x = Integer.parseInt(Settings.settings.get("tension_x"));
		tension_y = Integer.parseInt(Settings.settings.get("tension_y"));
		tension_text_x = Integer.parseInt(Settings.settings.get("tension_text_x"));
		tension_text_y = Integer.parseInt(Settings.settings.get("tension_text_y"));
		i18n_tension = Settings.settings.get("i18n_tension");
		tension_width = Integer.parseInt(Settings.settings.get("tension_width"));
		tension_height = Integer.parseInt(Settings.settings.get("tension_height"));
	}

	/**
	 * Отрисовка иконки НАЖИВКА в заголовке
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, tension_x, tension_y, tension_x + tension_width, tension_y + tension_height, 0, 0, image.getWidth(), image.getHeight(), null);
		g.drawLine((int)(tension_x + 4 + tension), tension_y, (int)(tension_x + 4 + tension), tension_y + tension_height);
		g.drawLine((int)(tension_x + 5 + tension), tension_y, (int)(tension_x + 5 + tension), tension_y + tension_height);
		g.drawString(i18n_tension+": " + tension + "%", tension_text_x, tension_text_y);
	}
	
}
