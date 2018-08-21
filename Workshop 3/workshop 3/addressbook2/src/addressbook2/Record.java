package addressbook2;

public class Record {
	
	
	public static final int SIZE = 93;
	private String name;
	
	private String street;
	private String city;
	private String state;
	private String zip;
	
	Record(){
		name="";
		street="";
		city="";
		state="";
		zip="";
	}
	public String getName() {
	
		return name.trim();
	}
	

	public void extractFromString(String total){
		try {
			setName(total.substring(0,32));
			setStreet(total.substring(32,63));
			setCity(total.substring(63,83));
			setState(total.substring(83,86));
      		setZip(total.substring(86,91));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setName(String name) throws Exception {
		if (name.length()>32)
			throw new Exception();
		this.name = name;
	}
	public String getStreet() {
		return street.trim();
	}
	public void setStreet(String street) throws Exception{
		if (street.length()>32)
			throw new Exception();
		this.street = street;
	}
	public String getCity() {
		return city.trim();
	}
	public void setCity(String city) throws Exception{
		if (city.length()>20)
			throw new Exception();
		this.city = city;
	}
	public String getState() {
		return state.trim();
	}
	public void setState(String state) throws Exception{
		if (state.length()>3) //was 2
			throw new Exception();
		this.state = state;
	}
	public String getZip() {
		return zip.trim();
	}
	public void setZip(String zip) throws Exception {
		if (zip.length()>5)
			throw new Exception();
		this.zip = zip;
	}

	private String pad(String str,int totalSize){
		
		String temp=str;
		
		for (int i=0;i<totalSize-str.length();i++){
			temp  = temp + " ";
		}
		return temp;
		
	}
	
	public String toString(){
		return pad(name,32) + pad(street,32)+pad(city,20) + pad(state,2) + pad(zip,5);
	}
	
}
