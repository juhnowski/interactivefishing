package tv.interactivefishing.header;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;

/**
 * Вид приманки, отображается в меню приманок
 * @author ilya
 *
 */
public class BaitItem {

	/**
	 * Идентификатор
	 */
	public int id;
	
	/**
	 * Координата X левого вернего угла в панели диалога выбора приманки
	 */
	public int bait_item_x;
	
	/**
	 * Координата Y левого вернего угла в панели диалога выбора приманки
	 */
	public int bait_item_y;

	/**
	 * Ширина иконки
	 */
	public int bait_item_width;
	
	/**
	 * Высота иконки
	 */
	public int bait_item_heigth;

	/**
	 * Иконка подсвечена
	 */
	public boolean selected = false;
	
	/**
	 * ScreenX1 координата X левого верхнего угла кнопки на экране
	 */
	public int sx1;
	
	/**
	 * ScreenY1 координата Y левого верхнего угла кнопки на экране
	 */
	public int sy1;

	/**
	 * ScreenX2 координата X правого нижнего угла кнопки на экране
	 */
	public int sx2;

	/**
	 * ScreenY2 координата Y правого нижнего угла кнопки на экране
	 */
	public int sy2;
	
	/**
	 * Буфферизированная картинка иконки наживки
	 */
	public BufferedImage image;
	
	public BaitItem(int id)
	{
		this.id = id;
		
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("bait_item_image_"+id));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/bait.gif"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		bait_item_x = Integer.parseInt(Settings.settings.get("bait_item_x_"+id));
		bait_item_y = Integer.parseInt(Settings.settings.get("bait_item_y_"+id));
		bait_item_width = Integer.parseInt(Settings.settings.get("bait_item_width_"+id));
		bait_item_heigth = Integer.parseInt(Settings.settings.get("bait_item_heigth_"+id));
	}
}
