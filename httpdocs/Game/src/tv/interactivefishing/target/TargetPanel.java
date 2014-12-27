package tv.interactivefishing.target;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;


import tv.interactivefishing.common.DebugInfo;
import tv.interactivefishing.context.Settings;
import tv.interactivefishing.footer.Camera;
import tv.interactivefishing.footer.Footer;
import tv.interactivefishing.footer.Pulse;
import tv.interactivefishing.footer.Tension;
import tv.interactivefishing.header.Header;
import tv.interactivefishing.menu.RMenu;
import tv.interactivefishing.screen.ScreenModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Класс для отрисовки игрового пространства 
 * @author ilya
 *
 */
@SuppressWarnings("serial")
public class TargetPanel extends JPanel {

	/**
	 * Цвет выбранного целеуказателя
	 */
	private Color selectedColor;
	
	/**
	 * Цвет активного целеуказателя
	 */
	private Color activeColor;
	
	/**
	 * Цвет для неактивного целеуказателя
	 */
	private Color disabledColor;

	/**
	 * Вспомогательная канва для отрисовки целеуказателя 
	 */
	private Paint pt;
	
	/**
	 * Буфферизированная панорама
	 */
	private BufferedImage image;
	
	/**
	 * Последняя координата мыши X
	 */
	private int lastX;

	/**
	 * Последняя координата мыши Y
	 */
	private int lastY;
	
	/**
	 * ширина экрана
	 */
	public int screenWidth;

	/**
	 * высота экрана
	 */
	public int screenHeigh;
	
	/**
	 * Модель экрана
	 */
	public ScreenModel screen = new ScreenModel();
	
	/**
	 * Состояние
	 */
	public int state = 0;
	
	/**
	 * Заголовок
	 */
	public static Header hdr;
	
	/**
	 * Меню
	 */
	public static RMenu mnu;

	/**
	 * Нижний колонтитул
	 */
	public static Footer ftr;
	
	/**
	 * Флаг отрисовки панорамы
	 */
	public static boolean isPanVisible=true;
	
	/**
	 * Список листенеров событий класса
	 */
	private EventListenerList listenerList = new EventListenerList();
	
	/**
	 * Флаг показывает что операция клика уже обработана, и mouseReleased выполнять уже не надо
	 */
	public boolean isActionHandled = false;
	
