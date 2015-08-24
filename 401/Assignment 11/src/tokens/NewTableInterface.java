package tokens;

import java.util.ArrayList;

public interface NewTableInterface<T> {

	public ArrayList<T> getKey();
	public ArrayList<T> getValue();
	public T get(T checkKey);
	public void put(T newKey, T newValue);
}
