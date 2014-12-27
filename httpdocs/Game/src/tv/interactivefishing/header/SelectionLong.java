package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;



/**
 * Длинная выделенная область - отрисовывавается в заголовке для выделения КОЛИЧЕСТВО ЗАБРОСОВ, УЛОВ
 * @author ilya
 *
 */
public class SelectionLong {

	/**
	 * Буфферизированная картинка выделенной области
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла области выделения
	 */
	public int sel_long_x;
	
	/**
	 * Координата y левого верхнего угла области выделения
	 */
	public int sel_long_y;
	
	/**
	 * Высота области выделения
	 */
	public int sel_long_h;
	
	/**
	 * Ширина области выделения
	 */
	public int sel_long_w;
	
	public SelectionLong()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("sel_long_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/sel_long.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		sel_long_x = Integer.parseInt(Settings.settings.get("sel_long_x"));
		sel_long_y = Integer.parseInt(Settings.settings.get("sel_long_y"));	
		sel_long_h = Integer.parseInt(Settings.settings.get("sel_long_h"));
		sel_long_w = Integer.parseInt(Settings.settings.get("sel_long_w"));		
	}
	
	/**
	 * Отрисовка области выделения
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawImage(image, sel_long_x, sel_long_y, sel_long_x + sel_long_w, sel_long_y + sel_long_h, 0, 0, image.getWidth(), image.getHeight(), null);
	}
}
