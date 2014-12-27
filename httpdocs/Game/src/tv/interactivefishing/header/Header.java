package tv.interactivefishing.header;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
//import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


import tv.interactivefishing.context.Settings;
import tv.interactivefishing.target.TargetTest;

/**
 * Игровой заголовок.
 * Используются интернализованные строки из файла настроек
 * @author ilya
 *
 */
public class Header {
	/**
	 * Буфферизированная панорама
	 */
	public static BufferedImage image;
	
	/**
	 * Имя пользователя
	 */
	public Username username;
	
	/**
	 * Шрифт для всех надписей в заголовке
	 */
	public Font baseFont;
	
	/**
	 * Шрифт для всех надписей в заголовке
	 */
	public Font timeFont;
	
	/**
	 * Игровой таймер
	 */
	public Timer timer;
	
	/**
	 * Количество забросов 
	 */
	public Cast cast;
	
	/**
	 * Количество пойманной рыбы
	 */
	public Fish fish;
	
	/**
	 * Масса улова 
	 */
	public Massa m;

	/**
	 * Наживка
	 */
	public Bait bait;
	
	/**
	 * Twitching
	 */
	public Twitching twitching;
	
	/**
	 * Jerking
	 */
	public Jerking jerking;
	
	/**
	 * WalkingDog
	 */
	public WalkingDog walkingDog;
	
	/**
	 * Uniform
	 */
	public Uniform uniform;
	
	/**
	 * График
	 */
	public Graphik graphik;
	
	/**
	 * Катушка
	 */
	public Coil coil;
	
	/**
	 * Длина лески
	 */
	public Length len;
	
	/**
	 * Удочка
	 */
	public Tackle tackle;
	
	/**
	 * Короткая выделенная область - отрисовывавается в заголовке для выделения значения ДЛИНА ЛЕСКИ
	 */
	public SelectionSmall selSmall;
	
	/**
	 * Выделенная область средней дины - отрисовывавается в заголовке для выделения значения СПОСОБ ПРОВОДКИ
	 */
	public SelectionMedium selMedium;
	
	/**
	 * Длинная выделенная область - отрисовывавается в заголовке для выделения КОЛИЧЕСТВО ЗАБРОСОВ, УЛОВ
	 */
	public SelectionLong selLong;
	
	/**
	 * Диалог выбора насадки
	 */
	public BaitSelection baitSelection;
	
	/**
	 * Конструктор класса
	 * @param targetTest основной класс программы
	 */
	public Header()
	{
		try
		{
			try
			{
				URL url = new URL(Settings.settings.get("header_image"));
				image = ImageIO.read(url);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				image = ImageIO.read(new File("C:/Users/ilya/workspace/youfishing/images/pl.jpg"));
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		initFonts();
		
		cast = new Cast();
		username = new Username();
		timer = new Timer();
		fish = new Fish();
		m = new Massa();
		bait = new Bait();
		twitching = new Twitching();
		jerking = new Jerking();
		walkingDog = new WalkingDog();
		uniform = new Uniform(); 
		graphik = new Graphik();
		coil = new Coil();
		len = new Length();
		tackle = new Tackle(); 
		selSmall = new SelectionSmall(); 
		selMedium = new SelectionMedium();
		selLong = new SelectionLong();
		baitSelection = new BaitSelection();
	}

	
	/**
	 * Инициализация шрифтов
	 */
	private void initFonts()
	{
		baseFont = new Font(Settings.settings.get("base_font_name"),
				Integer.parseInt(Settings.settings.get("base_font_style")),
				Integer.parseInt(Settings.settings.get("base_font_size"))
				);

		timeFont = new Font(Settings.settings.get("time_font_name"),
				Integer.parseInt(Settings.settings.get("time_font_style")),
				Integer.parseInt(Settings.settings.get("time_font_size"))
	);
		
	}
	
	/**
	 * Отрисовка заголовка
	 * @param g канва
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g; 
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
		int height = Integer.parseInt(Settings.settings.get("header_height"));
		g2.drawImage(image, 0, 0, TargetTest.tp.getWidth(), height, 0, 0, image.getWidth(), image.getHeight(), null);
		g2.setColor(Color.WHITE);
		
		g2.setFont(baseFont);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		//FontMetrics fm = g.getFontMetrics();
		username.paintComponent(g2);
		cast.paintComponent(g2);
		fish.paintComponent(g2);
		m.paintComponent(g2);
		selLong.paintComponent(g2);
		
		bait.paintComponent(g2);
		
		twitching.paintComponent(g2);
		jerking.paintComponent(g2);
		walkingDog.paintComponent(g2);
		uniform.paintComponent(g2);
		selMedium.paintComponent(g2);
		
		graphik.paintComponent(g2);
		
		coil.paintComponent(g2);
		
		len.paintComponent(g2);
		selSmall.paintComponent(g2);
		
		tackle.paintComponent(g2);
		
		baitSelection.paintComponent(g2);
		
		g2.setFont(timeFont);
		timer.paintComponent(g2);
		
	}
	

}
