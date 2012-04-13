package be.butskri.commons.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUtils {

	public List<String> getTagWaarden(InputStream inputStream, String tagnaam) {
		GetTagWaardenHandler handler = new GetTagWaardenHandler(tagnaam);
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(inputStream, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler.getGevondenwaarden();
	}

	public static class GetTagWaardenHandler extends DefaultHandler {

		private String tagnaam;
		private StringBuffer waardeInTag;
		private List<String> gevondenwaarden = new ArrayList<String>();

		public GetTagWaardenHandler(String tagnaam) {
			this.tagnaam = tagnaam;
		}

		@Override
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
			if (tagnaam.equals(name)) {
				waardeInTag = new StringBuffer();
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (waardeInTag != null) {
				waardeInTag.append(Arrays.copyOfRange(ch, start, start + length));
			}
		}

		@Override
		public void endElement(String uri, String localName, String name) throws SAXException {
			if (tagnaam.equals(name)) {
				gevondenwaarden.add(waardeInTag.toString());
				waardeInTag = null;
			}
		}

		public List<String> getGevondenwaarden() {
			return gevondenwaarden;
		}
	}
}
