package tv.interactivefishing.header;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import tv.interactivefishing.context.Settings;
import tv.interactivefishing.target.TargetPanel;
import tv.interactivefishing.target.TargetTest;

import static tv.interactivefishing.header.BaitsController.baititems;


/**
 * Выбор приманки
 * @author ilya
 *
 */
public class BaitSelection {
	
	/**
	 * Буфферизированная картинка выбора наживки
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла диалога
	 */
	public int sel_bait_x;

	/**
	 * Координата y левого верхнего угла диалога
	 */
	public int sel_bait_y;
	
	/**
	 * Ширина диалога
	 */
	public int sel_bait_width;
	
	/**
	 * Высота диалога
	 */
	public int sel_bait_height;
	
	/**
	 * Флаг отрисовки диалога
	 */
	public boolean isVisible = false;
	
	/**
	 * Конструктор
	 */
	public BaitSelection()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("sel_bait_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/sel_bait.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		sel_bait_x = Integer.parseInt(Settings.settings.get("sel_bait_x"));
		sel_bait_y = Integer.parseInt(Settings.settings.get("sel_bait_y"));
		sel_bait_width = Integer.parseInt(Settings.settings.get("sel_bait_width"));
		sel_bait_height = Integer.parseInt(Settings.settings.get("sel_bait_height"));
		
		/**
		 * Инициализируем доступные насадки
		 */
		BaitsController.getInstances();
		
		for (int i = 0; i<BaitsController.count; i++)
		{
			BaitItem b = BaitsController.baititems.get(i);
			b.sx1 = sel_bait_x + b.bait_item_x;
			b.sy1 = sel_bait_y + b.bait_item_y;
			b.sx2 = b.sx1 + b.bait_item_width;
			b.sy2 = b.sy1 + b.bait_item_heigth; 
		}
	}
	
	/**
	 * Отрисовка иконки НАЖИВКА в заголовке
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		if(isVisible)
		{
			g2.drawImage(image, sel_bait_x, sel_bait_y, sel_bait_x + sel_bait_width, sel_bait_y + sel_bait_height, 0, 0, image.getWidth(), image.getHeight(), null);
			for (int i = 0; i < BaitsController.count; i++)
			{
				BaitItem bi = baititems.get(i);
				
				if(bi.selected)
				{
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
				}
				else
				{
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2F));
				}
				
				g2.drawImage(	bi.image,
								bi.sx1, 
								bi.sy1, 
								bi.sx2, 
								bi.sy2,
								0,
								0,
								bi.image.getWidth(),
								bi.image.getHeight(),
								null
							);
/*				if(bi.selected)
				{
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
				}
*/
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
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
		  if ((X > sel_bait_x)&&
			(X < sel_bait_x + sel_bait_width)&&
			(Y > sel_bait_y ) &&
			(Y < sel_bait_y +  sel_bait_height)
			)
		  {
			isVisible = false;
			TargetTest.tp.hdr.bait.clicked = false;
			return true;
		  }
		return false;
	}
	
	/**
	 * Обработчик положения курсора
	 * @param X координата X курсора мыши
	 * @param Y координата Y курсора мыши
	 */
	public void onMouseChanged(int X, int Y)
	{
		for (int i = 0; i<BaitsController.count; i++)
		{
			BaitItem b = BaitsController.baititems.get(i);
			
			if (
					(X > b.sx1) &&
					(X < b.sx2) &&
					(Y > b.sy1) &&
					(Y < b.sy2)
				)
			{
				b.selected = true;
				TargetTest.tp.paintImmediately(b.sx1,b.sy1,b.sx2,b.sy2);
			}
			else
			{
				if (b.selected)
				{
					b.selected = false;
					TargetTest.tp.paintImmediately(b.sx1,b.sy1,b.sx2,b.sy2);
				}
			}
		}
	}

}

