/**
 * 
 */
package main.com.carService.notification;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface notificationRepository {

	public List<notification> getAll();
	public List<notification> getAllByuserId(int id);
	public List<notification> getAllByuserIdAndLimit(int id,int start,int end);
	public List<notification> getAllByuserIdAndState(int id, boolean state,int start,int end);
	public notification addnotification(notification data);
	public notification getById(int id);
	public boolean delete(notification data)throws Exception;
}
