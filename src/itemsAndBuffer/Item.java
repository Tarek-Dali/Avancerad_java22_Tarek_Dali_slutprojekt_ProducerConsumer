package itemsAndBuffer;

public class Item {
	String id = "";
	
	public Item(String id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id;	
	}
}
