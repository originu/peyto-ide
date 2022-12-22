package peyto.ide.editors.generation;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import peyto.ide.core.model.ManifestModel;

public class SourceCodeGenEditorInput implements IEditorInput {

	private ManifestModel data;
	
	public SourceCodeGenEditorInput(ManifestModel model) {
		this.setData(getData());
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
		return getData().getMetadata().getKind();
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
		return getData().getMetadata().getKind();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return getData().getMetadata().getKind().equals(this.getName());
	}

	public ManifestModel getData() {
		return data;
	}

	public void setData(ManifestModel data) {
		this.data = data;
	}


}
