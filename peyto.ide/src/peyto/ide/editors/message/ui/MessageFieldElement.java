package peyto.ide.editors.message.ui;

import java.util.ArrayList;

public class MessageFieldElement<T> {

	private T parent;
	private ArrayList<T> children = new ArrayList<>();

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}
	
	public ArrayList<T> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<T> children) {
		this.children = children;
	}
	
}
