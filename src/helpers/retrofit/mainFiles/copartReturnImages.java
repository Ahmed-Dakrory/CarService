package helpers.retrofit.mainFiles;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class copartReturnImages {

	
	  @SerializedName("objectId")
	    public String objectId;
	    
	  @SerializedName("imgCount")
	    public String imgCount;
	  
	  @SerializedName("lotImages")
	    public List<lotImages> lotImages;
	    
	    
	    public class lotImages{
	    	@SerializedName("sequence")
		    public String sequence;
		  
	    	@SerializedName("link")
		    public List<link> link;
		    
		    public class link{
		    	@SerializedName("url")
			    public String url;
			  
		    	@SerializedName("isThumbNail")
			    public boolean isThumbNail;
		    	
		    	@SerializedName("isHdImage")
			    public String isHdImage;
			    
			    
		    }
	    }
	   
	    
}
