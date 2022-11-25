package peyto.ide.editors.message.dnd;

import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;

import peyto.ide.core.data.DBColumnDto;
import peyto.ide.core.data.MessageFieldDto;
import peyto.ide.editors.message.ui.MessageFieldTreeElement;

public class DropTargetListenerImpl extends DropTargetAdapter {
	
	private TreeViewer treeViewer;
//	private MessageFieldTreeElement<MessageFieldDto> rootElement;
	private DropTargetDropListener	dropListener;
	
	public DropTargetListenerImpl(
			TreeViewer treeViewer, 
			DropTargetDropListener dropListener) {
		this.treeViewer = treeViewer;
		this.dropListener = dropListener;
	}
	
	@Override
	public void dragOver(DropTargetEvent event) {
		System.out.println("DropTargetListenerImpl::dragOver");
		event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SCROLL;
		if (event.item != null) {
			TreeItem item = (TreeItem) event.item;
			Point pt = Display.getCurrent().map(null, treeViewer.getTree(), event.x, event.y);
			Rectangle bounds = item.getBounds();
			if (pt.y < bounds.y + bounds.height / 3) {
				event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
			} else if (pt.y > bounds.y + 2 * bounds.height / 3) {
				event.feedback |= DND.FEEDBACK_INSERT_AFTER;
			} else {
				event.feedback |= DND.FEEDBACK_SELECT;
			}
		}
	}
	
