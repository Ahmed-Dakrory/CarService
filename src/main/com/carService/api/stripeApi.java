package main.com.carService.api;

import org.springframework.stereotype.Controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.google.gson.Gson;

import flexjson.JSONSerializer;
import main.com.carService.moneyBox.moneybox;
import main.com.carService.moneyBox.moneyboxAppServiceImpl;
@Controller
@RequestMapping("/Api")
public class stripeApi {

	
	@Inject
	private moneyboxAppServiceImpl moneyBoxFacade;
	
	
	@RequestMapping(value = "/")
    public ResponseEntity<String> getLogin() {
      return new ResponseEntity<>("",HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/stripe" ,method = RequestMethod.GET)
    public ResponseEntity<String> stripe(@RequestParam(value="type",required=false) String type,
    		@RequestParam(value="moneyBoxId",required=false) Integer moneyBoxId,
    		@RequestParam(value="amount",required=false) Integer amount,
    		@RequestParam(value="name",required=false) String name) {

		Gson gson = new Gson();
		// This is your real test secret API key.
	    Stripe.apiKey = "sk_live_u9fOW3T0BCwIGOXGG99PV2au";

	    if(type==null) {
	    	type="";
	    }
		final String YOUR_DOMAIN = "https://caromotolibya.com";
        String RemainOfURL = "/pages/secured/userData/moneyBox"+String.valueOf(type)+".jsf?faces-redirect=true&moneyBoxId="+String.valueOf(moneyBoxId)+"&type="+String.valueOf(type)+"&amount="+String.valueOf(amount);
		SessionCreateParams params =
          SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(YOUR_DOMAIN + RemainOfURL)
            .setCancelUrl(YOUR_DOMAIN + "/cancel.html")
            .addLineItem(
              SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(
                  SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency("usd")
                    .setUnitAmount((long) (amount*100))
                    .setProductData(
                      SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(name)
                        .build())
                    .build())
                .build())
            .build();
      Session session;
	try {
		session = Session.create(params);
		 HashMap<String, String> responseData = new HashMap<String, String>();
	      responseData.put("id", session.getId());
//	      return gson.toJson(responseData);
	      System.out.println(session.getId());
	    	return new ResponseEntity<>("{\"id\":\""+String.valueOf(session.getId())+"\"}", HttpStatus.CREATED); 
	} catch (StripeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    System.out.println("Error: "+String.valueOf(e));
	}

    System.out.println("Error");
	return new ResponseEntity<>("{\"statue\":\"Error\"}", HttpStatus.CREATED); 
     
//      return new ResponseEntity<>("",HttpStatus.OK);
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
