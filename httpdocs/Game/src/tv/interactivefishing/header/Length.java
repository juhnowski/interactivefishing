package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.text.NumberFormat;

import tv.interactivefishing.context.Settings;



/**
 * Длина лески - отображается в заголовке
 * @author ilya
 *
 */
public class Length {
	/**
	 * Длина
	 */
	public double length;
	
	/**
	 * Интернационализированая строка
	 */
	public String i18n_length;
	
	/**
	 * Интернационализированая строка
	 */
	public String i18n_length_m;
	
	/**
	 * Координата x левого верхнего угла надписи ДЛИНА
	 */
	public int length_caption_x;
	
	/**
	 * Координата y левого верхнего угла надписи ДЛИНА
	 */
	public int length_caption_y;
	
	/**
	 * Координата x левого верхнего угла значения ДЛИНА
	 */
	public int length_value_x;
	
	/**
	 * Координата y левого верхнего угла значения ДЛИНА
	 */
	public int length_value_y;
	
	/**
	 * Класс для форматирования значения
	 */
	private NumberFormat nf = NumberFormat.getInstance();
	/**
	 * Конструктор
	 */
	public Length()
	{
		length = Double.parseDouble(Settings.settings.get("length"));
		i18n_length = Settings.settings.get("i18n_length");
		i18n_length_m = Settings.settings.get("i18n_length_m");
		
		length_caption_x = Integer.parseInt(Settings.settings.get("length_caption_x"));
		length_caption_y = Integer.parseInt(Settings.settings.get("length_caption_y"));
		
		length_value_x = Integer.parseInt(Settings.settings.get("length_value_x"));
		length_value_y = Integer.parseInt(Settings.settings.get("length_value_y"));
	}
	
	/**
	 * Отрисовка надписи МАССА УЛОВА
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		
		g2.drawString(i18n_length, length_caption_x, length_caption_y);
		g2.drawString(nf.format(length) + " " +i18n_length_m, length_value_x, length_value_y);
	}
}
