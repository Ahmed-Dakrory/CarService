/**
 * 
 */
package main.com.carService.form_settings;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("form_settingsFacadeImpl")
public class form_settingsAppServiceImpl implements Iform_settingsAppService{

	@Autowired
	form_settingsRepository form_settingsDataRepository;
	
	
	@Override
	public List<form_settings> getAll() {
		try{
			List<form_settings> course=form_settingsDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public form_settings addform_settings(form_settings data) {
		try{
			form_settings data2=form_settingsDataRepository.addform_settings(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(form_settings data)throws Exception {
		// TODO Auto-generated method stub
		try{
			form_settingsDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public form_settings getById(int id) {
		// TODO Auto-generated method stub
		try{
			form_settings so=form_settingsDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public form_settings getByName(String name) {
		try{
			form_settings course=form_settingsDataRepository.getByName(name);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}
	
	
}
		
		

	
		
	


