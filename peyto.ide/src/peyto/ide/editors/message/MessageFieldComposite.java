package peyto.ide.editors.message;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.springframework.context.support.AbstractApplicationContext;

import peyto.ide.editors.message.ui.MessageFieldContentProvider;
import peyto.ide.editors.message.ui.MessageFieldTableColumn;

public class MessageFieldComposite extends Composite {

	private AbstractApplicationContext appContext;
	
	private TreeViewer treeViewer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MessageFieldComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		new Label(this, SWT.NONE);
		
		treeViewer = new TreeViewer(this, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		treeViewer.setContentProvider(new MessageFieldContentProvider());
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setHeaderBackground( Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND) );
		
		
//		MessageFieldTableColumn[] treeViewerColumns = {
//			
//		};
//		
//		for ( MessageFieldTableColumn treeViewerColumn : treeViewerColumns ) {
//			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE | SWT.MouseDoubleClick);
//			column.getColumn().setText(treeViewerColumn.getColumnName());
//			column.getColumn().setWidth(treeViewerColumn.getColumnWidth());
//			column.getColumn().setMoveable(treeViewerColumn.isMoveable());
//			column.setLabelProvider(treeViewerColumn.getLabelProvider());
//			column.setEditingSupport(treeViewerColumn.getEditingSupport());
//		}
	}
	
	
	public void setData() {
		treeViewer.setInput("");		
	}
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setApplicationContext(AbstractApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	public void init() {
	}
}
