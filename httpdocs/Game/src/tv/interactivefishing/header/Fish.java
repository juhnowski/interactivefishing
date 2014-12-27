package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Количество пойманой рыбы	- надпись в заголовке
 * @author ilya
 *
 */
public class Fish {

	/**
	 * Количество пойманной рыбы
	 */
	public int fish;
	
	/**
	 * Интернационализированная строка надписи КОЛИЧЕСТВО ПОЙМАННОЙ РЫБЫ
	 */
	public String i18n_fish;
	
	/**
	 * координата X надписи FISH
	 */
	public int fish_x;
	
	/**
	 * координата Y надписи FISH, значение Y = username_y
	 */
	public int fish_y;
	
	/**
	 * Конструктор
	 */
	public Fish()
	{
		fish = Integer.parseInt(Settings.settings.get("fish"));
		fish_x = Integer.parseInt(Settings.settings.get("fish_x"));
		fish_y = Integer.parseInt(Settings.settings.get("username_y"));
		i18n_fish = Settings.settings.get("i18n_fish");
	}
	
	/**
	 * Отрисовка надписи КОЛИЧЕСТВО ПОЙМАНОЙ РЫБЫ
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(fish + " " + i18n_fish, fish_x, fish_y);
	}
	
}
