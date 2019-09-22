/**
 * 
 */
package main.com.carService.notification;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("notificationFacadeImpl")
public class notificationAppServiceImpl implements InotificationAppService{

	@Autowired
	notificationRepository notificationDataRepository;
	
	
	@Override
	public List<notification> getAll() {
		try{
			List<notification> course=notificationDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public notification addnotification(notification data) {
		try{
			notification data2=notificationDataRepository.addnotification(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(notification data)throws Exception {
		// TODO Auto-generated method stub
		try{
			notificationDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public notification getById(int id) {
		// TODO Auto-generated method stub
		try{
			notification so=notificationDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	



	@Override
	public List<notification> getAllByuserId(int userId) {
		try{
			List<notification> course=notificationDataRepository.getAllByuserId(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<notification> getAllByuserIdAndState(int id, boolean state,int start,int end) {
		try{
			List<notification> course=notificationDataRepository.getAllByuserIdAndState(id,state,start,end);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<notification> getAllByuserIdAndLimit(int id, int start, int end) {
		try{
			List<notification> course=notificationDataRepository.getAllByuserIdAndLimit(id,start,end);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}
	
	
}
		
		

	
		
	


