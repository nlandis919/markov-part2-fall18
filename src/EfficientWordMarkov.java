import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
private Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov() {
		this(2);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");;
		myMap.clear();
		for (int i = 0; i < myWords.length - myOrder; i++) {
			WordGram item = new WordGram(myWords, i, myOrder);
			if (myMap.containsKey(item) && (i + myOrder + 1 < myWords.length)) {
				ArrayList<String> newList = myMap.get(item);
				newList.add(myWords[i + myOrder + 1]);
				myMap.put(item, newList);
				}
			else if (i + myOrder + 1 < myWords.length) {
				ArrayList<String> newList = new ArrayList<String>();
				newList.add(myWords[i + myOrder + 1]);
				myMap.put(item, newList);
			}
			else if (myMap.containsKey(item)) {
				ArrayList<String> newList = myMap.get(item);
				newList.add(PSEUDO_EOS);
				myMap.put(item, newList);
			}
			else {
				ArrayList<String> newList = new ArrayList<String>();
				newList.add(PSEUDO_EOS);
				myMap.put(item, newList);
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key) {
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		}
		throw new NoSuchElementException(key+" not in map");
	}

}
