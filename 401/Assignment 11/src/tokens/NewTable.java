package tokens;

import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"Table"})
public class NewTable<T> implements NewTableInterface<T>{
	
	public ArrayList<T> key = new ArrayList<>();
	public ArrayList<T> value = new ArrayList<>();

	public void put(T newKey, T newValue){

		//
		//This table key value checking from earlier assignments was causing issues with my commandlist when it attempted to do multiple move commands in 1 list.
		//

		//int index;
		//if(get(newKey) != null){
		//	index = Integer.valueOf(key.indexOf(newKey));
		//	value.set(index, newValue);
		//}else if(get(newKey) == null){
			key.add(newKey);
			value.add(newValue);
		//}
	}

	public T get(T checkKey){
		T keyValue;
		int index;
		if (key.contains(checkKey)){
		index = Integer.valueOf(key.indexOf(checkKey));
		keyValue = value.get(index);
		return keyValue;
		}else{
			return null;
		}
	}

	public ArrayList<T> getKey(){
		return key;
	}
	public ArrayList<T> getValue(){
		return value;
	}
}
