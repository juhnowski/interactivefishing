package tv.interactivefishing.footer;

import java.awt.Font;
import java.awt.Graphics;
import java.text.NumberFormat;

import tv.interactivefishing.context.Settings;
import tv.interactivefishing.screen.ScreenModel;

/**
 * Класс датчик в нижнем колонтитуле
 * @author ilya
 *
 */
public class Camera {

	/**
	 * Координата X верхнего левого угла параметров камеры
	 */
	public int footer_camera_x;

	/**
	 * Координата Y верхнего левого угла параметров камеры
	 */
	public int footer_camera_y;
	
	
	/**
	 * Модель экрана
	 */
	public ScreenModel screenModel;
	
	/**
	 * Класс для форматирования значения
	 */
	private NumberFormat nf = NumberFormat.getInstance();
	
	/**
	 * Шрифт
	 */
	private Font footerFont;
	
	/**
	 * Отрисовка параметро видеокамеры
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		StringBuffer sb = new StringBuffer();
		
		g.setFont(footerFont);
		
		sb.append("X:").append(screenModel.x).
			append(" Y:").append(screenModel.y).
			append(" Z:").append(nf.format(screenModel.zoom*10)).append("%");
		g.drawString(sb.toString(), footer_camera_x, footer_camera_y);
	}
	
	/**
	 * Конструктор
	 * @param sm параметры экрана, равнозначны положению камеры
	 */
	public Camera(ScreenModel sm)
	{
		screenModel = sm;
		footer_camera_x = Integer.parseInt(Settings.settings.get("footer_camera_x"));
		footer_camera_y = Integer.parseInt(Settings.settings.get("footer_camera_y"));
		
		footerFont = new Font(Settings.settings.get("footer_font_name"),
				Integer.parseInt(Settings.settings.get("footer_font_style")),
				Integer.parseInt(Settings.settings.get("footer_font_size"))
				);
	}
}
