package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;



/**
 * Выделенная область средней дины - отрисовывавается в заголовке для выделения способа проводки
 * @author ilya
 *
 */
public class SelectionMedium {

	/**
	 * Буфферизированная картинка выделенной области
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла области выделения
	 */
	public int sel_medium_x;
	
	/**
	 * Координата y левого верхнего угла области выделения
	 */
	public int sel_medium_y;
	
	/**
	 * Высота области выделения
	 */
	public int sel_medium_h;
	
	/**
	 * Ширина области выделения
	 */
	public int sel_medium_w;
	
	public SelectionMedium()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("sel_medium_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/sel_medium.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		sel_medium_x = Integer.parseInt(Settings.settings.get("sel_medium_x"));
		sel_medium_y = Integer.parseInt(Settings.settings.get("sel_medium_y"));	
		sel_medium_h = Integer.parseInt(Settings.settings.get("sel_medium_h"));
		sel_medium_w = Integer.parseInt(Settings.settings.get("sel_medium_w"));
	}
	
	/**
	 * Отрисовка области выделения
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawImage(image, sel_medium_x, sel_medium_y, sel_medium_x + sel_medium_w, sel_medium_y + sel_medium_h, 0, 0, image.getWidth(), image.getHeight(), null);
	}


}
