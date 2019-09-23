/**
 * 
 */
package main.com.carService.invoiceLanding;





import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("invoicelandingFacadeImpl")
public class invoicelandingAppServiceImpl implements IinvoicelandingAppService{

	@Autowired
	invoicelandingRepository invoicelandingDataRepository;
	
	
	@Override
	public List<invoicelanding> getAll() {
		try{
			List<invoicelanding> course=invoicelandingDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public invoicelanding addinvoicelanding(invoicelanding data) {
		try{
			invoicelanding data2=invoicelandingDataRepository.addinvoicelanding(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(invoicelanding data)throws Exception {
		// TODO Auto-generated method stub
		try{
			invoicelandingDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public invoicelanding getById(int id) {
		// TODO Auto-generated method stub
		try{
			invoicelanding so=invoicelandingDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	



	@Override
	public List<invoicelanding> getAllByUserIdCustomer(int userId) {
		try{
			List<invoicelanding> course=invoicelandingDataRepository.getAllByUserIdCustomer(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<invoicelanding> getAllByUserId(int userId) {
		try{
			List<invoicelanding> course=invoicelandingDataRepository.getAllByUserId(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<invoicelanding> getAllByUserIdBetweenDates(int userId, Calendar dateLower, Calendar dateHigh) {
		try{
			List<invoicelanding> course=invoicelandingDataRepository.getAllByUserIdBetweenDates(userId,dateLower, dateHigh);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}
	
	
}
		
		

	
		
	


