package tv.interactivefishing.menu;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import tv.interactivefishing.context.Settings;
import tv.interactivefishing.target.TargetPanel;
import tv.interactivefishing.target.TargetTest;

import static tv.interactivefishing.common.DebugInfo.MENU_PAINT;
import static tv.interactivefishing.menu.MButtonController.getInstances;
import static tv.interactivefishing.menu.MButtonController.buttons;

/**
 * Игровое меню.
 * Используются интернализованные строки из файла настроек
 * @author ilya
 *
 */
public class RMenu {

	/**
	 * Буфферизированный бэкгроунд меню
	 */
	private BufferedImage image;
	
	/**
	 * Координата x левого верхнего угла панели меню, вычисляется 
	 */
	public int x;
	
	/**
	 * Координата y левого верхнего угла панели меню, вычисляется
	 */
	public int y;
	
	/**
	 * Ширина панели меню в открытом состоянии
	 * тэг в session.xml <menu_width_open>
	 */
	public int menu_width_open;
	
	/**
	 * Ширина панели меню в закрытом состоянии
	 */
	public int menu_width_close;
	
	/**
	 * Текущая ширина панели меню
	 */
	public int width;
	
	/**
	 * Высота панели меню
	 */
	public int menu_height;
	
	/**
	 * Количество циклов отрисовки меню при открытии, закрытии
	 */
	public int menu_tiks;
	
	/**
	 * Пауза при цикле отрисовки
	 */
	public int menu_tiks_sleep;
	
	/**
	 * Прозрачность бэкгроунда меню
	 */
	public float menu_alfa;
	
	/**
	 * Флаг видимости меню на экране
	 */
	public boolean visible = false;
	
	/**
	 * Ширина TargetPanel
	 */
	private int tpx;
	
	/**
	 * Флаг инициализации
	 */
	private boolean isInit = true;
	
	/**
	 * Флаг состояния меню
	 */
	public int state;
	
	/**
	 * Выбранная кнопка
	 */
	public MButton selectedButton = null;
	
	/**
	 * Состояние: Меню свернуто
	 */
	public static final int STATE_HIDED = 0;
	
	/**
	 * Состояние: Меню в движении, сворачивается ил разворачивается
	 */
	public static final int STATE_PROCESSING = 1;
	
	/**
	 * Состояние: Меню развернуто
	 */
	public static final int STATE_SHOWN = 2;
	
	/*
	 * Приращение X при анимации меню
	 */
	private int deltaX;
	
	/**
	 * Панель для отрисовки
	 */
	private TargetPanel tap;
	
