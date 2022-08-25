/**
 * 
 */
package main.com.carService.item;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("itemFacadeImpl")
public class itemAppServiceImpl implements IitemAppService{

	@Autowired
	itemRepository itemDataRepository;
	
	
	@Override
	public List<item> getAll() {
		try{
			List<item> course=itemDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public item additem(item data) {
		try{
			item existData = itemDataRepository.getByValue(data.getValue());
			if(existData==null) {
				item data2=itemDataRepository.additem(data);
				return data2;
			}else {
				existData.setDeleted(data.isDeleted());
				itemDataRepository.additem(existData);
				return existData;
			}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(item data)throws Exception {
		// TODO Auto-generated method stub
		try{
			itemDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public item getById(int id) {
		// TODO Auto-generated method stub
		try{
			item so=itemDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	

	@Override
	public List<item> getAllByCarIdAndType(int idCar, int type) {
		try{
			List<item> course=itemDataRepository.getAllByCarIdAndType(idCar, type);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public item getByValue(String url) {
		try{
			item so=itemDataRepository.getByValue(url);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	
	
	
}
		
		

	
		
	


