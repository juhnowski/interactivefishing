package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Имя пользователя - надпись в заголовке
 * @author ilya
 *
 */
public class Username {
	/**
	 * Имя пользователя
	 */
	public String username;
	
	/**
	 * координата X надписи ИМЯ ПОЛЬЗОВАТЕЛЯ
	 */
	public int username_x;
	
	/**
	 * координата Y надписи ИМЯ ПОЛЬЗОВАТЕЛЯ
	 */
	public int username_y;
	
	/**
	 * Конструктор
	 */
	public Username()
	{
		username = Settings.settings.get("username");
		username_x = Integer.parseInt(Settings.settings.get("username_x"));
		username_y = Integer.parseInt(Settings.settings.get("username_y"));		
	}
	
	/**
	 * Отрисовка надписи ИМЯ ПОЛЬЗОВАТЕЛЯ
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(username, username_x, username_y);
	}
}
