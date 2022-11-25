package peyto.ide.editors.message.dnd;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import peyto.ide.core.data.MessageFieldDto;
import peyto.ide.editors.message.ui.MessageFieldTreeElement;

public class DragSourceListenerImpl extends DragSourceAdapter {

	private TreeViewer treeViewer;

	public DragSourceListenerImpl(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("DragSourceListenerImpl::dragStart");
		ISelection selection = treeViewer.getSelection();
		if (!selection.isEmpty()) {
			event.doit = true;
		} else {
			event.doit = false;
		}
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		System.out.println("DragSourceListenerImpl::dragSetData");
		IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		Object[] array = selection.toArray();
		MessageFieldTreeElement<MessageFieldDto>[] items = new MessageFieldTreeElement[array.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = (MessageFieldTreeElement<MessageFieldDto>) array[i];
		}
		event.data = items;
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("DragSourceListenerImpl::dragFinished");
		if (event.detail == DND.DROP_MOVE) {
			System.out.println("before >>>>");
//			for (MessageFieldTreeElement<MessageFieldDto> elements : rootElement.getChildren()) {
//				System.out.println(elements + " - " + elements.getElement().getMessageFieldName());
//			}	
//			(DragSource)event.getSource();
//			System.out.println("before <<<<");

			// ******************************************************************
//			IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
//			Object[] array = selection.toArray();
//			MessageFieldTreeElement<MessageFieldDto>[] items = new MessageFieldTreeElement[array.length];
//			for (int i = 0; i < items.length; i++) {
//				items[i] = (MessageFieldTreeElement<MessageFieldDto>) array[i];
//			}
//			for (MessageFieldTreeElement<MessageFieldDto> item : items) {
//				System.out.println(item.getElement().getMessageFieldName());
//				
//				Object data = event.data;
//				if (data == item) {
//					System.out.println();
//				} else {
//					System.out.println();
//				}
//				
//				item.getParent().getChildren().remove(item);
//			}

			// ******************************************************************
			// DropTargetListenerImpl.drop 에서 MessageFieldTreeElement<MessageFieldDto 의 children 하위에 추가를 하는데, 
			// 아래처럼 refresh를 콜해야 treeViewer.getTree().getItems() 데이터가 업데이트 된다.
//			treeViewer.refresh();	
			Tree tree = treeViewer.getTree();
			TreeItem[] items = tree.getSelection();
			
			MessageFieldTreeElement<MessageFieldDto> root = (MessageFieldTreeElement<MessageFieldDto>)treeViewer.getInput();
			for (int i = 0; i < items.length; i++) {
				TreeItem item = items[i];
				System.err.println("[2] " + item + ", " + item.getData());

				MessageFieldTreeElement<MessageFieldDto> parent = findParent(root, (MessageFieldTreeElement<MessageFieldDto>)item.getData(), new AtomicBoolean(false));
				parent.getChildren().remove(item.getData());
				
//				MessageFieldTreeElement<MessageFieldDto> elem = (MessageFieldTreeElement<MessageFieldDto>)item.getData();
//				boolean remove = elem.getParent().getChildren().remove(elem);
//				System.err.println("removed = " + remove);
				
//				root = elem.getParent();
//				MessageFieldTreeElement<MessageFieldDto> element = (MessageFieldTreeElement<MessageFieldDto>)item.getData();
//				
//				for (int j = 0; j < element.getParent().getChildren().size(); j++) {
//					MessageFieldTreeElement<MessageFieldDto> messageFieldTreeElement = element.getParent().getChildren().get(i);
//					if (messageFieldTreeElement.getElement().getMessageFieldName().equals(element.getElement().getMessageFieldName())) {
//						element.getParent().getChildren().remove(i);
//					}
//				}
				
//				MessageFieldTreeElement<MessageFieldDto> parentElement = (MessageFieldTreeElement<MessageFieldDto>)item.getParent().getData();
//				System.err.println( "[dragFinished] >> " + element.getElement().getMessageFieldName());
//				parentElement.getChildren().remove( element );
//				item.dispose();
			}

//			TreeItem[] selectedTreeItems = dragSourceItems;
//			for (TreeItem treeItem : selectedTreeItems) {
//				System.out.println("deleted = " + treeItem.getData() + " - " + ((IOItemElement) treeItem.getData()).getItemLogicalName());
//				((MessageFieldTreeElement<MessageFieldDto>) treeItem.getData()).getChildren().remove(treeItem);
//				ioSpecData.getInputSpecItem().removeChild( ( IOItemElement ) treeItem.getData() );
//				treeItem.dispose();
//			}

//			dragSourceItems = null;
			
//			MessageFieldTreeElement<MessageFieldDto> rootElem = new MessageFieldTreeElement<>();
//			updateHead(rootElem);
			
			updateHead(root);
			
			treeViewer.setInput( root );
			treeViewer.expandAll();
			//
			System.out.println("after >>>>");
//			for (IOItemElement IOItemElements : rootIOItemElement.getChild()) {
//				System.out.println(IOItemElements + " - " + IOItemElements.getItemLogicalName());
//			}
			System.out.println("after <<<<");
//			ioItemEditor.setDirty( true );		// need to save
		}

	}
	
	private void updateHead(MessageFieldTreeElement<MessageFieldDto> element) {
		ArrayList<MessageFieldTreeElement<MessageFieldDto>> children = element.getChildren();
		if(children.size() > 0) {
			for (MessageFieldTreeElement<MessageFieldDto> child : children) {
				child.setParent(element);
				updateHead(child);
			}
		} else {
			return;
		}
		
	}
	
	
	
	private MessageFieldTreeElement<MessageFieldDto> findParent(
			MessageFieldTreeElement<MessageFieldDto> root, 
			MessageFieldTreeElement<MessageFieldDto> item, AtomicBoolean flag ) {
		if ( root.getChildren().size() > 0) {
			if ( root == item ) {
				flag.set(true);
				return root;
			}
			for( MessageFieldTreeElement<MessageFieldDto> child : root.getChildren() ) {
				MessageFieldTreeElement<MessageFieldDto> current = findParent( child, item, flag);
				if ( current != null ) {
					if(flag.get() == true) {
						flag.set(false);
						return root;
					} else {
						return current;
					}
				}
			}
		} else {
			if ( root == item ) {
				flag.set(true);
				return root;
			}
		}
		return null;
	}
	
}