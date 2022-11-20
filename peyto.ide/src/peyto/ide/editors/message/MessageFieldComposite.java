package peyto.ide.editors.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
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

import com.fasterxml.jackson.core.type.TypeReference;

import peyto.ide.core.data.MessageDto;
import peyto.ide.core.data.MessageFieldDto;
import peyto.ide.core.data.ResData;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;
import peyto.ide.editors.message.ui.MessageFieldContentProvider;
import peyto.ide.editors.message.ui.MessageFieldTreeElement;

public class MessageFieldComposite extends Composite {

	private AbstractApplicationContext appContext;
	
	private TreeViewer treeViewer;
	
	private MessageFieldEditorInput editorInput;

	private HttpService httpService;
	
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

		TreeViewerColumn messageFieldNameColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		messageFieldNameColumn.getColumn().setWidth(160);
		messageFieldNameColumn.getColumn().setResizable(true);
		messageFieldNameColumn.getColumn().setText("field name");
		messageFieldNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MessageFieldTreeElement<MessageFieldDto> elem = ((MessageFieldTreeElement<MessageFieldDto>) element);
				return elem.getElement().getMessageFieldName();
			}
		});
		
		TreeViewerColumn messageFieldIdColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		messageFieldIdColumn.getColumn().setWidth(160);
		messageFieldIdColumn.getColumn().setResizable(true);
		messageFieldIdColumn.getColumn().setText("field id");
		messageFieldIdColumn.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public String getText(Object element) {
		    	MessageFieldTreeElement<MessageFieldDto> elem = ((MessageFieldTreeElement<MessageFieldDto>) element);
				return String.valueOf(elem.getElement().getMessageFieldId());
		    }
		});
		
		TreeViewerColumn messageIdColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		messageIdColumn.getColumn().setWidth(160);
		messageIdColumn.getColumn().setResizable(true);
		messageIdColumn.getColumn().setText("message id");
		messageIdColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
		    	MessageFieldTreeElement<MessageFieldDto> elem = ((MessageFieldTreeElement<MessageFieldDto>) element);
				return String.valueOf(elem.getElement().getMessageId());
			}
		});
		
		TreeViewerColumn messageFieldOrderColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		messageFieldOrderColumn.getColumn().setWidth(160);
		messageFieldOrderColumn.getColumn().setResizable(true);
		messageFieldOrderColumn.getColumn().setText("field order");
		messageFieldOrderColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MessageFieldTreeElement<MessageFieldDto> elem = ((MessageFieldTreeElement<MessageFieldDto>) element);
				return String.valueOf(elem.getElement().getMessageFieldOrder());
			}
		});

		TreeViewerColumn messageFieldDepthColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		messageFieldDepthColumn.getColumn().setWidth(160);
		messageFieldDepthColumn.getColumn().setResizable(true);
		messageFieldDepthColumn.getColumn().setText("field depth");
		messageFieldDepthColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MessageFieldTreeElement<MessageFieldDto> elem = ((MessageFieldTreeElement<MessageFieldDto>) element);
				return String.valueOf(elem.getElement().getMessageFieldDepth());
			}
		});
		
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
//		treeViewer.setInput("");		
	}
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setApplicationContext(AbstractApplicationContext appContext) {
		this.appContext = appContext;
	}

	public void setEditorInput(MessageFieldEditorInput editorInput) {
		this.editorInput = editorInput;		
	}
	
	public void init() {
		this.httpService = appContext.getBean(HttpService.class);
		MessageDto data = editorInput.getData();
		String resourcePath = String.format( "/api/message/%s/messagefield", data.getMessageId());
		httpService.callAsync(resourcePath, new ResponseHandler() {
			@Override
			public void completed(SimpleHttpResponse response) {
				try {
					ResData<List<MessageFieldDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<MessageFieldDto>>>() {});
					MessageFieldTreeElement<MessageFieldDto> rootElem = toTreeData( data.getBody());
					treeViewer.setInput(rootElem);
//					data.getBody().stream().forEach( item -> {
//						System.out.println( item );
//					});
					System.out.println(rootElem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MessageFieldTreeElement<MessageFieldDto> toTreeData(List<MessageFieldDto> items) {
		MessageFieldTreeElement<MessageFieldDto> rootElem = new MessageFieldTreeElement<>();

		// list => tree
		MessageFieldTreeElement<MessageFieldDto> currElement = rootElem;
		int prevMessageFieldDepth = 1;
		for( MessageFieldDto item : items ) {
			int messageFieldDepth = item.getMessageFieldDepth();
			if( prevMessageFieldDepth < messageFieldDepth ) {
				// add one in sub
				ArrayList<MessageFieldTreeElement<MessageFieldDto>> children = currElement.getChildren();
				MessageFieldTreeElement<MessageFieldDto> lastMessageFieldTreeElement = children.get(children.size() - 1);	// last one
				lastMessageFieldTreeElement.getChildren().add(new MessageFieldTreeElement<MessageFieldDto>(item) );
				currElement = lastMessageFieldTreeElement;
				prevMessageFieldDepth = messageFieldDepth;
			} else if( prevMessageFieldDepth > messageFieldDepth ) {
				MessageFieldTreeElement<MessageFieldDto> searchedElementByDepth = findElement( rootElem, messageFieldDepth);
				searchedElementByDepth.getChildren().add(new MessageFieldTreeElement<MessageFieldDto>(item));
				currElement = searchedElementByDepth;
				prevMessageFieldDepth = messageFieldDepth;
			} else {
				currElement.getChildren().add(new MessageFieldTreeElement<MessageFieldDto>(item));
			}
		}
		return rootElem;
	}
	
	private MessageFieldTreeElement<MessageFieldDto> findElement(MessageFieldTreeElement<MessageFieldDto> rootElement, int depth) {
		MessageFieldTreeElement<MessageFieldDto> currElement = rootElement;
		for (int i = 1; i < depth; i++) {
			ArrayList<MessageFieldTreeElement<MessageFieldDto>> children = currElement.getChildren();
			MessageFieldTreeElement<MessageFieldDto> lastMessageFieldTreeElement = children.get(children.size() - 1);
			currElement = lastMessageFieldTreeElement;
		}
		return currElement;
	}
}
