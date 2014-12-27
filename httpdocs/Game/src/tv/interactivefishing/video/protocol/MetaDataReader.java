package tv.interactivefishing.video.protocol;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Читает и парсит таймкод из xml файла
 * @author ilya
 *
 */
public class MetaDataReader {

	public static HashMap<Long, MetaData> mdlist = new HashMap<Long, MetaData>(); 
	
	public void read(String fileUri)
	{
		try
		{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		MetaDataXMLParser saxp = new MetaDataXMLParser();

		parser.parse(fileUri, saxp);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		MetaDataReader mdr = new MetaDataReader();
		mdr.read("http://interactivefishing.tv:8080/1.mov.xml");
	}
}