	/**
	 * Create the panel.
	 */
	public TargetPanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				switch(e.getButton())
				{
				case 1:
					{
//обработка событий=====================================================================================================
						if (isPanVisible)
						{
							if(hdr.baitSelection.isVisible)
							{
								if (hdr.baitSelection.onMouseClicked(e.getX(), e.getY()))
								{
									isActionHandled = true;
									repaint();
									break;
								}
								else
								{
									break;
								}
							}
							else
							{
								if(hdr.bait.onMouseClicked(e.getX(),e.getY()))
								{
									isActionHandled = true;
									repaint();
									break;
								}
								
							}
						}
						
						if(mnu.onMouseClicked(e.getX(), e.getY()))
						{
							isActionHandled = true;
							break;
						}
						
						if ((e.getX() > hdr.twitching.twitching_x)&&
								(e.getX() < hdr.twitching.twitching_x + hdr.twitching.twitching_width)&&
								(e.getY() > hdr.twitching.twitching_y - 20 ) &&
								(e.getY() < hdr.twitching.twitching_y)
								)
							{
								hdr.selMedium.sel_medium_x = hdr.twitching.twitching_x-4;
								hdr.selMedium.sel_medium_w = hdr.twitching.twitching_width;
								isActionHandled = true;
								repaint();
								break;
							}
						

						if ((e.getX() > hdr.jerking.jerking_x)&&
								(e.getX() < hdr.jerking.jerking_x + hdr.jerking.jerking_width)&&
								(e.getY() > hdr.jerking.jerking_y - 20 ) &&
								(e.getY() < hdr.jerking.jerking_y)
								)
							{
								hdr.selMedium.sel_medium_x = hdr.jerking.jerking_x-4;
								hdr.selMedium.sel_medium_w = hdr.jerking.jerking_width;
								isActionHandled = true;
								repaint();
								break;
							}
						
						if ((e.getX() > hdr.walkingDog.walking_dog_x)&&
								(e.getX() < hdr.walkingDog.walking_dog_x + hdr.walkingDog.walking_dog_width)&&
								(e.getY() > hdr.walkingDog.walking_dog_y - 20) &&
								(e.getY() < hdr.walkingDog.walking_dog_y )
								)
							{
								hdr.selMedium.sel_medium_x = hdr.walkingDog.walking_dog_x-4;
								hdr.selMedium.sel_medium_w = hdr.walkingDog.walking_dog_width;
								isActionHandled = true;
								repaint();
								break;
							}
						
						if ((e.getX() > hdr.uniform.uniform_x)&&
								(e.getX() < hdr.uniform.uniform_x + hdr.uniform.uniform_width)&&
								(e.getY() > hdr.uniform.uniform_y  - 20) &&
								(e.getY() < hdr.uniform.uniform_y)
								)
							{
								hdr.selMedium.sel_medium_x = hdr.uniform.uniform_x-4;
								hdr.selMedium.sel_medium_w = hdr.uniform.uniform_width;
								isActionHandled = true;
								repaint();
								break;
							}
						
						
//обработка событий=====================================================================================================						
						for(Target t: TargetController.targets)
						{
							if (t.isSelected)
							{
								System.out.println("t.id=" + t.id+" t.isSelected="+t.isSelected);
								startPlayVideo();
							}
						}

						lastX = e.getX();
						lastY = e.getY();
						setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
						break;
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(DebugInfo.TARGETPANEL_MOUSE_RELEASED){System.out.println("mouseReleased X=" + e.getX() + " Y="+e.getY());};
				
				if (hdr.baitSelection.isVisible)
				{
					return;
				}
				
				if(isActionHandled) 
				{
					isActionHandled = false;
					return;
				}
			
				switch(e.getButton())
				{
				case 1:
					{
						imageMove(e.getX(), e.getY());
						
						setCursor(Cursor.getDefaultCursor());
						break;
					}
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent evt) {

				boolean needRepaint = false;
				
				if(DebugInfo.TARGETPANEL_MOUSE_MOVED){ System.out.println("MouseEvent X=" + evt.getX() + " Y="+evt.getY());}
				
				int mouseX = evt.getX();
				int mouseY = evt.getY();

				int deltaX = (int)((mouseX/screen.zoom + screen.getX()));
				int deltaY = (int)((mouseY/screen.zoom + screen.getY()));

				for(Target t: TargetController.targets)
				{
					if(t.isAvailable)
					{
						
					  if ((Math.abs(t.stx - deltaX) < t.sabw*screen.zoom/2) && 
					      (Math.abs(t.sty - deltaY) < t.sabh*screen.zoom/2)
					     )
					  {
						//Курсор мыши попал в зону целеуказателя 
						if (!t.isSelected)
						{
							if(DebugInfo.TARGETPANEL_MOUSE_MOVED_IS_SELECTED) System.out.println("t.name=" + t.name + " isSelected=true"); 
							t.isSelected = true;
							setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							needRepaint = true;
						}
					  }
					  else
					  {
						if (t.isSelected)
						{
							//Курсор мыши вышел из зоны выбранного целеуказателя
							t.isSelected = false;
							setCursor(Cursor.getDefaultCursor());
							needRepaint = true;
						}
					  }
					}
				}
				mnu.onMouseChanged(mouseX, mouseY);
				hdr.bait.onMouseChanged(mouseX, mouseY);
				if(hdr.baitSelection.isVisible)
				{
					hdr.baitSelection.onMouseChanged(mouseX, mouseY);
				}
				
				if(needRepaint) repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(DebugInfo.TARGETPANEL_MOUSE_DRAGGED){System.out.println("mouseDragged X=" + e.getX() + " Y="+e.getY());}
				switch(e.getButton())
				{
				case 1:
					{
						imageMove(e.getX(), e.getY());
					}
				}
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent ev) {
				if(DebugInfo.TARGETPANEL_MOUSE_WHEEL_MOVED){System.out.println("mouseWheelMoved getWheelRotation=" + ev.getWheelRotation()+ " getScrollAmount=" + ev.getScrollAmount());}
				imageScale(ev.getWheelRotation(), ev.getScrollAmount());
			}
		});
		
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/2.jpg"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		TargetController.getInstances();
		
		
		selectedColor = new Color(
				Float.parseFloat(Settings.settings.get("color_r_selected")),
				Float.parseFloat(Settings.settings.get("color_g_selected")),
				Float.parseFloat(Settings.settings.get("color_b_selected")),
				Float.parseFloat(Settings.settings.get("color_a_selected"))
			);
		
		activeColor = new Color(
				Float.parseFloat(Settings.settings.get("color_r_enabled")),
				Float.parseFloat(Settings.settings.get("color_g_enabled")),
				Float.parseFloat(Settings.settings.get("color_b_enabled")),
				Float.parseFloat(Settings.settings.get("color_a_enabled"))
			);
		
