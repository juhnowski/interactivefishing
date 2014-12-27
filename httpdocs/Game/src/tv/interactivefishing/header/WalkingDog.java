package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Надпись Walking Dog в загаловке
 * @author ilya
 *
 */
public class WalkingDog {

	/**
	 * Координата x левого верхнего угла надписи Walking Dog
	 */
	public int walking_dog_x;

	/**
	 * Координата y левого верхнего угла надписи Walking Dog
	 */
	public int walking_dog_y;
	
	/**
	 * Интернационализированная надпись
	 */
	public String i18n_walking_dog;
	
	/**
	 * Ширина текста Walking Dog
	 */
	public int walking_dog_width;
	/**
	 * Конструктор
	 */
	public WalkingDog()
	{
		walking_dog_x = Integer.parseInt(Settings.settings.get("walking_dog_x"));
		walking_dog_y = Integer.parseInt(Settings.settings.get("walking_dog_y"));
		i18n_walking_dog = Settings.settings.get("i18n_walking_dog");
		walking_dog_width = Integer.parseInt(Settings.settings.get("walking_dog_width"));
	}
	
	/**
	 * Отрисовка надписи Twitching
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(i18n_walking_dog, walking_dog_x, walking_dog_y);
	}
}
//<walking_dog_width>104</walking_dog_width><!-- Ширина текста Walking Dog -->
