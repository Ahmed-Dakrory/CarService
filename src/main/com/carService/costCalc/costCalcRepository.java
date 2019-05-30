/**
 * 
 */
package main.com.carService.costCalc;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface costCalcRepository {

	public List<costCalc> getAll();
	public List<costCalc> getAllByParentId(int idParent);
	public List<costCalc> getAllByShipperOfParentId(int idParent);
	public List<costCalc> getAllByUserOfParentOfParentId(int idParent);
	public costCalc addcostCalc(costCalc data);
	public costCalc getById(int id);
	public boolean delete(costCalc data)throws Exception;
}
