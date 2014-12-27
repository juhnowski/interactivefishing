package tv.interactivefishing.target;
import java.net.URL;

/**
 * Класс цель.
 * Все значения связанные с экраном и панарамой - int
 * Все значения связанные с моделью - double
 * 								 
 * 0,0  +------------------------------------+ iw,0
 * 		|								     |
 * 		|	sx,sy  +---------+ sx+sw, sy     |
 *      |          |  stx,sty|               |
 * 		|		   | *tx,ty  |               |
 * 		| sx,sy+sh +---------+ sx+sw, sy+sh  |
 * 0,ih	+----------------------------------+ iw,ih
 *                       ^
 *                   tx=0,ty=0 начало отсчета модели
 * @author ilya
 *
 */
public class Target {
//общие дданные
	
	/**
	 * Уникальный идентификатор объекта
	 */
	public int id;
	
	/**
	 * Наименование цели
	 */
	public String name;
	
	/** Описание цели, глубина, рельеф дна
	 */
	public String description;
	
	/**
	 * Флаг цель выбрана, влияет на цвет отрисовки цели
	 * true - цель выбрана
	 */
	public boolean isSelected;
	
	/**
	 * Флаг цель видима
	 * true - цель отображается на экране
	 */
	public boolean isVisible;
	
	/**
	 * Флаг - целеуказатель активен
	 * true - активен, позволяет перейти в состояние selected
	 */
	public boolean isAvailable;
	
//координаты	
	/**
	 * Координата x цели, м
	 */
	public double tx;
	
	/**
	 * Координата y цели, м
	 */
	public double ty;
	
	/**
	 * Координата x цели на экране, пиксели
	 */
	public int stx;
	
	/**
	 * Координата y цели на экране, пиксели
	 */
	public int sty;

	/**
	 * Зум
	 */
	public double sz;
	
	/**
	 * Координата x левого верхнего угла экрана
	 */
	public int sx;
	
	/**
	 * Координата y левого верхнего угла экрана
	 */
	public int sy;
	
	/**
	 * ScreenWidth ширина экрана
	 */
	public int sw;
	
	/**
	 * ScreenHeigth высота экрана
	 */
	public int sh;
	
	/**
	 * ImageHeight высота панарамы
	 */
	public int ih;
	
	/**
	 * ImageWidth ширина панорамы
	 */
	public int iw;

//свойства целеуказателя	
	/**
	 * AimBigHeght высота большого эллипса целеуказателя, м
	 */
	public double abh;

	/**
	 * AimBigWidth ширина большого эллипса целеуказателя, м
	 */
	public double abw;
	
	/**
	 * AimSmallHeght высота малого эллипса целеуказателя, м
	 */
	public double ash;

	/**
	 * AimSmallWidth ширина малого эллипса целеуказателя, м
	 */
	public double asw;
	
	/**
	 * AimBigHeght высота большого эллипса целеуказателя, пиксели
	 */
	public double sabh;

	/**
	 * AimBigWidth ширина большого эллипса целеуказателя, пиксели
	 */
	public double sabw;
	
	/**
	 * AimSmallHeght высота малого эллипса целеуказателя, пиксели
	 */
	public double sash;

	/**
	 * AimSmallWidth ширина малого эллипса целеуказателя, пиксели
	 */
	public double sasw;
	
	/**
	 * AimBigRadius - большой радиус целеуказателя, м 
	 * Нужен для расчета AimBigHeght и AimBigWidth
	 */
	public double abr;

	/**
	 * AimSmallRadius - малый радиус целеуказателя, м 
	 * Нужен для расчета AimSmallHeght и AimSmallWidth
	 */
	public double asr;
	
//свойства камеры
	/**
	 * высота расположения камеры, м
	 */
	public double h;
	
	/**
	 * дистанция до цели, м
	 */
	public double D;
	
	/**
	 * угол наклона камеры, рад
	 * спсособ приведения из градусов: Math.PI*значение/180 
	 */
	public double theta;
	
	/**
	 * условное расстояние от камеры до плоскости экрана, м
	 */
	public double f;

	/**
	 * угол обзора камеры, рад
	 * спсособ приведения из градусов: Math.PI*значение/180
	 * 
	 */
	public double gama_k;
	
//интерактивные свойства
	/**
	 * URL видеоролика
	 */
	public URL video;
	
	/**
	 * Продолжительность видео, связано с невозможностью узнать конец ролика
	 */
	public int video_duration;
	
	/**
	 * VideoScreenX - координата X левого верхнего угла экрана, на который накладывается видео
	 */
	public int vsx;

	/**
	 * VideoScreenY - координата Y левого верхнего угла экрана, на который накладывается видео
	 */	
	public int vsy;
	
	/**
	 * VideoScreenZoom - зум экрана на который накладывается видео
	 */
	public double vsz;
	
	/**
	 * Пееменная показывает результат заброса в эту цель
	 * true - будет поймана рыба
	 */
	public boolean hasFish;
	
	/**
	 * Mass Fish - масса рыбы, если hasFish = false mFish=0
	 */
	public double mFish;
	
	/**
	 * Метод расчета параметров объекта
	 */
	public void refresh()
	{
		
	}
	
	/**
	 * Метод инициализации
	 */
	public void init()
	{
		
	}
}
