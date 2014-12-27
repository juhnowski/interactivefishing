package tv.interactivefishing.footer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;

/**
 * Датчик пульса в нижнем колонтитуле
 * @author ilya
 *
 */
public class Pulse {

	/**
	 * Значение пульса ударов/минуту
	 */
	public double pulse;
	
	
	/**
	 * Буфферизированный бэкгроунда сканера пульса
	 */
	private BufferedImage image;
	
	/**
	 * Координата X левого верхнего угла сканера пульса
	 */
	public int pulse_x;
	
	/**
	 * Координата Y левого верхнего угла сканера пульса
	 */
	public int pulse_y;

	/**
	 * Интернационализированная строка
	 */
	public String i18n_pulse;
	
	/**
	 * Ширина сканера пульса
	 */
	public int pulse_width;
	
	/**
	 * Высота сканера пульса
	 */
	public int pulse_height;
	
	/**
	 * Координата X текста сканера
	 */
	public int pulse_text_x;
	
	/**
	 * Координата Y текста сканера
	 */
	public int pulse_text_y;
	/**
	 * Конструктор
	 */
	public Pulse()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("pulse_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/pulse.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		pulse = Integer.parseInt(Settings.settings.get("pulse"));
		i18n_pulse = Settings.settings.get("i18n_pulse");
		pulse_x = Integer.parseInt(Settings.settings.get("pulse_x")); 
		pulse_y = Integer.parseInt(Settings.settings.get("pulse_y"));

		pulse_text_x = Integer.parseInt(Settings.settings.get("pulse_text_x")); 
		pulse_text_y = Integer.parseInt(Settings.settings.get("pulse_text_y"));
		
		pulse_width = Integer.parseInt(Settings.settings.get("pulse_width"));
		pulse_height = Integer.parseInt(Settings.settings.get("pulse_height"));
	}
	
	/**
	 * Отрисовка сканера пульса
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(pulse).append(" ").append(i18n_pulse);
		
		g.drawImage(image, pulse_x, pulse_y, pulse_x + pulse_width, pulse_y + pulse_height, 0, 0, image.getWidth(), image.getHeight(), null);
		g.drawString(sb.toString(), pulse_text_x, pulse_text_y);
	}
}
