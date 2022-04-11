/**
 * 
 */
package main.com.carService.form_settings;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface Iform_settingsAppService {

	public List<form_settings> getAll();
	public List<form_settings> getAllByType(int type);
	public form_settings getByName(String name);
	public form_settings addform_settings(form_settings data);
	public form_settings getById(int id);
	public boolean delete(form_settings data)throws Exception;
}
