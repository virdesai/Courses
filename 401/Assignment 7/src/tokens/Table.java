package tokens;

import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"Table"})
public class Table implements TableInterface{

	public ArrayList<String> key = new ArrayList<>();
	public ArrayList<Object> value = new ArrayList<>();

	public void put(String newKey, Object newValue){
		int index;
		if(get(newKey) != null){
			index = Integer.valueOf(key.indexOf(newKey));
			value.set(index, newValue);
		}else if(get(newKey) == null){
			key.add(newKey);
			value.add(newValue);
		}
	}

	public Object get(String checkKey){
		Object keyValue = new Object();
		int index;
		if (key.contains(checkKey)){
		index = Integer.valueOf(key.indexOf(checkKey));
		keyValue = value.get(index);
		return keyValue;
		}else{
			return null;
		}
	}
	
	public ArrayList<String> getKey(){
		return key;
	}
	public ArrayList<Object> getValue(){
		return value;
	}

}
