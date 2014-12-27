package tv.interactivefishing.screen;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;


import tv.interactivefishing.common.DebugInfo;
import tv.interactivefishing.context.Settings;

/**
 * Класс модель экрана
 * @author ilya
 *
 */
public class ScreenModel {
	
	/**
	 * Координата X левого верхнего угла экрана
	 */
	public int x;
	
	/**
	 * Координата Y левого верхнего угла экрана
	 */
	public int y;
	
	/**
	 * Зум экрана
	 */
	public double zoom;
	
	/**
	 * шаг зума, на одно вращение колесика мыши
	 */
	public double deltaZoom;
	
	/**
	 * Ширина экрана, пиксели  
	 */
	public int screenWidth;

	/**
	 * Высота экрана, пиксели
	 */
	public int screenHeight;
	
	/**
	 * Ширина панорамы, пиксели
	 */
	public int imageWidth;
	
	/**
	 * Высота панорамы, пиксели
	 */
	public int imageHeight;
	
	/**
	 * Максимально возможный зум панорамы
	 */
	public double maxZoom;
	
	/**
	 * Минимально возможный зум панорамы
	 */
	public double minZoom;
	
	
	/**
	 * Список листенеров событий класса
	 */
	private EventListenerList listenerList = new EventListenerList();
	
	/**
	 * Конструктор
	 * @param x координата X экран
	 * @param y координата Y экрана
	 * @param zoom зум экрана
	 */
	public ScreenModel(int x, int y, double zoom)
	{
		this.x = x;
		this.y = y;
		this.zoom = zoom;
		
		imageHeight = Integer.parseInt(Settings.settings.get("ih"));
		imageWidth = Integer.parseInt(Settings.settings.get("iw"));
		deltaZoom = Double.parseDouble(Settings.settings.get("delta_zoom"));
		maxZoom = Double.parseDouble(Settings.settings.get("max_zoom"));
		minZoom = Double.parseDouble(Settings.settings.get("min_zoom"));
	}
	
	/**
	 * Конструктор с параметрами по умолчанию. Аналогичен вызову ScreenModel(0,0,1);
	 */
	public ScreenModel()
	{
		this(0,0,1);
	}

	/**
	 * Установить координату X левого верхнего угла экрана
	 * @param x
	 */
	public void setX(int _x)
	{
		if (this.x == _x) return;
		
		if(DebugInfo.SCREEN_MODEL) System.out.println("try x="+_x+"; ");
		if(DebugInfo.SCREEN_MODEL) System.out.println("imageWidth*zoom="+imageWidth*zoom + " screenWidth="+screenWidth+" zoom="+zoom);
		
		int maxX = (int)((imageWidth - screenWidth/zoom)); 
		
		
		if (maxX<0) maxX = 0;
		
		if (_x < 0) _x =0;
		
		if (_x > maxX) _x = maxX;
		
		double oldX = this.x;
		
		this.x = _x;
		if(DebugInfo.SCREEN_MODEL) System.out.println("x=" + oldX + "->" + _x + "; maxX="+maxX);
		firePropertyChangeEvent( new PropertyChangeEvent(this,"x",oldX, _x));
		
	}
	
	public int getX()
	{
		return x;
	}
	

	public void setY(int _y)
	{
		if (this.y == _y) return;

		if(DebugInfo.SCREEN_MODEL) System.out.print("try y="+_y+"; ");
		//mouseX/screen.zoom + screen.getX()
		int maxY = (int)((imageHeight - screenHeight/zoom)); 
		if (maxY<0) maxY = 0;
		
		if (_y < 0) _y = 0;
		
		if (_y > maxY) _y = maxY;
		
		int oldY = this.y;
		
		this.y = _y;
		if (DebugInfo.SCREEN_MODEL) System.out.println("y=" + oldY + "->" + _y + "; maxY="+maxY);
		firePropertyChangeEvent( new PropertyChangeEvent(this,"y",oldY, _y));
		
	}
	
	public int getY()
	{
		return y;
	}	
	
	public void setZoom(double zoom)
	{
		if (this.zoom == zoom) return;
		
		if (zoom < minZoom) zoom = minZoom; 
		if (zoom > maxZoom) zoom = maxZoom;
		
		double oldZoom = this.zoom;
		
		this.zoom = zoom;
		if(DebugInfo.SCREEN_MODEL) System.out.println("zoom=" + oldZoom + "->" + zoom);
		firePropertyChangeEvent( new PropertyChangeEvent(this,"zoom",oldZoom, zoom));
	}
	
	public double getZoom()
	{
		return zoom;
	}

	
	private void firePropertyChangeEvent(PropertyChangeEvent event) 
	{
		EventListener[] listeners = listenerList.getListeners(PropertyChangeListener.class);
		
		for(EventListener l: listeners)
		{
			((PropertyChangeListener)l).propertyChange(event);
		}
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		listenerList.add(PropertyChangeListener.class, listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		listenerList.remove(PropertyChangeListener.class, listener);
	}	
}
