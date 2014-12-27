package tv.interactivefishing.header;

import java.awt.Graphics2D;

import tv.interactivefishing.context.Settings;



/**
 * Масса улова - надпись в заголовке
 * @author ilya
 *
 */
public class Massa {
	/**
	 * Улов, кг
	 */
	public double m;
	
	/**
	 * Интернационализированная строка надписи МАССА УЛОВА
	 */
	public String i18n_m;

	/**
	 * координата X надписи масса улова
	 */
	public int m_x;
	
	/**
	 * координата Y надписи масса улова, значение Y = username_y
	 */
	public int m_y;
	
	/**
	 * Конструктор
	 */
	public Massa()
	{
		m = Integer.parseInt(Settings.settings.get("m"));
		m_x = Integer.parseInt(Settings.settings.get("m_x"));
		m_y = Integer.parseInt(Settings.settings.get("username_y"));
		i18n_m = Settings.settings.get("i18n_m");		
	}

	/**
	 * Отрисовка надписи МАССА УЛОВА
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(m + " " + i18n_m, m_x, m_y);
	}
}
