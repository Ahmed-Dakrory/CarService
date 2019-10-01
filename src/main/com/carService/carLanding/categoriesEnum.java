package main.com.carService.carLanding;

public enum categoriesEnum {

	SMALLCARS("0","SMALL CARS"),SUV("1","SUV"),
	MotorCycle("2","MotorCycles"),JetSkies("3","JetSkies"),
	SnowMobile("4","Snow Mobile"),HeavyDuties("5","Heavy Duties")
	,Korean("6","Korean Cars");
	
	categoriesEnum(String type,String name){
		this.type=type;
		this.name=name;
	}
	
	String type="0";
	
	String name="";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
