package peyto.ide.views.dnd;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import peyto.ide.core.data.DBColumnDto;
import peyto.ide.editors.message.MessageFieldComposite;
import peyto.ide.editors.message.MessageFieldEditor;

public class DBColumnDtoDragSourceListener extends DragSourceAdapter {
	
	private TableViewer tableViewerDBTable; 

	public DBColumnDtoDragSourceListener(TableViewer tableViewerDBTable) {
		this.tableViewerDBTable	= tableViewerDBTable;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("DBColumnDtoDragSourceListener::dragStart");
		ISelection selection = tableViewerDBTable.getSelection();
		if (!selection.isEmpty()) {
			event.doit = true;
		} else {
			event.doit = false;
		}
	}
	
	@Override
	public void dragSetData(DragSourceEvent event) {
		System.out.println("DBColumnDtoDragSourceListener::dragSetData");
		IStructuredSelection selection = (IStructuredSelection) tableViewerDBTable.getSelection();
		Object[] array = selection.toArray();
		DBColumnDto[]	items	= new DBColumnDto[ array.length ];
		for (int i = 0; i < items.length; i++) {
			items[ i ] = ( DBColumnDto )array[ i ];
		}
		event.data = items;
		System.out.println("dragSetData1 + " + event);
	}
	
	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("DBColumnDtoDragSourceListener::dragFinished");
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if ( activeEditor instanceof MessageFieldEditor ) {
			Object selectedPage = ((MessageFieldEditor) activeEditor).getSelectedPage();
			if ( selectedPage instanceof MessageFieldComposite ) {
				((MessageFieldComposite) selectedPage).refreshTableViewer();
			}
		}
		System.out.println("dragFinished1 + " + event);				
	}
}
