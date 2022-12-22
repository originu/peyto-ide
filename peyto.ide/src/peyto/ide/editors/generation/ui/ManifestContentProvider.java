package peyto.ide.editors.generation.ui;

import org.eclipse.jface.viewers.ITreeContentProvider;

import peyto.ide.core.model.GroupModel;
import peyto.ide.core.model.ItemModel;
import peyto.ide.core.model.ManifestModel;
import peyto.ide.core.model.MetadataModel;
import peyto.ide.core.model.ProjectModel;
import peyto.ide.core.model.SpecModel;
import peyto.ide.core.model.TargetModel;
import peyto.ide.core.model.TemplateModel;

public class ManifestContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object element) {
		return getElements(element);
	}

	@Override
	public Object[] getElements(Object element) {
		if (element instanceof ManifestModel) {
			return ((ManifestModel) element).getMetadata().getGroups().toArray();
		} else if (element instanceof MetadataModel) {
			return ((MetadataModel) element).getGroups().toArray();
		} else if (element instanceof GroupModel) {
			return ((GroupModel) element).getItems().toArray();
		} else if (element instanceof ItemModel) {
			return new Object[] {};
		} else if (element instanceof SpecModel) {
			return new Object[] {};
		} else if (element instanceof ProjectModel) {
			return new Object[] {};
		} else if (element instanceof TargetModel) {
			return new Object[] {};
		} else if (element instanceof TemplateModel) {
			return new Object[] {};
		} else {
			return new Object[] {};
		}
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof ItemModel) {
			return false;
		} else if (element instanceof SpecModel) {
			return false;
		} else if (element instanceof ProjectModel) {
			return false;
		} else if (element instanceof TargetModel) {
			return false;
		} else if (element instanceof TemplateModel) {
			return false;
		} else {
			return true;
		}
	}

}
