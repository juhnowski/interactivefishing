package tv.interactivefishing.header;

import java.awt.Graphics2D;
import java.text.SimpleDateFormat;


import tv.interactivefishing.context.Settings;
import tv.interactivefishing.target.TargetPanel;
import tv.interactivefishing.target.TargetTest;
import tv.interactivefishing.video.protocol.MetaData;
import tv.interactivefishing.video.protocol.MetaDataReader;


/**
 * Игровой таймер - надпись в заголовке
 * Отвечает за отрисовку заголовка и нижнего колонтитула во время проигрывания видео.
 * @author ilya
 *
 */
public class Timer {

	/**
	 * Значение таймера 
	 */
	public static long timer;

	
	/**
	 * Значение видео таймера 
	 */
	public static long vtimer;
	
	/**
	 * Координата X надписи ТАЙМЕР
	 */
	public static int timer_x;
	
	/**
	 * координата Y надписи ТАЙМЕР
	 */
	public static int timer_y;
	
	/**
	 * Флаг того что видео ролик запущен.
	 */
	public static boolean isVideoStarted = false;
	
	/**
	 * Время старта видео
	 */
	private static long videoStartedTime;
	
	/**
	 * Время завершения видео
	 */
	private static long videoFinishedTime;
	
	private static boolean isExist = false;
	
	/**
	 * Конструктор
	 */
	public Timer()
	{
		//TODO: Т.к. время можно подменить, то тут надо что то делать 
		if (isExist) return;
		
		timer_x = Integer.parseInt(Settings.settings.get("timer_x"));
		timer_y = Integer.parseInt(Settings.settings.get("timer_y"));
		timer = Long.parseLong(Settings.settings.get("timer"));

		new Thread() 
		{
	        public void run() 
	        {
	        	while (timer>0)
	        	{
	        		try	{Thread.sleep(1000);	} catch(InterruptedException ex){}
	        		timer -= 1000;
	        		vtimer += 1000;
	        		
	        		if (isVideoStarted)
	        		{
	        			/**
	        			 * Получаем метаданные для текущего времени
	        			 */
	        			try
	        			{
	        				MetaData m = MetaDataReader.mdlist.get(vtimer);
	        				if (m !=null) updateScaners(m);
	        				else System.out.println("m==null vtimer="+vtimer);
	        			} catch(Exception e)
	        			{
	        				e.printStackTrace();
	        			}
	        			/**
	        			 * Проверяем завершение видео
	        			 */
	        			if (videoFinishedTime>timer)
	        			{
	        				isVideoStarted = false;
	        				TargetPanel.hdr.len.length = 0;
	        			}
	        		}
	        		/**
	        		 * Отрисовка всего хидера
	        		 */
	        		TargetTest.tp.paintImmediately(0,0,TargetTest.tp.hdr.image.getWidth(),TargetTest.tp.hdr.image.getHeight());
	        		
	        		/**
	        		 * Отрисовка футера
	        		 */
	        		TargetTest.tp.paintImmediately(0,TargetTest.tp.screenHeigh -TargetTest.tp.ftr.footer_heigth-40,TargetTest.tp.hdr.image.getWidth(),TargetTest.tp.hdr.image.getHeight());
	        		
	        	}
	        	
	        	System.out.println("game over");
	        }
		}.start();
		
		isExist = true;
	}
	
	/**
	 * Отрисовка надписи ТАЙМЕР
	 * @param g
	 */
	public void paintComponent(Graphics2D g2)
	{
		g2.drawString(getTime(), timer_x, timer_y);
	}
	
	/**
	 * Получить текущее время
	 * @return Текущее время
	 */
	private String getTime()
	{
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("HH:mm:ss");

		return dateFormatLocal.format(timer);
	}
	
	/**
	 * Обработка события запуска видео
	 * @param videoDuration
	 */
	public static void videoStarted(long videoDuration)
	{
		videoStartedTime = timer;
		videoFinishedTime = videoStartedTime - videoDuration; 
		isVideoStarted = true;
		vtimer = 0;
	}
	
	/**
	 * Обновление показаний сканеров
	 * @param md метаданные для конкретного времени
	 */
	public static void updateScaners(MetaData md)
	{
		TargetPanel.hdr.len.length = md.length;
		TargetPanel.hdr.coil.coil_angle = md.coil_angle;
		TargetPanel.hdr.tackle.tackle_angle_xy = md.tackle_angle_xy;
		TargetPanel.hdr.tackle.tackle_angle_yz = md.tackle_angle_yz;
		TargetPanel.ftr.pls.pulse = md.pulse;
		TargetPanel.ftr.tns.tension = md.tension;
		TargetPanel.ftr.cam.screenModel.x = (int)(md.x);
		TargetPanel.ftr.cam.screenModel.y = (int)(md.y);
		TargetPanel.ftr.cam.screenModel.zoom = md.z;
	}
}
