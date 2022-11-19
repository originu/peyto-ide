package peyto.ide.editors.message.ui;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;

public interface MessageFieldTableColumn {

	public String getColumnName();
	public int getColumnWidth();
	public boolean isMoveable();
	public CellLabelProvider getLabelProvider();
	public EditingSupport getEditingSupport();
}

/*
Key(treeViewer, ioItemEditor),
ItemType(treeViewer, ioItemEditor),
JavaDataType(treeViewer, ioItemEditor),
DBColumnDataType(treeViewer, ioItemEditor),
SourceType(treeViewer, ioItemEditor),
DataLengthType(treeViewer, ioItemEditor),
DescriptionType(treeViewer, ioItemEditor),
*/

class AbstractFieldColumn implements MessageFieldTableColumn {

	public String columnName;
	public int columnWidth;
	public boolean isMoveable;
	public CellLabelProvider labelProvider;
	public EditingSupport editingSupport;
	
	AbstractFieldColumn() {
		
	}
	
	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public int getColumnWidth() {
		return columnWidth;
	}

	@Override
	public boolean isMoveable() {
		return isMoveable;
	}

	@Override
	public CellLabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Override
	public EditingSupport getEditingSupport() {
		return editingSupport;
	}

}

class NameColumn extends AbstractFieldColumn {

}