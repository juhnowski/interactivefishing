package tv.interactivefishing.footer;

import java.awt.Graphics;

import tv.interactivefishing.context.Settings;
import tv.interactivefishing.screen.ScreenModel;
import tv.interactivefishing.target.TargetTest;

/**
 * Нижний колонтитул, отображается внизу экрана и содержит сканеры натяжения лек, пульса и камеры 
 * Бэекграунда нет
 * @author ilya
 *
 */
public class Footer {
	
	/**
	 * Высота колонтитула
	 */
	public int footer_heigth;
	
	/**
	 * Данные камеры
	 */
	public static Camera cam;
	
	/**
	 * Пульс 
	 */
	public static Pulse pls;
	
	/**
	 * Натяжение лески
	 */
	public static Tension tns;
	
	/**
	 * Конструктор
	 */
	public Footer(ScreenModel sm)
	{
		footer_heigth = Integer.parseInt(Settings.settings.get("footer_heigth"));
		cam = new Camera(sm);
		pls = new Pulse();
		tns = new Tension(); 
	}

	/**
	 * Отрисовка нижнего колотнитула
	 * @param g канва
	 */
	public void paintComponent(Graphics g)
	{
		cam.paintComponent(g);
		pls.paintComponent(g);
		tns.paintComponent(g);
	}
}
