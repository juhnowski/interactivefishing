package tv.interactivefishing.context;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;


public class SettingsXMLParser extends DefaultHandler {
	String thisElement = "";

	@Override
	public void startDocument() throws SAXException {
	  System.out.println("Start parse XML settings...");
	  Settings.settings.clear();
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
	  thisElement = qName; 
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
	  thisElement = "";
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		Settings.settings.put(thisElement, new String(ch, start, length));
	  }
	
	@Override
	public void endDocument() {
	  System.out.println("Stop parse XML settings...");
	}
}

