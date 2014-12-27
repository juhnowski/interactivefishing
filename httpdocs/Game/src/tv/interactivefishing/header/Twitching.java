package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Надпись Twitching в загаловке
 * @author ilya
 *
 */
public class Twitching {
	
	/**
	 * Координата x левого верхнего угла надписи Twitching
	 */
	public int twitching_x;
	
	/**
	 * Координата y левого верхнего угла надписи Twitching
	 */
	public int twitching_y;

	/**
	 * Интернационализированная надпись
	 */
	public String i18n_twitching;
	
	/**
	 * Ширина надписи Twitching
	 */
	public int twitching_width = 80;
	
	/**
	 * Конструктор
	 */
	public Twitching()
	{
		i18n_twitching = Settings.settings.get("i18n_twitching");
		twitching_x = Integer.parseInt(Settings.settings.get("twitching_x"));
		twitching_y = Integer.parseInt(Settings.settings.get("twitching_y"));
		twitching_width = Integer.parseInt(Settings.settings.get("twitching_width"));
	}
	
	/**
	 * Отрисовка надписи Twitching
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(i18n_twitching, twitching_x, twitching_y);
	}
	
}