	/**
	 * Функция открывает меню, ширина
	 */
	public void show()
	{
		state = STATE_PROCESSING;
		
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
            	for(int i = 0; i < menu_tiks; i++)
            	{
            		width = menu_width_close + deltaX*i;
            		x = tpx - width;
            		tap.paintImmediately(tpx-menu_width_open,y,tpx,y+menu_height);
            		tap.validate();
            		try	{Thread.sleep(menu_tiks_sleep);	} catch(InterruptedException ex){}
            	}
            	x = tpx - menu_width_open;
            	width = menu_width_open;
            	state = STATE_SHOWN;
            	tap.paintImmediately(tpx-menu_width_open,y,tpx,y+menu_height);
            }
        });
	}
	
	/**
	 * Функция сворачивает меню
	 */
	public void hide()
	{
		state = STATE_PROCESSING;
		
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

            	for(int i = 0; i < menu_tiks; i++)
            	{
            		width = menu_width_open - deltaX*i;
            		x = tpx - width;
            		tap.paintImmediately(tpx-menu_width_open,y,tpx,y+menu_height);
            		tap.validate();
            		try	{Thread.sleep(menu_tiks_sleep);	} catch(InterruptedException ex){}
            	}
            	x = tpx - menu_width_close;
            	width = menu_width_close;
            	state = STATE_HIDED;
            	tap.paintImmediately(tpx-menu_width_open,y,x+width,y+menu_height);
            }
        });		
	}
	
	/**
	 * Конструктор
	 */
	public RMenu()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("menu_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("menu.png"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		menu_width_open = Integer.parseInt(Settings.settings.get("menu_width_open"));
		menu_width_close = Integer.parseInt(Settings.settings.get("menu_width_close"));
		menu_height = Integer.parseInt(Settings.settings.get("menu_height"));
		menu_tiks = Integer.parseInt(Settings.settings.get("menu_tiks"));
		menu_tiks_sleep = Integer.parseInt(Settings.settings.get("menu_tiks_sleep"));
		menu_alfa = Float.parseFloat(Settings.settings.get("menu_alfa"));
		deltaX = (menu_width_open - menu_width_close) / menu_tiks;
		getInstances();
	}
	
	/**
	 * Функция инициализации
	 */
	private void init()
	{
		if(isInit)
		{
			isInit = false;
			visible = true;
			
			width = menu_width_close;
			tpx = TargetTest.frame.getWidth();
			x = tpx - menu_width_close;
			y = (int)(TargetTest.frame.getHeight() - menu_height)/2; 		
			if(MENU_PAINT) System.out.println("Menu init(): x="+x+" y="+y+" tpx="+ tpx + " menu_height="+menu_height+" width="+width);
			
			tap =TargetTest.tp;
			
			for (int i = 0; i < MButtonController.count; i++)
			{
				MButton mb = buttons.get(i);
				mb.sx1 = tpx - menu_width_open + mb.x;
				mb.sy1 = y + mb.y;
				mb.sx2 = tpx - menu_width_open + mb.x + mb.image.getWidth();
				mb.sy2 = y + mb.y+mb.image.getHeight();
			}
		}
	}
	
	/**
	 * Отрисовка панели меню
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g; 
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, menu_alfa));
		
		if (visible)
		{
			int w;
			int h;
			
			if(width < image.getWidth())
			{
				w = width; 
			}
			else
			{
				w = image.getWidth();
			}
			
			if(menu_height < image.getHeight())
			{
				h = menu_height;
			}
			else
			{
				h = image.getHeight();
			}
			
			g2.drawImage(image, x, y, tpx, y + menu_height, 0, 0, w, h, null);
			
			if(state==STATE_SHOWN)
			{
				for (int i = 0; i < MButtonController.count; i++)
				{
					MButton mb = buttons.get(i);
					if(mb.selected)
					{
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
					}
					g2.drawImage(	mb.image,
									mb.sx1, 
									mb.sy1, 
									mb.sx2, 
									mb.sy2,
									0,
									0,
									mb.image.getWidth(),
									mb.image.getHeight(),
									null
								);
					if(mb.selected)
					{
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, menu_alfa));
					}
				}
			}
		}
		else
		{
			init();			
		}
	}
	
	/**
	 * Обработчик положения курсора
	 * @param X координата X курсора мыши
	 * @param Y координата Y курсора мыши
	 */
	public void onMouseChanged(int X, int Y)
	{
		if (!visible) return;
		if (state == STATE_PROCESSING) return;
		
		if ((Y > y ) && (Y < y+menu_height) && (X>x))
		{
			if(state == STATE_HIDED)
			{
				show();
			}
		}
		else
		{
			if(state == STATE_SHOWN)
			{
				hide();
			}
		}
		
		if(state==STATE_SHOWN)
		{
			for (int i = 0; i < MButtonController.count; i++)
			{
				MButton mb = buttons.get(i);
				if (
						(Y > mb.sy1)&&
						(Y < mb.sy2)&&
						(X > mb.sx1)&&
						(X < mb.sx2)
						)
				{
					if (!mb.selected)
					{
						mb.selected = true;
						//меняем курсор на палец
						tap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						selectedButton = mb;
					}
					
				}
				else
				{
					if (mb.selected)
					{
						mb.selected = false;
						//меняем курсор на стрелку
						tap.setCursor(Cursor.getDefaultCursor());
						selectedButton = null;
					}
				}
				tap.paintImmediately(tpx-menu_width_open,y,x+width,y+menu_height);
				
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
		if (selectedButton == null) return false;
		System.out.println("Selected Button URL:" + selectedButton.url);
		TargetTest.settingsReopen(selectedButton.url, new String[] {""});
		return true;
	}
}

