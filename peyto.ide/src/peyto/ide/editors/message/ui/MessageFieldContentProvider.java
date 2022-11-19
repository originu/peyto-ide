package peyto.ide.editors.message.ui;

import org.eclipse.jface.viewers.ITreeContentProvider;

import peyto.ide.core.data.MessageFieldData;

public class MessageFieldContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		MessageFieldElement<MessageFieldData> elem = (MessageFieldElement<MessageFieldData>)parentElement;
		return getElements(elem);
	}

	@Override
	public Object[] getElements(Object arg0) {
		return null;
	}

	@Override
	public Object getParent(Object arg0) {
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		return false;
	}

}
