package lk.cipher.radio;

import java.io.IOException;
import java.util.ArrayList;


import lk.cipher.http.HttpHelper;
import lk.cipher.persist.Channel;
import lk.cipher.xml.MyXMLParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class SriLankaRadio extends Activity implements OnPreparedListener, OnBufferingUpdateListener, OnErrorListener {
	
	private MediaPlayer mp;
	private int buffer_percent = 0;
	private String current_url= "";
	private String current_channel="";
	private ArrayList<Channel> ch =null;
	public final static long CONNECTION_TIMEOUT =30;
	
	private static final int SRI_LANKA_RADIO_LIVE = 1;
	NotificationManager mNotificationManager;
	Notification notification;
	
	private ConnectivityManager connMgr;
	private android.net.NetworkInfo network_info;
	private boolean blnNetworkAccess=false;

	
	 private String[] urls;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        checkNetwork();
        
        if(blnNetworkAccess) {
        	Log.i("Info","Internet Connection OK");
        	T t =new T();
        	t.start();
        } else {
        	showToastFast("No Internet Connection");
        	enableInternet();
        }


        //Adding the gridview
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		
        		
        		if(urls!=null){
        		
        		stopPlay();
        		//current_url = urls[position];
        		switch(position){
        			
        		case 0:
        			if(urls[4]!=null || urls[4]!=""){
        				showToast("E FM");
            			current_url = urls[4];
            			current_channel="E FM";
            			playMedia(urls[4]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 1:
        			if(urls[12]!=null || urls[12]!=""){
        			showToast("Sirasa FM");
        			current_channel="Sirasa FM";
        			current_url = urls[12];
        			playMedia(urls[12]);
        			//showPlayer(v);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			break;
        		case 2:
        			if(urls[7]!=null || urls[7]!=""){
        			showToast("Yes Fm");
        			current_channel="Yes Fm";
        			current_url = urls[7];
        			playMedia(urls[7]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 3:
        			if(urls[6]!=null || urls[6]!=""){
        			showToast("FM Derana");
        			current_channel="FM Derana";
        			current_url = urls[6];
        			playMedia(urls[6]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 4:
        			if(urls[0]!=null || urls[0]!=""){
        			showToast("SLBC Sinhala");
        			current_channel="SLBC Sinhala";
        			current_url = urls[0];
        			playMedia(urls[0]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 5:
        			if(urls[5]!=null || urls[5]!=""){
        			showToast("TNL Radio");
        			current_channel="TNL Radio";
        			current_url = urls[5];
        			playMedia(urls[5]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 6:
        			if(urls[13]!=null || urls[13]!=""){
        			showToast("Youth FM");
        			current_channel="Youth FM";
        			current_url = urls[13];
        			playMedia(urls[13]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 7:
        			if(urls[2]!=null || urls[2]!=""){
        			showToast("City FM");
        			current_url = urls[2];
        			current_channel="City FM";
        			playMedia(urls[2]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 8:
        			if(urls[1]!=null || urls[1]!=""){
        			showToast("SLBC Commercial");
        			playMedia(urls[1]);
        			current_channel="SLBC Commercial";
        			current_url = urls[1];
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 9:
        			if(urls[9]!=null || urls[9]!=""){
        			showToast("Shakthi FM");
        			current_channel="Shakthi FM";
        			current_url = urls[9];
        			playMedia(urls[9]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 10:
        			if(urls[2]!=null || urls[2]!=""){
        			showToast("SLBC Thendral");
        			current_channel="SLBC Thendral";
        			current_url = urls[2];
        			playMedia(urls[2]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        		    //showPlayer(v);
        			break;
        		case 11:
        			if(urls[11]!=null || urls[11]!=""){
        			showToast("Soorian FM");
        			current_channel="Soorian FM";
        			current_url = urls[11];
        			playMedia(urls[11]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 12:
        			if(urls[10]!=null || urls[10]!=""){
        			showToast("Gold FM");
        			current_channel="Gold FM";
        			current_url = urls[10];
        			playMedia(urls[10]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		case 13:
        			if(urls[8]!=null || urls[8]!=""){
        			showToast("Tamil Star FM");
        			current_channel="Tamil Star FM";
        			current_url = urls[8];
        			playMedia(urls[8]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;	
        		case 14:
        			if(urls[14]!=null || urls[14]!=""){
        			showToast("Hiru FM");
        			current_channel="Hiru FM";
        			current_url = urls[14];
        			playMedia(urls[14]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;		
        		default:
        			if(urls[10]!=null || urls[10]!=""){
        			showToast("Gold FM");
        			current_channel="Gold FM";
        			current_url = urls[10];
        			playMedia(urls[10]);
        			}else{
        				showToastFast("Channel Offline");
        			}
        			//showPlayer(v);
        			break;
        		}
        		
        		}else{
        			showToastFast("Channel Offline");
        		}
        		//showPlayer(v);
            }
    
			});

    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu, menu);
		return true;
	}

    
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int x = item.getItemId();
		
		switch(x){
		case R.id.itStop:
			stopPlay();
			break;
		case R.id.itPlay:
			playMedia(current_url);
			break;
		case R.id.itExit:
			//parseChannel();
			//getRequest("http://cipherlk.com/android/slradio/?url=1");
			stopPlay();
			removeNotification();
		    System.exit(1);
			break;
		default:
			startPlay();
			//showToastFast("default");
		}
		return true;
	}
    
	@SuppressWarnings("unused")
	private void getRequest(String url){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try{
            HttpResponse response = client.execute(request);
            Log.i("channel List",HttpHelper.request(response));
        }catch(Exception ex){
            showToast("Loading Channels Failed!");
        }
    }
	
	private void parseChannel(){
		MyXMLParser my = new MyXMLParser();
		ch = my.parseChannelResponse("http://cipherlk.com/android/slradio/?url=1");
		Log.i("no of channels", ch.size()+"");
		
		if(ch!=null){
			urls = new String[ch.size()];
			
			for(int x=0;x<ch.size()-1;x++){
				urls[x]=ch.get(x).getChnl_st_url();
			}
		}
		
		
		
		
		
	} 
	
	class T extends Thread{
		public void run(){
			Log.i("Info","Running in sepreate Thread");
			parseChannel();
		}
	}
	
    @SuppressWarnings("unused")
	private void showPlayer(View v){
    	Intent myIntent = new Intent(v.getContext(), MPlayer.class);
        startActivityForResult(myIntent, 0);
    }
    
    //show the selected radio channel
    private void showToast(String text){
    	Toast.makeText(SriLankaRadio.this, "" + text, Toast.LENGTH_LONG).show();
    }
    
  //show the selected radio channel
    @SuppressWarnings("unused")
	private void showToastFast(String text){
    	Toast.makeText(SriLankaRadio.this, "" + text, Toast.LENGTH_SHORT).show();
    }
    
    //show the selected radio channel
    @SuppressWarnings("unused")
	private void showToast(int res_id){
    	Toast.makeText(SriLankaRadio.this, "" + res_id, Toast.LENGTH_LONG).show();
    }
    
    //show the selected radio channel
    @SuppressWarnings("unused")
	private void showToastFast(int res_id){
    	Toast.makeText(SriLankaRadio.this, "" + res_id, Toast.LENGTH_SHORT).show();
    }
    
    /**
     * 
     * @param url
     * @return
     */
    @SuppressWarnings("unused")
	private boolean canPingHost(String url) {
    	Runtime runtime = Runtime.getRuntime();
    	Process proc;
		try {
			proc = runtime.exec("ping -c 1 "+url);
		//other servers, for example
			proc.waitFor();
    	int exit = proc.exitValue();
    	Log.i("ping exit value",proc.exitValue()+"");
    	Log.i("ping",proc.toString());
    	if (exit == 0) { // normal exit
    	   return true;
    	} else { // abnormal exit, so decide that the server is not reachable
    	   return false;
    	}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}  catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

    }

    
    private void stopPlay(){
    	if(mp!=null){
    		mp.stop();
    		mp.reset();
    	}
    }
    
    
    @SuppressWarnings("unused")
	private void pausePlay(){
    	if(mp!=null){
    		mp.pause();
    		String cst= current_channel;
			if(cst!=null || cst!=""){
				pauseNotification(cst);
			}
    		
    	}
    }
    
    private void startPlay(){
    	if(mp!=null){
    		mp.start();
    	}
    }
    
    
    //play the radio channel
    private void playMedia(String url){
    	
	
    	if(mp!=null){
    		mp.stop();
    		mp.reset();
    	}

					try {
						mp = new MediaPlayer();
						
						if(mp!=null){
							mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
							mp.setDataSource(url);
							mp.setOnPreparedListener(this);
							mp.setOnBufferingUpdateListener(this);
							mp.setOnErrorListener(this);
							mp.prepareAsync();
						showNotification(current_channel);
						}
					} catch (IllegalArgumentException e) {
						showToast(e.getMessage());
					} catch (IllegalStateException e) {
						showToast(e.getMessage());
					} catch (IOException e) {
						showToast(e.getMessage());
					}     
					startPlay();
					String cst= current_channel;
					if(cst!=null || cst!=""){
						updateNotification(cst);
					}
    }

    
    
	public void onPrepared(MediaPlayer mp1) {
		mp1.start();
		//showToast("starting ..");
	}

	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		buffer_percent = percent;
		Log.i("buffer",buffer_percent+"");
	}
	
	 public boolean onError(MediaPlayer mp, int what, int extra) {
         StringBuilder sb = new StringBuilder();
   
         	sb.append("Media Player Error: ");
         		switch (what) {
			         case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
			                 sb.append("Not Valid for Progressive Playback");
			                 break;
			         case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
			                 sb.append("Server Died");
			                 break;
			         case MediaPlayer.MEDIA_ERROR_UNKNOWN:
			                 sb.append("Unknown");
			                 break; 
			         default:
			                 //sb.append(" Non standard (");
			                 //sb.append(what);
			                 //sb.append(")");
			        	     //
			        	 
         		}
         			sb.append(" (" + what + ") ");
         			sb.append(extra);
         			Log.i("info",sb.toString());
         			return true;    
	 }
	 
	 @SuppressWarnings("unused")
	private int getBufferPercentage() {
         return buffer_percent;
     }
	 
	 private void removeNotification(){
		 if(mNotificationManager!=null){
			 mNotificationManager.cancel(SRI_LANKA_RADIO_LIVE);
		 }
	 }
	 
	 private void updateNotification(String cur_station){
		 
		    Context context = getApplicationContext();
		    if(context!=null){
		        CharSequence contentTitle = "Sri Lanka Radio Live";
		        CharSequence contentText = "Playing "+cur_station;
		        Intent notificationIntent = new Intent(this, SriLankaRadio.class);
		        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	
		        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		        notification.flags |= Notification.FLAG_ONGOING_EVENT;
		        mNotificationManager.notify(SRI_LANKA_RADIO_LIVE, notification);
		    }
	 }
	 
	 private void pauseNotification(String cur_station){
		 
		    Context context = getApplicationContext();
		    if(context!=null){
		        CharSequence contentTitle = "Sri Lanka Radio Live";
		        CharSequence contentText = "Paused "+cur_station;
		        Intent notificationIntent = new Intent(this, SriLankaRadio.class);
		        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	
		        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		        notification.flags |= Notification.FLAG_ONGOING_EVENT;
		        mNotificationManager.notify(SRI_LANKA_RADIO_LIVE, notification);
		    }
	 }
	 
	 
    private void showNotification(String current_station){
    	
    	String ns = Context.NOTIFICATION_SERVICE;
        mNotificationManager = (NotificationManager) getSystemService(ns);

        if(mNotificationManager!=null){
	        
	        int icon = R.drawable.icon;
	        CharSequence tickerText = "Playing "+ current_station;
	        long when = System.currentTimeMillis();
	
	        notification = new Notification(icon, tickerText, when);
	        
	        if(notification!=null){
	        
		        Context context = getApplicationContext();
		        CharSequence contentTitle = "Sri Lanka Radio Live";
		        CharSequence contentText = "Playing "+current_station;
		        Intent notificationIntent = new Intent(this, SriLankaRadio.class);
		        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		
		        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		        notification.flags |= Notification.FLAG_ONGOING_EVENT;
		        mNotificationManager.notify(SRI_LANKA_RADIO_LIVE, notification);
	        }
        }
    }
    
    /**
     * check network availability
     * @return true if internt access OK
     */
    private boolean HaveNetworkConnection()
    {
        boolean HaveConnectedWifi = false;
        boolean HaveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    HaveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    HaveConnectedMobile = true;
        }
        return HaveConnectedWifi || HaveConnectedMobile;
    }
    
    private void checkNetwork(){
    	connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
    	
    	if(connMgr!=null){
    		network_info = connMgr.getActiveNetworkInfo();
    	}
    	
    	if(network_info != null && network_info.isConnectedOrConnecting()) {
    		blnNetworkAccess = true;
    	}

    }

    private void enableInternet(){
    	ConnectivityManager manager = (ConnectivityManager)getSystemService(SriLankaRadio.CONNECTIVITY_SERVICE);
    	Boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        /*
         * wifi confirm
         */
        Boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if(is3g){
            showToastFast("3G Connection OK");
        }else if(isWifi){
        	 showToastFast("WIFI Connection OK");
        }else{
            
         //Activity transfer to wifi settings
            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }

    }

	
}