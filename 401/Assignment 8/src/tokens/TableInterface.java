package tokens;

import java.util.ArrayList;

public interface TableInterface {
	
	public ArrayList<String> getKey();
	public ArrayList<Object> getValue();
	public Object get(String checkKey);
	public void put(String newKey, Object newValue);

}
