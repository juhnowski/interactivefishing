package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;



/**
 * Короткая выделенная область - отрисовывавается в заголовке для выделения значения ДЛИНА ЛЕСКИ
 * @author ilya
 *
 */
public class SelectionSmall {

	/**
	 * Буфферизированная картинка выделенной области
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла области выделения
	 */
	public int sel_small_x;
	
	/**
	 * Координата y левого верхнего угла области выделения
	 */
	public int sel_small_y;
	
	/**
	 * Высота области выделения
	 */
	public int sel_small_h;
	
	/**
	 * Ширина области выделения
	 */
	public int sel_small_w;
	
	/**
	 * Конструктор
	 */
	public SelectionSmall()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("sel_small_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/sel_small.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		sel_small_x = Integer.parseInt(Settings.settings.get("sel_small_x"));
		sel_small_y = Integer.parseInt(Settings.settings.get("sel_small_y"));	
		sel_small_h = Integer.parseInt(Settings.settings.get("sel_small_h"));
		sel_small_w = Integer.parseInt(Settings.settings.get("sel_small_w"));
	}
	
	/**
	 * Отрисовка области выделения
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawImage(image, sel_small_x, sel_small_y, sel_small_x + sel_small_w, sel_small_y + sel_small_h, 0, 0, image.getWidth(), image.getHeight(), null);
	}
}


