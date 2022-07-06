/**
 * 
 */
package main.com.carService.log_info;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("log_infoFacadeImpl")
public class log_infoAppServiceImpl implements Ilog_infoAppService{

	@Autowired
	log_infoRepository log_infoDataRepository;
	
	
	@Override
	public List<log_info> getAll() {
		try{
			List<log_info> course=log_infoDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public log_info addlog_info(log_info data) {
		try{
			log_info data2=log_infoDataRepository.addlog_info(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(log_info data)throws Exception {
		// TODO Auto-generated method stub
		try{
			log_infoDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public log_info getById(int id) {
		// TODO Auto-generated method stub
		try{
			log_info so=log_infoDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	



	@Override
	public List<log_info> getAllByuserId(int userId) {
		try{
			List<log_info> course=log_infoDataRepository.getAllByuserId(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<log_info> getAllWithPagination(int start, int number, String searchValue, int role, int state,
			int useridAnyint, int col_order_number, String col_ordering) {
		// TODO Auto-generated method stub
		try{
			List<log_info> course=log_infoDataRepository.getAllWithPagination( start,  number,  searchValue,  role,  state,
					useridAnyint,col_order_number,  col_ordering);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public long getAllCountSearch(int start, int number, String searchValue, int role, int state, int useridAny,
			int col_order_number, String col_ordering) {
		try{
			long course=log_infoDataRepository.getAllCountSearch( start,  number,  searchValue,  role,  state,
					 useridAny,col_order_number,  col_ordering);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return 0;
			}
	}



	



	
	
}
		
		

	
		
	


