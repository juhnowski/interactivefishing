package tv.interactivefishing.menu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;

/**
 * Кнопка меню 
 * @author ilya
 *
 */
public class MButton {

	/**
	 * Буфферизированный бэкгроунд кнопки меню
	 */
	public BufferedImage image;
	
	/**
	 * Координата X левого верхнего угла в пикселях
	 */
	public int x;

	/**
	 * Координата Y левого верхнего угла в пикселях
	 */
	public int y;
	
	/**
	 * Идентификатор объекта
	 */
	public int id;
	
	/**
	 * Адрес команды
	 */
	public String url;
	
	/**
	 * Флаг подсветки кнопки, true - указатель мыши находится над кнопкой
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
	 * Конструктор
	 * @param Name имя кнопки
	 */
	public MButton(int id)
	{
		this.id = id;
		x = Integer.parseInt(Settings.settings.get("mnu_x_" + id));
		y = Integer.parseInt(Settings.settings.get("mnu_y_" + id));
		url = Settings.settings.get("mnu_url_" + id);
		
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("mnu_image_" + id));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("c"+id+".png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}