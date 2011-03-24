package lk.cipher.xml;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lk.cipher.persist.Channel;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

public class MyXMLParser {

	private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
			        SAXParserFactory factory = SAXParserFactory.newInstance();
			        // create a parser
			        SAXParser parser = factory.newSAXParser();
			        Log.i("info ","parser created");
			        // create the reader (scanner)
			        XMLReader xmlreader = parser.getXMLReader();
			        Log.i("info ","xml reader created");
			        return xmlreader;
	}
	
	 public ArrayList<Channel> parseChannelResponse(String xmlpath) {
	        
	        try {
	            
	            XMLReader xmlreader = initializeReader();
	            Log.i("info ","xml reader initialized");
	            
	            ChannelHandler channelHandler = new ChannelHandler();
	            
	            // assign our handler
	            xmlreader.setContentHandler(channelHandler);
	            Log.i("info ","content handler set");
	            URL url = new URL(xmlpath);

				InputStream mystream = url.openStream();
				InputSource is = new InputSource(mystream);

	            
	            // perform the synchronous parse	            
	            xmlreader.parse(is);
	            Log.i("info ","input stream set");
	            
	            return channelHandler.getChannelList();
	            
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	        
	    }

}
