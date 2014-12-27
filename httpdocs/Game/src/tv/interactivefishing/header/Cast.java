package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Класс количество забросов
 * @author ilya
 *
 */
public class Cast {
	
	/**
	 * Интернационализированная строка надписи КОЛИЧЕСТВО ЗАБРОСОВ
	 */
	public String i18n_cast;
	
	/**
	 * Количество забросов
	 */
	public int cast;
	
	/**
	 * координата X надписи CAST
	 */
	public int cast_x;
	
	/**
	 * координата надписи CAST значение Y = username_y
	 */
	public int cast_y;
	
	
	/**
	 * Конструктор
	 */
	public Cast()
	{
		cast = Integer.parseInt(Settings.settings.get("cast"));
		cast_x = Integer.parseInt(Settings.settings.get("cast_x"));
		cast_y = Integer.parseInt(Settings.settings.get("username_y"));
		i18n_cast = Settings.settings.get("i18n_cast");
	}
	
	/**
	 * Отрисовка надписи ИМЯ ПОЛЬЗОВАТЕЛЯ
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(cast + " " + i18n_cast, cast_x, cast_y);
	}

}
