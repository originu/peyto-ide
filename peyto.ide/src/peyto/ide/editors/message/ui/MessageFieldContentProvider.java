package peyto.ide.editors.message.ui;

import org.eclipse.jface.viewers.ITreeContentProvider;

import peyto.ide.core.data.MessageFieldData;

public class MessageFieldContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object element) {
		return getElements(element);
	}

	@Override
	public Object[] getElements(Object element) {
		return ((MessageFieldTreeElement<MessageFieldData>) element).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		if (element == null ) {
			return element;
		} else {
			MessageFieldTreeElement<MessageFieldData> elem = (MessageFieldTreeElement<MessageFieldData>)element;
			return elem.getParent();
		}
	}

	@Override
	public boolean hasChildren(Object element) {
		return ((MessageFieldTreeElement<MessageFieldData>) element).getChildren().size() > 0;
	}

}
