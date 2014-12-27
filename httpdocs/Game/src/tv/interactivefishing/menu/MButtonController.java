package tv.interactivefishing.menu;

import java.util.ArrayList;

import tv.interactivefishing.context.Settings;

/**
 * Контроллер кнопок меню
 * @author ilya
 *
 */
public class MButtonController {
	
	/**
	 * Список целей
	 */
	public static ArrayList<MButton> buttons = new ArrayList<MButton>();
	
	/**
	 * Количество кнопок меню
	 */
	public static int count;
	
	/**
	 * Фабрика объектов Target - Цель, на базе объекта класса Settings - настройки
	 */
	public static void getInstances()
	{
		count = Integer.parseInt(Settings.settings.get("menu_count"));
		for (int i = 1; i<=count; i++)
		{
			buttons.add(new MButton(i));
		}
	}

}
