package be.butskri.commons.util;

import com.thoughtworks.xstream.XStream;

public class ObjectUtils {
	
	private XStream xstream = new XStream();
	
	public String convertToString(Object obj) {
		String result = null;
		
		if (obj != null) {
			result = xstream.toXML(obj);
		}
		return result;
	}

}
