package peyto.ide.editors.message.ui;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageFieldTreeElement<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3560783242204168373L;
	
	private MessageFieldTreeElement<T> parent;
	private T element;
	private ArrayList<MessageFieldTreeElement<T>> children = new ArrayList<>();
	
	public MessageFieldTreeElement() {
	}

	public MessageFieldTreeElement(MessageFieldTreeElement<T> parent, T element) {
		this.parent = parent;
		this.element = element;
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
