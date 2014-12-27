package tv.interactivefishing.target;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import tv.interactivefishing.context.Settings;



/**
 * Управление целеуказателями
 * Перед началом работы с контроллером надо установить значения переменных ScreenWidth, ScreenHeigth
 * @author ilya
 *
 */
public class TargetController {
	
	/**
	 * Список целей
	 */
	public static ArrayList<Target> targets = new ArrayList<Target>();
	
	/**
	 * Счетчик объектов
	 */
	public static int counter = 0;
	
	/**
	 * Ширина экрана, в пикселях
	 */
	public static int ScreenWidth;
	
	/**
	 * Высота экрана, в пикселях
	 */
	public static int ScreenHeigth;
	
	/**
	 * Фабрика объектов Target - Цель, на базе объекта класса Settings - настройки
	 */
	public static void getInstances()
	{
		int cnt = Integer.parseInt(Settings.settings.get("target_count"));
		while(counter < cnt)
		{
			Target t = getInstance();
			
			try {
			t.video = new URL(Settings.settings.get("video_"+counter));
			
			} catch(MalformedURLException ex)
			{
				System.out.println("video_"+counter);
				ex.printStackTrace();
			}
			t.video_duration = Integer.parseInt(Settings.settings.get("video_duration_"+counter));
			t.name = Settings.settings.get("name_"+counter);
			t.description = Settings.settings.get("description_"+counter);
			t.tx = Double.parseDouble(Settings.settings.get("tx_"+counter));
			t.ty = Double.parseDouble(Settings.settings.get("ty_"+counter));
			t.stx = Integer.parseInt(Settings.settings.get("stx_"+counter));
			t.sty = Integer.parseInt(Settings.settings.get("sty_"+counter));
			t.sz = 1;
			t.sx = 0;
			t.sy = 0;
			t.sw = ScreenWidth;
			t.sh = ScreenHeigth;
			
			t.sabh = Integer.parseInt(Settings.settings.get("sabh_"+counter)); //высота большого эллипса целеуказателя, пиксели
			t.sabw = Integer.parseInt(Settings.settings.get("sabw_"+counter)); //ширина большого эллипса целеуказателя, пиксели
			t.sash = Integer.parseInt(Settings.settings.get("sash_"+counter)); //высота малого эллипса целеуказателя, пиксели
			t.sasw = Integer.parseInt(Settings.settings.get("sasw_"+counter)); //ширина малого эллипса целеуказателя, пиксели
			
			t.vsx = Integer.parseInt(Settings.settings.get("vsx_"+counter));  //координата X левого верхнего угла экрана, на который накладывается видео, пиксели
			t.vsy = Integer.parseInt(Settings.settings.get("vsy_"+counter));  //координата Y левого верхнего угла экрана, на который накладывается видео, пиксели
			t.vsz = Double.parseDouble(Settings.settings.get("vsz_"+counter));   //зум экрана на который накладывается видео
			t.hasFish = Boolean.parseBoolean(Settings.settings.get("hasFish_"+counter)); //Пееменная показывает результат заброса в эту цель
			t.mFish = Double.parseDouble(Settings.settings.get("mFish_"+counter));   //масса рыбы в кг
			
			targets.add(t);
		}
	}
	
	/**
	 * Фабрика объектов Target - Цель, используется в редакторе целей
	 * @param sx  координата X левого верхнего угла экрана на панораме, пиксель
	 * @param sy  координата Y левого верхнего угла экрана на панораме, пиксель
	 * @param sz  зум экрана для экранных координат
	 * @param stx координата X центра цели, пиксель
	 * @param sty координата Y центра цели, пиксель
	 * @return
	 */
	private static Target getInstance(int sx, int sy, int sz, int stx, int sty)
	{
		Target t = getInstance();
		
		t.sx = sx;
		t.sy = sy;
		t.sz = sz;
		t.stx = stx;
		t.sty = sty;
		
		//рассчитываем недостающие параметры
		t.refresh();
		
		targets.add(t);
		return t;
	}
	
	private static Target getInstance()
	{
		Target t = new Target();
		t.id = 1+counter++;
		//Считывание и установка настроек
				//Камера
				t.theta = Double.parseDouble(Settings.settings.get("theta"))*Math.PI/180;
				t.f = Double.parseDouble(Settings.settings.get("f"));
				t.gama_k = Double.parseDouble(Settings.settings.get("gama_k"))*Math.PI/180;
				t.h = Double.parseDouble(Settings.settings.get("h"));
				
				//Целеуказатель
				t.abr =Double.parseDouble(Settings.settings.get("abr"));
				t.asr =Double.parseDouble(Settings.settings.get("asr"));

				//Экран
				t.ih = Integer.parseInt(Settings.settings.get("ih")); //высота панарамы
				t.iw = Integer.parseInt(Settings.settings.get("iw")); //ширина панорамы
				
				//Состояние цели
				t.isSelected = false;
				t.isAvailable = true;
				t.isVisible = true;
				
		return t;
	}
	
	/**
	 * Добавить объект Target в список объектов
	 * @param sx  координата X левого верхнего угла экрана на панораме, пиксель
	 * @param sy  координата Y левого верхнего угла экрана на панораме, пиксель
	 * @param sz  зум экрана для экранных координат
	 * @param stx координата X центра цели, пиксель
	 * @param sty координата Y центра цели, пиксель
	 */
	public static void add(int sx, int sy, int sz, int stx, int sty)
	{
		targets.add(getInstance(sx, sy, sz, stx, sty));
		
	}

	/**
	 * Обработчик перемещения экрана. Пересчитываются значения параметров целеказателей из массива
	 * @param sx  координата X левого верхнего угла экрана на панораме, пиксель
	 * @param sy  координата Y левого верхнего угла экрана на панораме, пиксель
	 * @param sz  зум экрана для экранных координат
	 */
	public static void screenChanged(int sx, int sy, int sz)
	{
		
	}
	
	/**
	 * Обработчик перемещения экрана. Пересчитываются значения параметров целеказателей из массива.
	 * Перед началом зуммирования центр экран перемещается в координаты мыши на экране 
	 * @param sx  координата X левого верхнего угла экрана на панораме, пиксель
	 * @param sy  координата Y левого верхнего угла экрана на панораме, пиксель
	 * @param sz  зум экрана для экранных координат
	 * @param msx  координата X мыши при зумировании
	 * @param msy  координата Y мыши при зумировании
	 */
	public static void screenZoomChanged(int sx, int sy, int sz, int msx, int msy)
	{
		
	}	
	
	/**
	 * Функция вызывает проигрывание видеоролика. 
	 * Алшоритм: Из списка получаем объект цель с флагом isSelected = true
	 */
	public static void play()
	{
		
	}
	
	/**
	 * Функция вызывается после завершения показа видеоролика
	 */
	public static void afterPlay()
	{
		
	}

}
