package main.com.carService.carLanding;

public enum categoriesEnum {

	SMALLCARS(0,"SMALL CARS"),SUV(1,"SUV"),
	MotorCycle(2,"MotorCycles"),JetSkies(3,"JetSkies"),
	SnowMobile(4,"Snow Mobile"),HeavyDuties(5,"Heavy Duties");
	
	categoriesEnum(int type,String name){
		this.type=type;
		this.name=name;
	}
	
	int type=0;
	
	String name="";

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
