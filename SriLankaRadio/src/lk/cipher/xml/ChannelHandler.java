package lk.cipher.xml;

import java.util.ArrayList;

import lk.cipher.persist.Channel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class ChannelHandler extends DefaultHandler{

private StringBuffer buffer = new StringBuffer();
    
    private ArrayList<Channel> chList;
    private Channel ch;
 
    
    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
        
    	Log.i("stateing element",localName+" Q -"+qName);
        
        buffer.setLength(0);
        Log.i("buffer size",buffer.length()+"");
        
        if (localName.equals("channel_list")) {
            chList = new ArrayList<Channel>();
        }
        else if (localName.equals("channel")) {
            ch = new Channel();
        }else{
        	
        }

    }
    
    @Override
    public void endElement(String uri, String localName, String qName)throws SAXException {
        
        if (localName.equals("channel")) {
            chList.add(ch);
        }
        else if (localName.equals("id")) {
            ch.setChnl_id(Integer.parseInt(buffer.toString()));
        }
        else if (localName.equals("name")) {
            ch.setChnl_name(buffer.toString());
        }
        else if (localName.equals("strm_url")) {
            ch.setChnl_st_url(buffer.toString());
        }
        else if (localName.equals("site_url")) {
            ch.setChnl_site_url(buffer.toString());
        }
        else if (localName.equals("desc")) {
            ch.setChnl_desc(buffer.toString());
        }else if (localName.equals("img_icon")) {
            ch.setImg_icon((buffer.toString()));
        }
        
    }
    
    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }
        
    public ArrayList<Channel> getChannelList() {
        return chList;
    }
    

}
