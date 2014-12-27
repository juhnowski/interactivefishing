package tv.interactivefishing.video.protocol;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 * Класс обработчик таймкода
 * @author ilya
 *
 */
public class MetaDataXMLParser extends DefaultHandler {
	String thisElement = "";
	MetaData data;
	
	
	@Override
	public void startDocument() throws SAXException {
	  System.out.println("Start parse XML timecode...");
	  MetaDataReader.mdlist.clear();
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException 
	{
	  thisElement = qName;
	  if(qName.toString().equals("time"))
	  {
		  data = new MetaData(Long.parseLong(atts.getValue("value")));
	  }
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
	  thisElement = "";
	  if(qName.toString().equals("time"))
	  {
		  MetaDataReader.mdlist.put(data.time, data);
	  }
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		if (thisElement.equals("length")) 
		{
			data.length = Double.parseDouble(new String(ch, start, length));
			return;
		}
		
		if (thisElement.equals("coil_angle")) 
		{
			data.coil_angle = Double.parseDouble(new String(ch, start, length));
			return;
		}
		
		if (thisElement.equals("tackle_angle_xy")) 
		{
			data.tackle_angle_xy = Double.parseDouble(new String(ch, start, length));
			return;
		}
		
		if (thisElement.equals("tackle_angle_yz")) 
		{
			data.tackle_angle_yz = Double.parseDouble(new String(ch, start, length));
			return;
		}

		if (thisElement.equals("pulse")) 
		{
			data.pulse = Double.parseDouble(new String(ch, start, length));
			return;
		}
		
		if (thisElement.equals("tension")) 
		{
			data.tension = Double.parseDouble(new String(ch, start, length));
			return;
		}		
		
		if (thisElement.equals("x")) 
		{
			data.x = Double.parseDouble(new String(ch, start, length));
			return;
		}		

		if (thisElement.equals("y")) 
		{
			data.y = Double.parseDouble(new String(ch, start, length));
			return;
		}				

		if (thisElement.equals("z")) 
		{
			data.z = Double.parseDouble(new String(ch, start, length))/10;
			return;
		}					
	  }
	
	@Override
	public void endDocument() {
	  System.out.println("Stop parse XML timecode...");
	  System.out.println(MetaDataReader.mdlist.size() + " rows have been resolved");
	}
}