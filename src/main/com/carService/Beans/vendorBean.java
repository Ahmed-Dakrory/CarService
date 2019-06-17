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

import main.com.carService.loginNeeds.user;
import main.com.carService.shipper.shipper;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.tools.Constants;
import main.com.carService.vendor.vendor;
import main.com.carService.vendor.vendorAppServiceImpl;


@ManagedBean(name = "vendorBean")
@SessionScoped
public class vendorBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 


	@ManagedProperty(value = "#{vendorFacadeImpl}")
	private vendorAppServiceImpl vendorFacade;
	
	@ManagedProperty(value = "#{shipperFacadeImpl}")
	private shipperAppServiceImpl shipperFacade;
	
	private List<vendor> allvendors;
	
	private vendor selectedvendor;
	
	private vendor addNewvendor;
	
	@PostConstruct
	public void init() {
		
		refresh();
		
		selectedvendor=new vendor();
		addNewvendor=new vendor();
		user userId=new user();
		addNewvendor.setUserId(userId);
		shipper shipper_of_this_account=shipperFacade.getByUserId(loginBean.getTheUserOfThisAccount().getId());
		
		addNewvendor.setParentId(shipper_of_this_account);
	}
	
	public void refresh(){
		
		//Get All Vendors For The Shipper
		//
		if(loginBean.getTheUserOfThisAccount().getRole()==user.ROLE_SHIPPER) {
		shipper shipper_of_this_account=shipperFacade.getByUserId(loginBean.getTheUserOfThisAccount().getId());
		
			allvendors=vendorFacade.getAllByParentId(shipper_of_this_account.getId());
		}else if(loginBean.getTheUserOfThisAccount().getRole()==user.ROLE_MAIN) {
			allvendors=vendorFacade.getAllByParentIdForUser(loginBean.getTheUserOfThisAccount().getId());
			
		}else {
			allvendors=new ArrayList<vendor>();
		}
		
		
	}
	
	public void goToAddNewvendor() {
		addNewvendor=new vendor();
		user userId=new user();
		addNewvendor.setUserId(userId);
		shipper shipper_of_this_account=shipperFacade.getByUserId(loginBean.getTheUserOfThisAccount().getId());
		addNewvendor.setParentId(shipper_of_this_account);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/vendor/vendorAddNew.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectvendor(int idvendor) {
		selectedvendor=vendorFacade.getById(idvendor);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/vendor/vendorEdit.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void saveNewvendorData() {
		user userNew= addNewvendor.getUserId();
		
		boolean isValid=checkValidForUser(addNewvendor);
		if(isValid) {
			boolean checkEmail = checkEmailIsExist(addNewvendor.getUserId().getEmail());
			if(checkEmail) {
		userNew.setActive(1);
		userNew.setDate(Calendar.getInstance());
		userNew.setRole(user.ROLE_VENDOR);
		userNew.setPassword(new  Md5PasswordEncoder().encodePassword(userNew.getEmail(),userNew.getEmail()));
		
		loginBean.getUserDataFacede().adduser(userNew);
		shipper shipper_of_this_account=shipperFacade.getByUserId(loginBean.getTheUserOfThisAccount().getId());
		
		addNewvendor.setParentId(shipper_of_this_account);
		vendorFacade.addvendor(addNewvendor);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your vendor has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		Constants.sendEmailNewAccount(addNewvendor.getUserId().getFirstName(),addNewvendor.getUserId().getEmail(),addNewvendor.getUserId().getEmail());
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/vendor/vendorList.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}else {
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Check this ',\r\n" + 
						"			text: 'This email is already Registered',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
			}
		}else {
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Check the Madatory fields',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
	}

	private boolean checkEmailIsExist(String email) {
		// TODO Auto-generated method stub
		
		user the_user=loginBean.getUserDataFacede().getByEmail(email);
		if(the_user!=null) {
			return false;
		}
		return true;
	}

	private boolean checkValidForUser(vendor addNewvendor2) {
		// TODO Auto-generated method stub
		if(addNewvendor2.getUserId().getCompany().equals("")||addNewvendor2.getUserId().getCompany()==null) {
			return false;
		}
		
		if(addNewvendor2.getUserId().getEmail().equals("")||addNewvendor2.getUserId().getEmail()==null) {
			return false;
		}
		
		if(addNewvendor2.getUserId().getFirstName().equals("")||addNewvendor2.getUserId().getFirstName()==null) {
			return false;
		}
		
		if(addNewvendor2.getUserId().getLastName().equals("")||addNewvendor2.getUserId().getLastName()==null) {
			return false;
		}
		
		return true;
	}

	public void cancel() {
		System.out.println("Cancel");
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/vendor/vendorList.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateData() {

		boolean isValid=checkValidForUser(selectedvendor);
		if(isValid) {
		loginBean.getUserDataFacede().adduser(selectedvendor.getUserId());
		
		vendorFacade.addvendor(selectedvendor);

		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your data has been updated.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		
		}else {
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Check the Madatory fields',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
			
		}
	}

	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public vendorAppServiceImpl getvendorFacade() {
		return vendorFacade;
	}

	public void setvendorFacade(vendorAppServiceImpl vendorFacade) {
		this.vendorFacade = vendorFacade;
	}

	public List<vendor> getAllvendors() {
		return allvendors;
	}

	public void setAllvendors(List<vendor> allvendors) {
		this.allvendors = allvendors;
	}

	public vendor getSelectedvendor() {
		return selectedvendor;
	}

	public void setSelectedvendor(vendor selectedvendor) {
		this.selectedvendor = selectedvendor;
	}

	public vendor getAddNewvendor() {
		return addNewvendor;
	}

	public void setAddNewvendor(vendor addNewvendor) {
		this.addNewvendor = addNewvendor;
	}

	public vendorAppServiceImpl getVendorFacade() {
		return vendorFacade;
	}

	public void setVendorFacade(vendorAppServiceImpl vendorFacade) {
		this.vendorFacade = vendorFacade;
	}

	public shipperAppServiceImpl getShipperFacade() {
		return shipperFacade;
	}

	public void setShipperFacade(shipperAppServiceImpl shipperFacade) {
		this.shipperFacade = shipperFacade;
	}
	

	
	
}
