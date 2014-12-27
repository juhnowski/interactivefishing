package tv.interactivefishing.header;

import java.util.ArrayList;

import tv.interactivefishing.context.Settings;
import tv.interactivefishing.menu.MButton;

/**
 * Управление наживками
 * @author ilya
 *
 */
public class BaitsController {
	/**
	 * Список наживок
	 */
	public static ArrayList<BaitItem> baititems = new ArrayList<BaitItem>();
	
	/**
	 * Количество кнопок меню
	 */
	public static int count;
	
	/**
	 * Фабрика объектов BaitItem
	 */
	public static void getInstances()
	{
		count = Integer.parseInt(Settings.settings.get("bait_item_count"));
		for (int i = 1; i<=count; i++)
		{
			baititems.add(new BaitItem(i));
		}
	}
}