	/**
	 * kevin, May 22th 2018
	 * DO NOT call tableViewer.refresh() in this drop method
	 */
	@Override
	public void drop(DropTargetEvent event) {
		System.out.println("DropTargetListenerImpl::drop");
		if (event.data == null) {
			event.detail = DND.DROP_NONE;
			return;
		}
		
//	    private long messageFieldId;
//	    private long messageId;
//	    private int messageFieldOrder;
//	    private int messageFieldDepth;
//	    private String messageFieldName;
//	    private String messageFieldDescription;
//	    private String messageFieldDataType;
//	    private long messageFieldLength;
//	    private String createdBy;
//	    private Date createdDate;
//	    private String updatedBy;
//	    private Date updatedDate;

		MessageFieldTreeElement<MessageFieldDto>[] copiedTargetItems = null;
		
		if( event.data instanceof DBColumnDto[]) {
			DBColumnDto[] _items = (DBColumnDto[]) event.data;
			copiedTargetItems = new MessageFieldTreeElement[_items.length];
			
			for (int i = 0; i < _items.length; i++) {
				DBColumnDto dbColumnDto = _items[i];
				MessageFieldDto dto = new MessageFieldDto();
				dto.setMessageFieldOrder(i + 1);
				dto.setMessageFieldDepth(1);
				dto.setMessageFieldDataType(dbColumnDto.getDataType());
				dto.setMessageFieldDescription("by db column, " + dbColumnDto.getColumnName());
				dto.setMessageFieldLength(0);
				dto.setMessageFieldName(dbColumnDto.getColumnName());
				copiedTargetItems[i] = new MessageFieldTreeElement<MessageFieldDto>(dto);
			}	
		} else if (event.data instanceof MessageFieldTreeElement<?>[]) {
			copiedTargetItems = (MessageFieldTreeElement<MessageFieldDto>[])event.data;
		} else {
			// need to implement more
			return;
		}

		MessageFieldTreeElement<MessageFieldDto> rootElement = (MessageFieldTreeElement<MessageFieldDto>) treeViewer.getInput();
		
		if (event.item == null) {
			// 이 경우는, 옮기는 element의 근처 element가 없을 때, 즉 drag and drop에서 근처 element가 지정되지 않았을 때이며, 이때는 루트의 하위에 포함시킨다.
			for ( MessageFieldTreeElement<MessageFieldDto> element : copiedTargetItems ) {
				rootElement.getChildren().add( element );
			}
		} else {
			Point pt = Display.getDefault().map(null, treeViewer.getTree(), event.x, event.y);
			TreeItem	targetItem = ( TreeItem ) event.item;		// 옮기는 element의 근처 element임 (옮기려는 element가 아님), event.data는 올기려는 element임
			Rectangle	targetItemBounds = targetItem.getBounds();
			TreeItem	targetParentItem = targetItem.getParentItem();
			if (targetParentItem != null) {
				// 타겟아이템이 부모 노드가 있을 경우,
				TreeItem[] items = targetParentItem.getItems();
				int index = 0;
				for (int i = 0; i < items.length; i++) {
					if (items[i] == targetItem) {
						index = i;
						break;
					}
				}
				if (pt.y < targetItemBounds.y + targetItemBounds.height / 3) {
					for (int i = (copiedTargetItems.length - 1); i >= 0; i--) {
						ArrayList<MessageFieldTreeElement<MessageFieldDto>> __items = ((MessageFieldTreeElement<MessageFieldDto>) targetParentItem.getData()).getChildren();
						if (!existElement(__items, copiedTargetItems[i])) {
						}
						__items.add(index, copiedTargetItems[i]);
					}
				} else if (pt.y > targetItemBounds.y + 2 * targetItemBounds.height / 3) {
					for (int i = (copiedTargetItems.length - 1); i >= 0; i--) {
						ArrayList<MessageFieldTreeElement<MessageFieldDto>> __items = ((MessageFieldTreeElement<MessageFieldDto>) targetParentItem.getData()).getChildren();
						if (!existElement(__items, copiedTargetItems[i])) {
						}
						__items.add(index + 1, copiedTargetItems[i]);
					}
				} else {
					for (MessageFieldTreeElement<MessageFieldDto> element : copiedTargetItems) {
						ArrayList<MessageFieldTreeElement<MessageFieldDto>> __items = ((MessageFieldTreeElement<MessageFieldDto>) targetItem.getData()).getChildren();
						if (!existElement(__items, element)) {
						}
						__items.add(element);
					}
				}
			} else {
				// 타겟 아이템이 부모 노드가 없는 경우, 이때 타겟 아이템은 depth가 1인 아이템
				TreeItem[] items = treeViewer.getTree().getItems();
				int index = 0;
				for (int i = 0; i < items.length; i++) {
					if (items[i] == targetItem) {
						index = i;
						break;
					}
				}
				if (pt.y < targetItemBounds.y + targetItemBounds.height / 3) {
					// 같은 depth에서 선택한 element를 같은 depth로 위의 element로 옮길 때
					for (int i = (copiedTargetItems.length - 1); i >= 0; i--) {
						if (!existElement(rootElement.getChildren(), copiedTargetItems[i])) {
						}
						rootElement.getChildren().add(index, copiedTargetItems[i]);
//						System.err.println("[1] " + rootElement.getChildren() + ", " + copiedTargetItems[i]);
					}
				} else if (pt.y > targetItemBounds.y + 2 * targetItemBounds.height / 3) {
					// 같은 depth에서 선택한 element를 같은 depth로 아래의 element로 옮길 때
					for (int i = (copiedTargetItems.length - 1); i >= 0; i--) {
						if (!existElement(rootElement.getChildren(), copiedTargetItems[i])) {
						}
						rootElement.getChildren().add(index + 1, copiedTargetItems[i]);
					}
				} else {
					// 같은 depth에서 선택한 element를 자신의 element의 하위로 추가할때, 이때는 무시해야 한다.
					for (MessageFieldTreeElement<MessageFieldDto> element : copiedTargetItems) {
						ArrayList<MessageFieldTreeElement<MessageFieldDto>> __items = ((MessageFieldTreeElement<MessageFieldDto>) targetItem.getData()).getChildren();
						if (!existElement(__items, element)) {
						}
						__items.add(element);
					}
				}
			}
		}
		dropListener.drop( event );
	}

	private boolean existElement(
			ArrayList<MessageFieldTreeElement<MessageFieldDto>> list,
			MessageFieldTreeElement<MessageFieldDto> newElem) {
		return list.stream().anyMatch( item -> (
				newElem.getElement().getMessageFieldName().equals(item.getElement().getMessageFieldName())
				)
			);
	}

}