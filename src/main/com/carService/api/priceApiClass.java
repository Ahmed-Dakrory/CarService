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

import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.costCalc.transportfee;
import main.com.carService.costCalc.transportfeeAppServiceImpl;
import main.com.carService.myCars.mycarsAppServiceImpl;

@Controller
@RequestMapping("/ApiPrice")
public class priceApiClass {


	@Inject
	private carLandingAppServiceImpl carLandingFacade;
	

	@Inject
	private transportfeeAppServiceImpl transportfeeFacade;
	

	@Inject
	private mycarsAppServiceImpl mycarsFacade;
	
	

	
	@RequestMapping(value = "/")
    public ResponseEntity<String> getLogin() {
      return new ResponseEntity<>("",HttpStatus.OK);
    }
	
	
	

	@RequestMapping(value = "/allPrices" ,method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> allPrices(@RequestParam(value="start",required=false) Integer start,
    		@RequestParam(value="length",required=false) Integer length,
    		@RequestParam(value="draw",required=false) Integer draw,
    		@RequestParam(value="search[value]",required=false) String search_value) { 

		
		Gson gson = new Gson();
//			int pageNumber = (start/length + 1);
			List<transportfee> list = transportfeeFacade.getAllWithPagination(start, length,search_value);
		
	      JsonArray allObjects = new JsonArray();
	      for(int i=0;i<list.size();i++) {
	    	  allObjects.add(list.get(i).toJson());
	      }
	      
	      
	      JsonObject obj =new JsonObject();
	      obj.add("data", allObjects);
	      obj.addProperty("draw", draw);
	      obj.addProperty("recordsTotal", transportfeeFacade.getAllCount(search_value));
	      obj.addProperty("recordsFiltered", transportfeeFacade.getAllCount(search_value));
	    	return new ResponseEntity<>(gson.toJson(obj), HttpStatus.CREATED); 
	

     
    }




	public carLandingAppServiceImpl getCarLandingFacade() {
		return carLandingFacade;
	}




	public void setCarLandingFacade(carLandingAppServiceImpl carLandingFacade) {
		this.carLandingFacade = carLandingFacade;
	}




	public transportfeeAppServiceImpl getTransportfeeFacade() {
		return transportfeeFacade;
	}




	public void setTransportfeeFacade(transportfeeAppServiceImpl transportfeeFacade) {
		this.transportfeeFacade = transportfeeFacade;
	}




	public mycarsAppServiceImpl getMycarsFacade() {
		return mycarsFacade;
	}




	public void setMycarsFacade(mycarsAppServiceImpl mycarsFacade) {
		this.mycarsFacade = mycarsFacade;
	}
	
	
	
	
	
}
