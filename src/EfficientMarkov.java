import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov() {
		this(3);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int i = 0; i < myText.length() - myOrder; i++) {
			String item = myText.substring(i, i + myOrder);
			if (myMap.containsKey(item)) {
				ArrayList<String> newList = myMap.get(item);
				newList.add(myText.substring(i + myOrder, i + myOrder + 1));
				myMap.put(item, newList);
				}
			else {
				ArrayList<String> newList = new ArrayList<String>();
				newList.add(myText.substring(i + myOrder, i + myOrder + 1));
				myMap.put(item, newList);
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(String key) {
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		}
		throw new NoSuchElementException(key+" not in map");
	}
}
