package helpers.retrofit.mainFiles;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class copartReturnDataWithImages {

	
	  @SerializedName("returnCodeDesc")
	    public String returnCodeDesc;
	    
	    @SerializedName("data")
	    public data data;
	    
	    
	    
	   
	    
	    
	    public class data{
	    	@SerializedName("lotDetails")
	 	    public lotDetails lotDetails;
	    	
	    	
	    	
	    	@SerializedName("imagesList")
	 	    public ImagesList imagesList;
	    	
	    	
	    	public class lotDetails{
	    		 @SerializedName("gr")
	    		    public String gridRow;
	    		 

	    		 @SerializedName("syn")
	    		    public String saleName;
	    		 

	    		 @SerializedName("vehTypDesc")
	    		    public String vechicleType;
	    		 

	    		 @SerializedName("dd")
	    		    public String damageDescription;
	    		 

	    		 @SerializedName("td")
	    		    public String docType;
	    		 

	    		 @SerializedName("hb")
	    		    public String currentBid;

	    		 @SerializedName("tmtp")
	    		    public String transmissionType;

	    		 @SerializedName("aan")
	    		    public String itemNumber;

	    		 @SerializedName("rc")
	    		    public String estimatedRepair;

	    		 @SerializedName("ord")
	    		    public String odometerDispcription;

	    		 @SerializedName("orr")
	    		    public String odometerNum;
	    		 

	    		 @SerializedName("obc")
	    		    public String odometerChar;
	    		 

	    		 @SerializedName("cy")
	    		    public String cylinder;
	    		 
	    		 @SerializedName("ft")
	    		    public String fuel;
	    		 

	    		 @SerializedName("egn")
	    		    public String engineType;
	    		 

	    		 @SerializedName("mkn")
	    		    public String make;
	    		 

	    		 @SerializedName("bstl")
	    		    public String bodyStyle;
	    		 

	    		 @SerializedName("lmg")
	    		    public String model;
	    		 

	    		 @SerializedName("lcy")
	    		    public String year;
	    		 

	    		 @SerializedName("la")
	    		    public String estimatedRetails;

	    		 @SerializedName("ad")
	    		    public String saleDatetimeInMilliSeconds;

	    		 @SerializedName("at")
	    		    public String timeOfSaleDate;
	    		 

	    		 @SerializedName("clr")
	    		    public String color;
	    		 
	    		 
	    		 
	    		 
	    	}
	    	
	    	 public class ImagesList{
	 	    	
	 	    	 @SerializedName("FULL_IMAGE")
	 	 	    public List<FULL_IMAGE> FULL_IMAGE;
	 	    	
	 	 	    
	 	 	    
	 	 	  public class FULL_IMAGE{
	 	 		 @SerializedName("url")
		 	 	    public String url;
	 	 		 
	 	 		 @SerializedName("imageTypeDescription")
		 	 	    public String imageTypeDescription;
	 	 		 
	 	 	  }
	 	    }
	    	
	    }
	   
}
