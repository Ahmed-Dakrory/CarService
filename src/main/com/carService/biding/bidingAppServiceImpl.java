/**
 * 
 */
package main.com.carService.biding;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("bidingFacadeImpl")
public class bidingAppServiceImpl implements IbidingAppService{

	@Autowired
	bidingRepository bidingDataRepository;
	
	
	@Override
	public List<biding> getAll() {
		try{
			List<biding> course=bidingDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public biding addbiding(biding data) {
		try{
				biding data2=bidingDataRepository.addbiding(data);
				return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(biding data)throws Exception {
		// TODO Auto-generated method stub
		try{
			bidingDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public biding getById(int id) {
		// TODO Auto-generated method stub
		try{
			biding so=bidingDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	

	@Override
	public biding getByCarIdAnduserIdAndType(int idCar, int idUser,int type) {
		try{
			biding course=bidingDataRepository.getByCarIdAnduserIdAndType(idCar, idUser,type);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<biding> getAllMaxCarBidingsAndType(int type) {
		try{
			List<biding> so=bidingDataRepository.getAllMaxCarBidingsAndType(type);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public biding getByCarIdLessThanFullAmountAndType(int idCar, float amount,int type) {
		try{
			biding course=bidingDataRepository.getByCarIdLessThanFullAmountAndType(idCar, amount,type);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<biding> getAllByuserId(int idUser) {
		try{
			List<biding> course=bidingDataRepository.getAllByuserId(idUser);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public biding getByCarIdandMaxAmountAndType(int idCar,int type) {
		// TODO Auto-generated method stub
				try{
					biding so=bidingDataRepository.getByCarIdandMaxAmountAndType(idCar,type);
					
					return so;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						return null;
					}
	}



	@Override
	public List<biding> getAllByCarId(int idcar) {
		// TODO Auto-generated method stub
		try{
			List<biding> so=bidingDataRepository.getAllByCarId(idcar);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	
	
	
}
		
		

	
		
	


