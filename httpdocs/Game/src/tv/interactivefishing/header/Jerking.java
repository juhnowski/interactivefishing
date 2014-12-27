package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Надпись Jerking в заголовке
 * @author ilya
 *
 */
public class Jerking {
	/**
	 * Координата x левого верхнего угла надписи Jerking
	 */
	public int jerking_x;

	/**
	 * Координата y левого верхнего угла надписи Jerking
	 */
	public int jerking_y;
	
	/**
	 * Интернационализированная надпись
	 */
	public String i18n_jerking;
	
	/**
	 * Ширина текста Jerking
	 */
	public int jerking_width = 66;
	
	/**
	 * Конструктор
	 */
	public Jerking()
	{
		jerking_x = Integer.parseInt(Settings.settings.get("jerking_x"));
		jerking_y = Integer.parseInt(Settings.settings.get("jerking_y"));
		i18n_jerking = Settings.settings.get("i18n_jerking");
		jerking_width = Integer.parseInt(Settings.settings.get("jerking_width"));
	}
	
	/**
	 * Отрисовка надписи Jerking
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(i18n_jerking, jerking_x, jerking_y);
	}

}
