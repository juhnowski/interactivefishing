package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Надпись Uniform в заголовке
 * @author ilya
 *
 */
public class Uniform {

	/**
	 * Координата x левого верхнего угла надписи Uniform
	 */
	public int uniform_x;

	/**
	 * Координата y левого верхнего угла надписи Uniform
	 */
	public int uniform_y;
	
	/**
	 * Интернационализированная надпись
	 */
	public String i18n_uniform;
	
	/**
	 * Ширина текста Uniform
	 */
	public int uniform_width;
	
	/**
	 * Конструктор
	 */
	public Uniform()
	{
		uniform_x = Integer.parseInt(Settings.settings.get("uniform_x"));
		uniform_y = Integer.parseInt(Settings.settings.get("uniform_y"));
		i18n_uniform = Settings.settings.get("i18n_uniform");
		uniform_width = Integer.parseInt(Settings.settings.get("uniform_width"));
	}
	
	/**
	 * Отрисовка надписи Uniform
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(i18n_uniform, uniform_x, uniform_y);
	}
}
