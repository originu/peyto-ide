package peyto.ide.editors.message.ui;

import java.util.ArrayList;

public class MessageFieldTreeElement<T> {

	private MessageFieldTreeElement<T> parent;
	private T element;
	private ArrayList<MessageFieldTreeElement<T>> children = new ArrayList<>();
	
	public MessageFieldTreeElement() {
	}

	public MessageFieldTreeElement(T element) {
		this.element = element;
	}
	
	public MessageFieldTreeElement<T> getParent() {
		return parent;
	}
	public void setParent(MessageFieldTreeElement<T> parent) {
		this.parent = parent;
	}
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public ArrayList<MessageFieldTreeElement<T>> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<MessageFieldTreeElement<T>> children) {
		this.children = children;
	}

	
	
}
