package Views.Interfaces;

import java.util.Map;

public interface ExtendedSearchBox extends BasicBox{
	public Map<String,Object> getSearchFilter();
	public void clearFields();
}
