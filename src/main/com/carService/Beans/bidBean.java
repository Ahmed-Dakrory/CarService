package main.com.carService.Beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import main.com.carService.moneyBox.moneybox;
import main.com.carService.notification.notification;
import main.com.carService.notification.notificationAppServiceImpl;


@ManagedBean(name = "bidBean")
@SessionScoped
public class bidBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 

	private List<moneybox> listOfAllUsersMoneyBox;
	private List<moneybox> selectedlistOfAllUsersMoneyBox;
	
	
	@ManagedProperty(value = "#{notificationFacadeImpl}")
	private notificationAppServiceImpl notificationFacade;

	List<notification> allNotifcationForThisUser;
	List<notification> allNotifcationForThisUserLimited;
	List<notification> allUnReadNotifcationForThisUser;
	@PostConstruct
	public void init() {
		
		refresh();
		
	}
	
	public void refresh(){
		
		listOfAllUsersMoneyBox=loginBean.getMoneyboxDataFacede().getAll();
		

		allNotifcationForThisUser=notificationFacade.getAllByuserId(loginBean.getTheUserOfThisAccount().getId());
	}

	public void refreshNotification() {
		
		allNotifcationForThisUserLimited=notificationFacade.getAllByuserIdAndLimit(loginBean.getTheUserOfThisAccount().getId(),0,30);
		
		allUnReadNotifcationForThisUser=notificationFacade.getAllByuserIdAndState(loginBean.getTheUserOfThisAccount().getId(),false,0,999);
		

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel1");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel2");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel3");
	}
	
	
	
	
	 public void onRowEdit(RowEditEvent event) {
		 

	        moneybox mNew= ((moneybox) event.getObject());
	        
	        loginBean.getMoneyboxDataFacede().addmoneybox(mNew);
	        PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Success',\r\n" + 
					"			text: 'Your data has been saved.',\r\n" + 
					"			type: 'success'\r\n" + 
					"		});");
	       
         
    }
	
	
	
	
	
	public void readNotification() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 Integer notificationId = Integer.valueOf((String) map.get("notificationId"));
		 
		notification notificationNew=notificationFacade.getById(notificationId);
		notificationNew.setReaded(true);
		notificationFacade.addnotification(notificationNew);
	}
	
	
	
	
	
	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<moneybox> getListOfAllUsersMoneyBox() {
		return listOfAllUsersMoneyBox;
	}

	public void setListOfAllUsersMoneyBox(List<moneybox> listOfAllUsersMoneyBox) {
		this.listOfAllUsersMoneyBox = listOfAllUsersMoneyBox;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<moneybox> getSelectedlistOfAllUsersMoneyBox() {
		return selectedlistOfAllUsersMoneyBox;
	}

	public void setSelectedlistOfAllUsersMoneyBox(List<moneybox> selectedlistOfAllUsersMoneyBox) {
		this.selectedlistOfAllUsersMoneyBox = selectedlistOfAllUsersMoneyBox;
	}

	public notificationAppServiceImpl getNotificationFacade() {
		return notificationFacade;
	}

	public void setNotificationFacade(notificationAppServiceImpl notificationFacade) {
		this.notificationFacade = notificationFacade;
	}

	public List<notification> getAllNotifcationForThisUser() {
		return allNotifcationForThisUser;
	}

	public void setAllNotifcationForThisUser(List<notification> allNotifcationForThisUser) {
		this.allNotifcationForThisUser = allNotifcationForThisUser;
	}

	public List<notification> getAllNotifcationForThisUserLimited() {
		return allNotifcationForThisUserLimited;
	}

	public void setAllNotifcationForThisUserLimited(List<notification> allNotifcationForThisUserLimited) {
		this.allNotifcationForThisUserLimited = allNotifcationForThisUserLimited;
	}

	public List<notification> getAllUnReadNotifcationForThisUser() {
		return allUnReadNotifcationForThisUser;
	}

	public void setAllUnReadNotifcationForThisUser(List<notification> allUnReadNotifcationForThisUser) {
		this.allUnReadNotifcationForThisUser = allUnReadNotifcationForThisUser;
	}
	
	
	
	
	
}