		disabledColor = new Color(
				Float.parseFloat(Settings.settings.get("color_r_disabled")),
				Float.parseFloat(Settings.settings.get("color_g_disabled")),
				Float.parseFloat(Settings.settings.get("color_b_disabled")),
				Float.parseFloat(Settings.settings.get("color_a_disabled"))
			);
		
		hdr = new Header();
		mnu = new RMenu(); 
		ftr = new Footer(screen);
	}
	
	/**
	 * Отрисовка компонента
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (isPanVisible)
		{
		g.drawImage(image, 0, 0, screenWidth,screenHeigh,(int)(screen.getX()),(int)(screen.getY()),(int)(screenWidth/screen.getZoom()+screen.getX()),(int)(screenHeigh/screen.getZoom()+screen.getY()), null);
		
		for(Target t: TargetController.targets)
		{
		if(t.isVisible)
		{
				Graphics2D g2 = (Graphics2D)g; 
				g2.setPaint(pt);
				
				Color cl = switchColor(t);			
				g2.setColor(cl);
				pt = new GradientPaint(0,0,cl, 5, 10, cl);
				Ellipse2D ellipse1 = new Ellipse2D.Double(
										(-screen.getX()+t.stx - t.sabw/2)*screen.zoom,
										(-screen.getY() + t.sty - t.sabh/2)*screen.zoom,
										t.sabw*screen.zoom,
										t.sabh*screen.zoom
										);
				g2.fill(ellipse1);
				pt = new GradientPaint(0,0,cl, 5, 10, cl);
				Ellipse2D ellipse2 = new Ellipse2D.Double(
										(-screen.getX()+t.stx - t.sasw/2)*screen.zoom,
										(-screen.getY() + t.sty - t.sash/2 )*screen.zoom,
										(t.sasw)*screen.zoom,
										(t.sash)*screen.zoom
										);
				g2.fill(ellipse2);
		}
		}
		}
		hdr.paintComponent(g);
		mnu.paintComponent(g);
		ftr.paintComponent(g);
	}
	
	/**
	 * Получить цвет для целеуказателя в зависимости от его состояния.
	 * @param t целеуказатель
	 * @return цвет
	 */
	private Color switchColor(Target t)
	{
		if (t.isSelected)
		{
			return selectedColor;
		}
		
		if (t.isAvailable)
		{
			return activeColor;
		}
		else
		{
			return disabledColor;
		}
	}
	
	/**
	 * Обработчик перемещения панорамы 
	 * @param x новая координата X
	 * @param y новая координата Y
	 */
	public void imageMove(int x, int y)
	{
		try
		{
			screen.setX(screen.getX() + lastX-x);
			lastX = x;
		}catch(IllegalArgumentException e){}
		
		try
		{
			screen.setY(screen.getY() + lastY-y);
			lastY = y;
		}catch(IllegalArgumentException e){}
		
		repaint();		
		validate();
	}

	/**
	 * Устанавливает координаты картинки 
	 * @param x новая координата X
	 * @param y новая координата Y
	 */
	public void imageSet(int x, int y)
	{
		screen.setX(x);
		screen.setY(y);
		repaint();		
		validate();
	}
	
	/**
	 * Обработчик масштабирования панорамы
	 * @param rotation вращение колесика мышки
	 * @param scrollAmount количество позиций колесика мышки
	 */
	public void imageScale(int rotation, int scrollAmount)
	{
		screen.setZoom(screen.getZoom() + rotation*screen.deltaZoom*scrollAmount);
		repaint();
		validate();
	}
	
	/**
	 * Обработчик масштабирования панорамы
	 * @param z зум
	 */
	public void imageScale(double z)
	{
		screen.setZoom(z);
		repaint();
		validate();
	}
	
	
	/**
	 * Функция запуска воспроизведения видео ролика
	 */
	public void startPlayVideo()
	{
		state = 1;
		firePropertyChangeEvent( new PropertyChangeEvent(this,"state",0, 1));
	}
	
	/**
	 * Обнаруживает всех зарегестрированных обработчиков событий и для каждого из них вызывает метод propertyChange
	 * @param event событие
	 */
	private void firePropertyChangeEvent(PropertyChangeEvent event) 
	{
		EventListener[] listeners = listenerList.getListeners(PropertyChangeListener.class);
		
		for(EventListener l: listeners)
		{
			((PropertyChangeListener)l).propertyChange(event);
		}
		
	}
	
	/**
	 * Добавление обработчика событий
	 * @param listener Обработчик
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		listenerList.add(PropertyChangeListener.class, listener);
	}
	
	/**
	 * Удаление обработчика событий
	 * @param listener Обработчик
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		listenerList.remove(PropertyChangeListener.class, listener);
	}

}
