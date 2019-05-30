/**
 * 
 */
package main.com.carService.costCalc;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("costCalcFacadeImpl")
public class costCalcAppServiceImpl implements IcostCalcAppService{

	@Autowired
	costCalcRepository costCalcDataRepository;
	
	
	@Override
	public List<costCalc> getAll() {
		try{
			List<costCalc> course=costCalcDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public costCalc addcostCalc(costCalc data) {
		try{
			costCalc data2=costCalcDataRepository.addcostCalc(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(costCalc data)throws Exception {
		// TODO Auto-generated method stub
		try{
			costCalcDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public costCalc getById(int id) {
		// TODO Auto-generated method stub
		try{
			costCalc so=costCalcDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<costCalc> getAllByParentId(int idParent) {
		try{
			List<costCalc> course=costCalcDataRepository.getAllByParentId(idParent);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<costCalc> getAllByUserOfParentOfParentId(int idParent) {
	
		try{
			List<costCalc> course=costCalcDataRepository.getAllByUserOfParentOfParentId(idParent);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<costCalc> getAllByShipperOfParentId(int idParent) {
		try{
			List<costCalc> course=costCalcDataRepository.getAllByShipperOfParentId(idParent);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}
	
	
}
		
		

	
		
	


