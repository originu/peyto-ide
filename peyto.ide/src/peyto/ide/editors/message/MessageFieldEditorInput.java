package peyto.ide.editors.message;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

import peyto.ide.core.data.MessageDto;

public class MessageFieldEditorInput implements IEditorInput {

	private MessageDto data;
	
	public MessageFieldEditorInput(MessageDto data) {
		this.data = data;
	}
	
	@Override
	public <T> T getAdapter(Class<T> arg0) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return data.getMessageName();
	}

	/**
	 * https://www.programcreek.com/java-api-examples/nl/?api=org.eclipse.ui.IPersistableElement
	 */
	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return data.getMessageDescription();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageFieldEditorInput selectedObj = (MessageFieldEditorInput) obj;
		return selectedObj.getName().equals(this.getName());
	}

}
