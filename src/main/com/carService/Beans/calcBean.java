package main.com.carService.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import main.com.carService.costCalc.transportfee;
import main.com.carService.costCalc.transportfeeAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.shipper.shipper;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.vendor.vendor;
import main.com.carService.vendor.vendorAppServiceImpl;


@ManagedBean(name = "calcBean")
@SessionScoped
public class calcBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 


	@ManagedProperty(value = "#{transportfeeFacadeImpl}")
	private transportfeeAppServiceImpl transportfeeFacade;
	
	

	private List<transportfee> allLocation;
	private List<transportfee> allCity;
	private List<transportfee> allState;
	
	private transportfee selectedTansportFees;
	
	
	@PostConstruct
	public void init() {
		
		refresh();
		
		
	}
	
	public void refresh(){
		allLocation = transportfeeFacade.getAllGroupsOfLocation();
		allCity = transportfeeFacade.getAllGroupsOfCity();
		allState = transportfeeFacade.getAllGroupsOfstate();
	
	}

	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}

	public transportfeeAppServiceImpl getTransportfeeFacade() {
		return transportfeeFacade;
	}

	public void setTransportfeeFacade(transportfeeAppServiceImpl transportfeeFacade) {
		this.transportfeeFacade = transportfeeFacade;
	}

	public List<transportfee> getAllLocation() {
		return allLocation;
	}

	public void setAllLocation(List<transportfee> allLocation) {
		this.allLocation = allLocation;
	}

	public List<transportfee> getAllCity() {
		return allCity;
	}

	public void setAllCity(List<transportfee> allCity) {
		this.allCity = allCity;
	}

	public List<transportfee> getAllState() {
		return allState;
	}

	public void setAllState(List<transportfee> allState) {
		this.allState = allState;
	}

	public transportfee getSelectedTansportFees() {
		return selectedTansportFees;
	}

	public void setSelectedTansportFees(transportfee selectedTansportFees) {
		this.selectedTansportFees = selectedTansportFees;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
