package tv.interactivefishing.video.protocol;
/**
 * Класс метаданных.
 * Моедль xml секции
 * <time value = 12000> <!-- значение времени в мс с начала видео-->
 *	<values> <!-- значения метаданных для момента времени-->
 *		<length>15</length> <!-- длина заброшеной лески -->
 *		<coil_angle>280</coil_angle> <!-- угол положения катушки -->
 *		<tackle_angle_xy>30</tackle_angle_xy> <!-- угол удочки в плоскости XY-->
 *		<tackle_angle_yz>30</tackle_angle_yz> <!-- угол удочки в плоскости YZ-->
 *	<values>
 *</time>
 * @author ilya
 *
 */
public class MetaData {

	/**
	 * Таймкод, мсек
	 */
	public long time;
	
	/**
	 * Длина заброшеной лески, м
	 */
	public double length;
	
	/**
	 * Угол положения катушки, радианы
	 */
	public double coil_angle;
	
	/**
	 * Угол удочки в плоскости XY, радианы
	 */
	public double tackle_angle_xy;
	
	/**
	 * Угол удочки в плоскости YZ, радианы
	 */
	public double tackle_angle_yz;
	
	/**
	 * Натяжение лески, значение от 0 до 100, %
	 */
	public double tension;
	
	/**
	 * Пульс рыбака, ударов в минуту
	 */
	public double pulse;
	
	/**
	 * Координата изображения X
	 */
	public double x;

	/**
	 * Координата изображения Y
	 */
	public double y;
	
	/**
	 * Координата изображения Z
	 */
	public double z;
	
	/**
	 * Конструктор
	 * @param time время
	 */
	public MetaData(long time)
	{
		this.time = time;
	}

	/**
	 * Конструктор
	 */
	public MetaData()
	{
	}
	
	/**
	 * Конструктор
	 * @param time Таймкод, мсек
	 * @param length Длина заброшеной лески, м
	 * @param coil_angle Угол положения катушки, градусы
	 * @param tackle_angle_xy Угол удочки в плоскости XY, градусы
	 * @param tackle_angle_yz Угол удочки в плоскости YZ, градусы
	 * @param tension Натяжение лески, значение от 0 до 100, %
	 * @param pulse Пульс рыбака, ударов в минуту
	 * @param x Координата изображения X
	 * @param y Координата изображения Y
	 * @param z Координата изображения Z
	 */
	public MetaData(long time, double length, double coil_angle, double tackle_angle_xy, double tackle_angle_yz, double tension, double pulse, double x, double y, double z)
	{
		this.time = time;
		this.length = length;
		this.coil_angle = coil_angle;
		this.tackle_angle_xy = tackle_angle_xy;
		this.tackle_angle_yz=tackle_angle_yz;
		this.tension = tension;
		this.pulse = pulse;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
}
