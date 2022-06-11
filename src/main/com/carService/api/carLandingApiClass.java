package main.com.carService.api;

import org.springframework.stereotype.Controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import main.com.carService.carLanding.carLanding;
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.myCars.mycars;
import main.com.carService.myCars.mycarsAppServiceImpl;
import main.com.carService.product.product;
import main.com.carService.product.productAppServiceImpl;
@Controller
@RequestMapping("/ApiCarLanding")
public class carLandingApiClass {


	@Inject
	private carLandingAppServiceImpl carLandingFacade;
	

	@Inject
	private productAppServiceImpl productFacade;
	

	@Inject
	private mycarsAppServiceImpl mycarsFacade;
	
	

	
	@RequestMapping(value = "/")
    public ResponseEntity<String> getLogin() {
      return new ResponseEntity<>("",HttpStatus.OK);
    }
	
	
	

	@RequestMapping(value = "/allProduct" ,method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> allProduct(@RequestParam(value="start",required=false) Integer start,
    		@RequestParam(value="length",required=false) Integer length,
    		@RequestParam(value="draw",required=false) Integer draw,
    		@RequestParam(value="search[value]",required=false) String search_value) { 

		
		Gson gson = new Gson();
//			int pageNumber = (start/length + 1);
			List<product> list = productFacade.getAllWithPagination(start, length,search_value);
		
	      JsonArray allCars = new JsonArray();
	      for(int i=0;i<list.size();i++) {
	    	  allCars.add(list.get(i).toJson());
	      }
	      
	      
	      JsonObject obj =new JsonObject();
	      obj.add("data", allCars);
	      obj.addProperty("draw", draw);
	      obj.addProperty("recordsTotal", productFacade.getAllCount());
	      obj.addProperty("recordsFiltered", productFacade.getAllCount());
	    	return new ResponseEntity<>(gson.toJson(obj), HttpStatus.CREATED); 
	

     
    }
	
	
	
	@RequestMapping(value = "/all" ,method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> all(@RequestParam(value="start",required=false) Integer start,
    		@RequestParam(value="length",required=false) Integer length,
    		@RequestParam(value="draw",required=false) Integer draw,
    		@RequestParam(value="search[value]",required=false) String search_value) {

		Gson gson = new Gson();
//			int pageNumber = (start/length + 1);
			List<carLanding> list = carLandingFacade.getAllWithPagination(start, length,search_value);
		
	      JsonArray allCars = new JsonArray();
	      for(int i=0;i<list.size();i++) {
	    	  allCars.add(list.get(i).toJson());
	      }
	      
	      
	      JsonObject obj =new JsonObject();
	      obj.add("data", allCars);
	      obj.addProperty("draw", draw);
	      obj.addProperty("recordsTotal", carLandingFacade.getAllCount());
	      obj.addProperty("recordsFiltered", carLandingFacade.getAllCount());
	    	return new ResponseEntity<>(gson.toJson(obj), HttpStatus.CREATED); 
	

     
    }
	
	

	@RequestMapping(value = "/allForId" ,method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> allForId(@RequestParam(value="start",required=false) Integer start,
    		@RequestParam(value="length",required=false) Integer length,
    		@RequestParam(value="draw",required=false) Integer draw,
    		@RequestParam(value="id",required=false) Integer id,
    		@RequestParam(value="search[value]",required=false) String search_value) {

		Gson gson = new Gson();
//			int pageNumber = (start/length + 1);
			List<carLanding> list = carLandingFacade.getAllWithPaginationWithId(start, length,search_value,id);
		
	      JsonArray allCars = new JsonArray();
	      for(int i=0;i<list.size();i++) {
	    	  allCars.add(list.get(i).toJson());
	      }
	      
	      
	      JsonObject obj =new JsonObject();
	      obj.add("data", allCars);
	      obj.addProperty("draw", draw);
	      obj.addProperty("recordsTotal", carLandingFacade.getAllCount(id));
	      obj.addProperty("recordsFiltered", carLandingFacade.getAllCount(id));
	    	return new ResponseEntity<>(gson.toJson(obj), HttpStatus.CREATED); 
	

     
    }
	
	
	
	@RequestMapping(value = "/allSearch" ,method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> allSearch(@RequestParam(value="start",required=false) Integer start,
    		@RequestParam(value="length",required=false) Integer length,
    		@RequestParam(value="draw",required=false) Integer draw,
    		@RequestParam(value="type",required=false) String type,
    		@RequestParam(value="make",required=false) String make,
    		@RequestParam(value="model",required=false) String model,
    		@RequestParam(value="yearStart",required=false) String yearStart,
    		@RequestParam(value="yearEnd",required=false) String yearEnd,
    		@RequestParam(value="auctionType",required=false) String auctionType,
    		@RequestParam(value="search[value]",required=false) String search_value) {

		Gson gson = new Gson();
//			int pageNumber = (start/length + 1);
			List<carLanding> list = carLandingFacade.getAllWithPaginationSearch(start,length,search_value,yearStart,
					 yearEnd,  make,  model,  type,auctionType);
		
	      JsonArray allCars = new JsonArray();
	      for(int i=0;i<list.size();i++) {
	    	  allCars.add(list.get(i).toJson());
	      }
	      
	      
	      JsonObject obj =new JsonObject();
	      obj.add("data", allCars);
	      obj.addProperty("draw", draw);
	      obj.addProperty("recordsTotal", carLandingFacade.getAllCountSearch( search_value,  yearStart,  yearEnd,  make,  model,
	    			 type,auctionType));
	      obj.addProperty("recordsFiltered",  carLandingFacade.getAllCountSearch( search_value,  yearStart,  yearEnd,  make,  model,
	    			 type,auctionType));
	    	return new ResponseEntity<>(gson.toJson(obj), HttpStatus.CREATED); 
	

     
    }
	
	
	@RequestMapping(value = "/isCarInFav" ,method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> allSearch(@RequestParam(value="userId",required=false) Integer userId,
    		@RequestParam(value="carId",required=false) Integer carId) {

		Gson gson = new Gson();

		mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(userId, mycars.TYPE_WATCH_LIST, carId);

		 JsonObject obj =new JsonObject();
		if(watchListCarNew!=null) {
			
		      obj.addProperty("statues", true);
		      obj.addProperty("isExist", true);
		}else {
		      obj.addProperty("statues", true);
		      obj.addProperty("isExist", false);
		}
	      
	     
	    
	    	return new ResponseEntity<>(gson.toJson(obj), HttpStatus.CREATED); 
	

     
    }
	
	@RequestMapping(value = "/moneybox",method = RequestMethod.POST)
    public ResponseEntity<String> getCourse(@RequestParam(value="id",required=false) Integer id) {
    	if(id==null) {

        	return new ResponseEntity<>(" ", HttpStatus.OK);
    	}
    	//moneybox course=moneyBoxFacade.getById(Integer.parseInt(String.valueOf(id)));

    	
    	return new ResponseEntity<>("{\"statue\":\"Ok\"}", HttpStatus.CREATED); 
    }
}
