/**
 * 
 */
package main.com.carService.log_info;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface log_infoRepository {

	public List<log_info> getAll();
	public List<log_info> getAllByuserId(int id);
	public List<log_info> getAllWithPagination(int start, int number,String searchValue,int role,int state,int useridAnyint ,int col_order_number, String col_ordering);
	public long getAllCountSearch(int start, int number,String searchValue,int role,int state,int useridAny ,int col_order_number, String col_ordering);
	
	public log_info addlog_info(log_info data);
	public log_info getById(int id);
	public boolean delete(log_info data)throws Exception;
}
