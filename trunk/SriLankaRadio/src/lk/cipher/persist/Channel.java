package lk.cipher.persist;

public class Channel {

	private int chnl_id;
	private String chnl_name;
	private String chnl_st_url;
	private String chnl_site_url;
	private String chnl_desc;
	private String img_icon;
	
	public Channel(){
		
	}
	
	public Channel(int id,String name){
		this.chnl_id=id;
		this.chnl_name=name;
	}
	
	public String getImg_icon() {
		return img_icon;
	}

	public void setImg_icon(String img_icon) {
		this.img_icon = img_icon;
	}
	
	public int getChnl_id() {
		return chnl_id;
	}

	public void setChnl_id(int chnl_id) {
		this.chnl_id = chnl_id;
	}

	public String getChnl_name() {
		return chnl_name;
	}

	public void setChnl_name(String chnl_name) {
		this.chnl_name = chnl_name;
	}

	public String getChnl_st_url() {
		return chnl_st_url;
	}

	public void setChnl_st_url(String chnl_st_url) {
		this.chnl_st_url = chnl_st_url;
	}

	public String getChnl_site_url() {
		return chnl_site_url;
	}

	public void setChnl_site_url(String chnl_site_url) {
		this.chnl_site_url = chnl_site_url;
	}

	public String getChnl_desc() {
		return chnl_desc;
	}

	public void setChnl_desc(String chnl_desc) {
		this.chnl_desc = chnl_desc;
	}
	
	
}
