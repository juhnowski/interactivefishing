package tv.interactivefishing.header;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;
import tv.interactivefishing.target.TargetTest;



/**
 * Насадка - иконка в заголовке
 * @author ilya
 *
 */
public class Bait {
	
	/**
	 * Буфферизированная картинка иконки наживки
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла иконки наживки
	 */
	public int bait_x;
	
	/**
	 * Координата y левого верхнего угла иконки наживки
	 */
	public int bait_y;
	
	/**
	 * Ширина иконки наживки
	 */
	public int bait_width;
	
	/**
	 * Высота иконки наживки
	 */
	public int bait_height;
	
	/**
	 * Флаг того что иконка подсвечена
	 */
	public boolean selected = false;
	
	/**
	 * Флаг того что иконка выбрана
	 */
	public boolean clicked = false;
	
	
	/**
	 * Конструктор
	 */
	public Bait()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("bait_image"));
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
		
		bait_x = Integer.parseInt(Settings.settings.get("bait_x"));
		bait_y = Integer.parseInt(Settings.settings.get("bait_y"));
		bait_width = Integer.parseInt(Settings.settings.get("header_height")) - 4;
		bait_height = bait_width;

	}

	/**
	 * Отрисовка иконки НАЖИВКА в заголовке
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		if(selected)
		{
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
		}
		else
		{
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
		}
		
		g2.drawImage(image, bait_x, bait_y, bait_x + bait_width, bait_y + bait_height, 0, 0, image.getWidth(), image.getHeight(), null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
	}

	/**
	 * Обработчик положения курсора
	 * @param X координата X курсора мыши
	 * @param Y координата Y курсора мыши
	 */
	public void onMouseChanged(int X, int Y)
	{
		if ((X > bait_x)&&
				(X < bait_x + bait_width)&&
				(Y > bait_y ) &&
				(Y < bait_y +  bait_height)
				)
		{
			selected = true;
			TargetTest.tp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			TargetTest.tp.paintImmediately(bait_x,bait_y,bait_x+bait_width,bait_y+bait_height);
		}
		else
		{
			if((selected)&&(!clicked))
			{
				selected = false;
				TargetTest.tp.setCursor(Cursor.getDefaultCursor());
				TargetTest.tp.paintImmediately(bait_x,bait_y,bait_x+bait_width,bait_y+bait_height);
			}
		}
	}
	
	/**
	 * Обработчик клика
	 * @param X координата X курсора мыши
	 * @param Y координата Y курсора мыши
	 * @return возвращаем true, если клик обработан
	 */	
	public boolean onMouseClicked(int X, int Y)
	{
		if (selected)
		{
			TargetTest.tp.hdr.baitSelection.isVisible = true;
			clicked = true;

			return true;
		}
		else
		{
			return false;
		}
	}
}
