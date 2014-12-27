package tv.interactivefishing.video.protocol;
import java.io.FileWriter;

/**
 * Простейший генератор метаданных
 * @author ilya
 *
 */
public class MetaDataGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * Продолжительность видеоролика
		 */
		long videoTime = 720000;
		
		/**
		 * Время начала заброса
		 */
		long castStartTime = 12000;
		/**
		 * Продолжительность заброса
		 */
		long castEndTime = 15000;
		/**
		 * Пауза на погружение
		 */
		long castPauseTime = 16000;
		
		/**
		 * Продолжительность вываживания
		 */
		long castFinished = 32000;
		/**
		 * L0 - первоначальная длина заброшенной лески
		 */
		double len = 20;
		
		/**
		 * Радиус катушки, м
		 */
		double R = 0.01;
		
		MetaData data;

		StringBuffer sb = new StringBuffer();
		/**
		 * Угловая скорость, считаем w=const 
		 */
		double w = len/((castFinished - castPauseTime)*R);
		
		sb.append("<timecode>");
		for (long t = 0; t < videoTime; t+=1000)
		{
			if(t>castFinished)//заброс завершен
			{
				data = new MetaData(t,0,0,0,0,0,95,0,0,1);
			}
			else if (t > castPauseTime)
			{
				long ct = t - castPauseTime;
				data = new MetaData(t, len - w*R*ct,Math.asin(Math.sin(w*ct))*360/Math.PI,Math.asin(Math.sin(ct)),Math.acos(Math.sin(ct)),1,95,0,0,1);
			}
			else if(t > castEndTime)
			{
				data = new MetaData(t,len,0,0,0,1,95,0,0,1);
			}
			else if(t > castStartTime)
			{
				data = new MetaData(t,len,0,0,0,1,95,0,0,1);
			}
			else
			{
				data = new MetaData(t,0,0,0,0,1,95,0,0,1);
			}
			
			append(sb,data);
		}
		sb.append("</timecode>");
		
		try
		{
			FileWriter out = new FileWriter("output.xml");
			out.write(sb.toString());
			out.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Добавляет секцию с метаданными переданными объектом метаданных в функцию
	 * @param sb генерируемая строка
	 * @param dt объект метаданных
	 */
	private static void append(StringBuffer s, MetaData dt)
	{

		  s.append("<time value =\"").append(dt.time).append("\">")
				.append("<values>")
					.append("<length>").append(dt.length).append("</length>")
					.append("<coil_angle>").append(dt.coil_angle).append("</coil_angle>")
					.append("<tackle_angle_xy>").append(dt.tackle_angle_xy).append("</tackle_angle_xy>")
					.append("<tackle_angle_yz>").append(dt.tackle_angle_yz).append("</tackle_angle_yz>")
				.append("</values>")
			.append("</time>");
	}
}
