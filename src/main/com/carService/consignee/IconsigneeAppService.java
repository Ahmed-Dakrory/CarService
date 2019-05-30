/**
 * 
 */
package main.com.carService.consignee;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IconsigneeAppService {

	public List<consignee> getAll();
	public List<consignee> getAllByParentId(int idShipperParent);
	public List<consignee> getAllByParentOfParentId(int idUserParent);
	public consignee addconsignee(consignee data);
	public consignee getById(int id);
	public consignee getByUserId(int id);
	public boolean delete(consignee data)throws Exception;
}
