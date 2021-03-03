package main.com.carService.loginNeeds;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import main.com.carService.moneyBox.moneybox;
import main.com.carService.moneyBox.moneyboxAppServiceImpl;
import main.com.carService.moneyBox.moneyboxConfig;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_details;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_details.depositeTypes;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_detailsAppServiceImpl;
import main.com.carService.security.AuthenticationService;


@ManagedBean(name = "loginBean")
@SessionScoped
public class loginBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715784400190397743L;
	private boolean isLoggedIn;
	private String emailOfUserLoggedIn;
	private String passwordOfUserLoggedIn;
	private String passwordConfirm;
	private user theUserOfThisAccount;
	private int type;
	
	private Integer amountToPayThroughPayPal = 0;
	

	@ManagedProperty(value = "#{userFacadeImpl}")
	private userAppServiceImpl userDataFacede; 
	
	
	@ManagedProperty(value = "#{moneyboxFacadeImpl}")
	public moneyboxAppServiceImpl moneyboxDataFacede; 
	
	
	@ManagedProperty(value = "#{moneybox_transaction_detailsFacadeImpl}")
	private moneybox_transaction_detailsAppServiceImpl moneybox_transaction_detailsDataFacede; 
	

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;
	
	
	public moneybox thisAccountMoneyBox;
	private List<moneybox_transaction_details> mymoneyTransactions;
	
	@PostConstruct
	public void init() {
		isLoggedIn=false;
		theUserOfThisAccount=new user();
		thisAccountMoneyBox=new moneybox();
		mymoneyTransactions=new ArrayList<moneybox_transaction_details>();
		
	}
	
	public void refresh(){
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequest();
		
		try{
			Integer id=Integer.parseInt(origRequest.getParameterValues("id")[0]);
				if(id!=null){
					user user=userDataFacede.getById(id);
					user.setActive(true);
					userDataFacede.adduser(user);
					
				}
			}
		catch(Exception ex){
			 
		}
	}
	
	
	public void reloadedParametersAndPanelRefresh() {
if(isLoggedIn) {
		thisAccountMoneyBox=new moneybox();
		mymoneyTransactions=new ArrayList<moneybox_transaction_details>();
		thisAccountMoneyBox = moneyboxDataFacede.getByUserId(theUserOfThisAccount.getId());
		if(thisAccountMoneyBox==null) {
			thisAccountMoneyBox=new moneybox();
			mymoneyTransactions=new ArrayList<moneybox_transaction_details>();
		}else {
			mymoneyTransactions=moneybox_transaction_detailsDataFacede.getAllByUserMoneyBoxId(thisAccountMoneyBox.getId(), 0, 6);
		}
		
		
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel");
}
	}
	
	
	public void saveBankAccountDetails() {
		
		moneyboxDataFacede.addmoneybox(thisAccountMoneyBox);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your data has been changed.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
	}
	
	
	public void activateMyAccount() {
		
		if(thisAccountMoneyBox!=null) {
			if(thisAccountMoneyBox.getDepositedMoney()>=400) {
				//You can activate the account
				thisAccountMoneyBox.setActive(true);
				moneyboxDataFacede.addmoneybox(thisAccountMoneyBox);
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Success',\r\n" + 
						"			text: 'Your Account has been changed.',\r\n" + 
						"			type: 'success'\r\n" + 
						"		});");
			}else {
				
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Check this ',\r\n" + 
						"			text: 'Please Make sure that your account has more than $400',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
			}
			

			 reloadedParametersAndPanelRefresh();
		}
		
	}
	public void addThisAmountToMyAccount(){
		 FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 Integer amount = Integer.valueOf((String) map.get("paymentAmount"));
		 
		 moneyboxConfig.depositeMoney(amount, theUserOfThisAccount, userDataFacede, moneyboxDataFacede, moneybox_transaction_detailsDataFacede);
		 
		 reloadedParametersAndPanelRefresh();

			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
			
			
		 PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Success',\r\n" + 
					"			text: 'Your Money Has Been Deposite, for Any Inquiry Sent us over Email.',\r\n" + 
					"			type: 'success'\r\n" + 
					"		});");
		 
		 
	}
	public String logOut(){

		emailOfUserLoggedIn="";
		passwordOfUserLoggedIn="";
		authenticationService.logout();
		theUserOfThisAccount=new user();
		thisAccountMoneyBox=new moneybox();
		isLoggedIn=false;
		System.out.println("");
		return "/pages/public/index.jsf?faces-redirect=true";
	}
	public void login(){

		 String hashedPassword= new  Md5PasswordEncoder().encodePassword(passwordOfUserLoggedIn,emailOfUserLoggedIn);

		theUserOfThisAccount = userDataFacede.getByEmailAndPassword(emailOfUserLoggedIn,hashedPassword);

		if(theUserOfThisAccount!=null){
			isLoggedIn=true;
			
		}else{
			isLoggedIn=false;
			theUserOfThisAccount=new user();
			wrongPassword();
		}
		if(isLoggedIn){
			

			
						boolean success = authenticationService.autoLogin(theUserOfThisAccount.getEmail(), passwordOfUserLoggedIn);
						if (success) {
							thisAccountMoneyBox = moneyboxDataFacede.getByUserId(theUserOfThisAccount.getId());
							if(thisAccountMoneyBox==null) {
								thisAccountMoneyBox=new moneybox();
							}else {
								mymoneyTransactions=moneybox_transaction_detailsDataFacede.getAllByUserMoneyBoxId(thisAccountMoneyBox.getId(), 0, 6);
							}
								FacesContext.getCurrentInstance().getExternalContext()
											.getSessionMap().put("resetMenu", true);
									

					
//				PrimeFaces.current().executeScript("location.reload();");
								try {
									FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/userData/moneyBox.jsf?faces-redirect=true");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			
							}
		}else{
			

			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("FormMain");
		}
		
	}
	
	
	
	public void loginAfterReg(){

		 String hashedPassword= new  Md5PasswordEncoder().encodePassword(passwordOfUserLoggedIn,emailOfUserLoggedIn);

		theUserOfThisAccount = userDataFacede.getByEmailAndPassword(emailOfUserLoggedIn,hashedPassword);

		if(theUserOfThisAccount!=null){
			isLoggedIn=true;
			
		}else{
			isLoggedIn=false;
			theUserOfThisAccount=new user();
			wrongPassword();
		}
		if(isLoggedIn){
			

			
						boolean success = authenticationService.autoLogin(theUserOfThisAccount.getEmail(), passwordOfUserLoggedIn);
						if (success) {
							thisAccountMoneyBox = moneyboxDataFacede.getByUserId(theUserOfThisAccount.getId());
							if(thisAccountMoneyBox==null) {
								thisAccountMoneyBox=new moneybox();
							}else {
								mymoneyTransactions=moneybox_transaction_detailsDataFacede.getAllByUserMoneyBoxId(thisAccountMoneyBox.getId(), 0, 6);
							}
								FacesContext.getCurrentInstance().getExternalContext()
											.getSessionMap().put("resetMenu", true);
									

								try {
									FacesContext.getCurrentInstance()
									   .getExternalContext().redirect("/");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
		}else{
			

			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("FormMain");
		}
		
	}
	
	
	public void createNewUser(int role) {
		System.out.println("Ahmed Dakrory");
		theUserOfThisAccount.setRole(role);
		theUserOfThisAccount.setActive(true);
		if(checkCredentialsValid(theUserOfThisAccount)) {

			theUserOfThisAccount.setDate(Calendar.getInstance());
			theUserOfThisAccount.setPassword(new  Md5PasswordEncoder().encodePassword(passwordConfirm,theUserOfThisAccount.getEmail()));
			userDataFacede.adduser(theUserOfThisAccount);
			
			thisAccountMoneyBox = new moneybox();
			thisAccountMoneyBox.setActive(false);
			thisAccountMoneyBox.setDepositedMoney(0);
			thisAccountMoneyBox.setUserId(theUserOfThisAccount);
			thisAccountMoneyBox.setTotalUsed(0);
			moneyboxDataFacede.addmoneybox(thisAccountMoneyBox);
			
			
			emailOfUserLoggedIn=theUserOfThisAccount.getEmail();
			loginAfterReg();
			
			
		}
	}
	

private boolean checkCredentialsValid(user theUserOfThisAccount2) {
		// TODO Auto-generated method stub
	
	user userToCheckMail =userDataFacede.getByEmail(theUserOfThisAccount2.getEmail());
	if(userToCheckMail!=null) {
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Credentials!',\r\n" + 
					"			text: 'The Email Is Registered!',\r\n" + 
					"			type: 'error',\r\n" + 
					"			left:\"1%\"\r\n" + 
					"		});");
			
		
		
		return false;
	}
	
	if(!passwordOfUserLoggedIn.equals(passwordConfirm)) {
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Credentials!',\r\n" + 
				"			text: 'Passwords are not identical',\r\n" + 
				"			type: 'error',\r\n" + 
				"			left:\"1%\"\r\n" + 
				"		});");
		return false;
	}
		return true;
	}

public void updateDataOfUser() {
		
	// TODO Auto-generated method stub
			boolean ok=false;

				
			if(passwordOfUserLoggedIn.equals(passwordConfirm)&&!passwordOfUserLoggedIn.equals("")&&passwordOfUserLoggedIn!=null){
				ok=true;
			}
			
			
			if(ok){
				
					theUserOfThisAccount.setPassword(new  Md5PasswordEncoder().encodePassword(passwordOfUserLoggedIn,theUserOfThisAccount.getEmail()));
					userDataFacede.adduser(theUserOfThisAccount);
					PrimeFaces.current().executeScript("new PNotify({\r\n" + 
							"			title: 'Success',\r\n" + 
							"			text: 'Your data has been changed.',\r\n" + 
							"			type: 'success'\r\n" + 
							"		});");
				
				
			}else{
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Check this ',\r\n" + 
						"			text: 'Please Make sure that the Passwords are the same and not empty!',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
				
				
			}
	}
	
	
   
	public void wrongPassword(){
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'wrong Credentials!',\r\n" + 
				"			text: 'Please try Again!',\r\n" + 
				"			type: 'error',\r\n" + 
				"			left:\"1%\"\r\n" + 
				"		});");
		
	
	}
	
	
	
	public depositeTypes getTransactionType(int type) {
        return depositeTypes.values()[type];
    }
	
	

	
	public String getTheStatueOfLoginMenu(){
		if(isLoggedIn){
			return "inherit";
		}
		return "none";
	}
	
	
	
	
	
	public String getTheStatueMenuMain(){
		if(isLoggedIn){
			return "none";
		}
		return "block";
	}

	

	/*
	 * the start of getting and setting method
	 */
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getEmailOfUserLoggedIn() {
		return emailOfUserLoggedIn;
	}

	public void setEmailOfUserLoggedIn(String emailOfUserLoggedIn) {
		this.emailOfUserLoggedIn = emailOfUserLoggedIn;
	}

	public String getPasswordOfUserLoggedIn() {
		return passwordOfUserLoggedIn;
	}

	public void setPasswordOfUserLoggedIn(String passwordOfUserLoggedIn) {
		this.passwordOfUserLoggedIn = passwordOfUserLoggedIn;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public user getTheUserOfThisAccount() {
		return theUserOfThisAccount;
	}

	public void setTheUserOfThisAccount(user theUserOfThisAccount) {
		this.theUserOfThisAccount = theUserOfThisAccount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public userAppServiceImpl getUserDataFacede() {
		return userDataFacede;
	}

	public void setUserDataFacede(userAppServiceImpl userDataFacede) {
		this.userDataFacede = userDataFacede;
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public moneyboxAppServiceImpl getMoneyboxDataFacede() {
		return moneyboxDataFacede;
	}

	public void setMoneyboxDataFacede(moneyboxAppServiceImpl moneyboxDataFacede) {
		this.moneyboxDataFacede = moneyboxDataFacede;
	}

	public moneybox_transaction_detailsAppServiceImpl getMoneybox_transaction_detailsDataFacede() {
		return moneybox_transaction_detailsDataFacede;
	}

	public void setMoneybox_transaction_detailsDataFacede(
			moneybox_transaction_detailsAppServiceImpl moneybox_transaction_detailsDataFacede) {
		this.moneybox_transaction_detailsDataFacede = moneybox_transaction_detailsDataFacede;
	}

	public moneybox getThisAccountMoneyBox() {
		return thisAccountMoneyBox;
	}

	public void setThisAccountMoneyBox(moneybox thisAccountMoneyBox) {
		this.thisAccountMoneyBox = thisAccountMoneyBox;
	}

	public List<moneybox_transaction_details> getMymoneyTransactions() {
		return mymoneyTransactions;
	}

	public void setMymoneyTransactions(List<moneybox_transaction_details> mymoneyTransactions) {
		this.mymoneyTransactions = mymoneyTransactions;
	}

	public Integer getAmountToPayThroughPayPal() {
		return amountToPayThroughPayPal;
	}

	public void setAmountToPayThroughPayPal(Integer amountToPayThroughPayPal) {
		this.amountToPayThroughPayPal = amountToPayThroughPayPal;
	}
	
	
	
	
	
	

	
}
