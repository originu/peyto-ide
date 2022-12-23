package peyto.ide.editors.message;

import java.util.List;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.springframework.context.support.AbstractApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;

import peyto.ide.Activator;
import peyto.ide.core.data.ApplicationDto;
import peyto.ide.core.data.MessageChannelDto;
import peyto.ide.core.data.MessageDto;
import peyto.ide.core.data.ResData;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;

public class MessageBuilderComposite extends Composite {

	private AbstractApplicationContext appContext;
	private HttpService httpService;
	
	private ComboViewer applicationsComboViewer;
	private ComboViewer messageChannelComboViewer;
	private TableViewer messagesTableViewer;
	private Table table;
	private Text text;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MessageBuilderComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(5, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Applications");
		
		applicationsComboViewer = new ComboViewer(this, SWT.NONE | SWT.READ_ONLY);
		Combo combo = applicationsComboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 79;
		combo.setLayoutData(gd_combo);
		applicationsComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		applicationsComboViewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
		        if (element instanceof ApplicationDto) {
		        	ApplicationDto type = (ApplicationDto) element;
		            return type.getApplicationName();
		        } else {
		        	return "unknown";
		        }
		    }
		});
		applicationsComboViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				if ( arg0.getSelection() instanceof StructuredSelection) {
					StructuredSelection s = (StructuredSelection)arg0.getSelection();
					ApplicationDto dto = (ApplicationDto)s.getFirstElement();
					
					String resourcePath = String.format( "/api/message-channel?applicationId=%s", dto.getApplicationId());
					httpService.get(resourcePath, new ResponseHandler() {
						@Override
						public void completed(SimpleHttpResponse response) {
							try {
								ResData<List<MessageChannelDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<MessageChannelDto>>>() {});
								messageChannelComboViewer.setInput(data.getBody());
							} catch (Exception e) {
								e.printStackTrace();
								Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "test error");
								ErrorDialog.openError(getShell(), "Error", "Exception occured during calling API", status);
							}
						}
					});
				}
				
			}
		});
		
		
		Button applicationRefreshButton = new Button(this, SWT.NONE);
		applicationRefreshButton.setText("Refresh");
		applicationRefreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				String resourcePath = String.format( "/api/application");
				httpService.get(resourcePath, new ResponseHandler() {
					@Override
					public void completed(SimpleHttpResponse response) {
						try {
							ResData<List<ApplicationDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<ApplicationDto>>>() {});
							applicationsComboViewer.setInput(data.getBody());
						} catch (Exception e) {
							e.printStackTrace();
							Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "test error");
							ErrorDialog.openError(getShell(), "Error", "Exception occured during calling API", status);
						}
					}
				});
			}
		});
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("Search");
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("Message Channels");
		
		messageChannelComboViewer = new ComboViewer(this, SWT.NONE | SWT.READ_ONLY);
		messageChannelComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		messageChannelComboViewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
		        if (element instanceof MessageChannelDto) {
		        	MessageChannelDto type = (MessageChannelDto) element;
		            return type.getMessageChannelName();
		        } else {
		        	return "unknown";
		        }
		    }
		});
		messageChannelComboViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				if ( arg0.getSelection() instanceof StructuredSelection) {
					StructuredSelection s = (StructuredSelection)arg0.getSelection();
					MessageChannelDto dto = (MessageChannelDto)s.getFirstElement();
					
					String resourcePath = String.format( "/api/message?messageChannelId=%s", dto.getMessageChannelId());
					httpService.get(resourcePath, new ResponseHandler() {
						@Override
						public void completed(SimpleHttpResponse response) {
							try {
								ResData<List<MessageDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<MessageDto>>>() {});
								messagesTableViewer.setInput(data.getBody());
							} catch (Exception e) {
								e.printStackTrace();
								Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "test error");
								ErrorDialog.openError(getShell(), "Error", "Exception occured during calling API", status);
							}
						}
					});
				}
				
			}
		});
		Combo combo_1 = messageChannelComboViewer.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button messageChannelRefreshButton = new Button(this, SWT.NONE);
		messageChannelRefreshButton.setText("Refresh");
		messageChannelRefreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection s = (StructuredSelection)messageChannelComboViewer.getSelection();
				MessageChannelDto dto = (MessageChannelDto)s.getFirstElement();
				String resourcePath = String.format( "/api/message-channel?applicationId=%s", dto.getApplicationId());
				httpService.get(resourcePath, new ResponseHandler() {
					@Override
					public void completed(SimpleHttpResponse response) {
						try {
							ResData<List<MessageChannelDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<MessageChannelDto>>>() {});
							messageChannelComboViewer.setInput(data.getBody());
						} catch (Exception e) {
							e.printStackTrace();
							Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "test error");
							ErrorDialog.openError(getShell(), "Error", "Exception occured during calling API", status);
						}
					}
				});
			}
		});
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		messagesTableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		messagesTableViewer.setContentProvider( new ArrayContentProvider() );
		table = messagesTableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		table.setLinesVisible(true);
		table.setHeaderBackground( Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND) );
		table.setHeaderVisible( true );
		
		TableViewerColumn messageIdColumn = new TableViewerColumn(messagesTableViewer, SWT.NONE);
		messageIdColumn.getColumn().setWidth(160);
		messageIdColumn.getColumn().setResizable(true);
		messageIdColumn.getColumn().setText("ID");
		messageIdColumn.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public String getText(Object element) {
		    	MessageDto dto = ((MessageDto) element);
		    	return String.valueOf(dto.getMessageId());
		    }
		});
		
		TableViewerColumn messageNameColumn = new TableViewerColumn(messagesTableViewer, SWT.NONE);
		messageNameColumn.getColumn().setWidth(160);
		messageNameColumn.getColumn().setResizable(true);
		messageNameColumn.getColumn().setText("Name");
		messageNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MessageDto dto = ((MessageDto) element);
				return dto.getMessageName();
			}
		});
		
		TableViewerColumn messageDescColumn = new TableViewerColumn(messagesTableViewer, SWT.NONE);
		messageDescColumn.getColumn().setWidth(160);
		messageDescColumn.getColumn().setResizable(true);
		messageDescColumn.getColumn().setText("Description");
		messageDescColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MessageDto dto = ((MessageDto) element);
				return dto.getMessageDescription();
			}
		});
		
		TableViewerColumn messageUpdatedColumn = new TableViewerColumn(messagesTableViewer, SWT.NONE);
		messageUpdatedColumn.getColumn().setWidth(160);
		messageUpdatedColumn.getColumn().setResizable(true);
		messageUpdatedColumn.getColumn().setText("Updated Date");
		messageUpdatedColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MessageDto dto = ((MessageDto) element);
				if(dto.getUpdatedDate() == null ) {
					if (dto.getCreatedDate() == null) {
						return "";
					} else {
						return dto.getCreatedDate().toString();
					}
				} else {
					return dto.getCreatedDate().toString();
				}
			}
		});
		messagesTableViewer.addDoubleClickListener( new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection	selection	= (IStructuredSelection)event.getSelection();
				MessageDto dto = (MessageDto)selection.getFirstElement();
		        try {
					MessageFieldEditorInput editorInput = new MessageFieldEditorInput(dto);
					IEditorPart openEditor = PlatformUI
							.getWorkbench()
							.getActiveWorkbenchWindow()
							.getActivePage()
							.openEditor(editorInput, MessageFieldEditor.ID);
		        	
		        	System.out.println(openEditor);
		        } catch (PartInitException e) {
		            e.printStackTrace();
		        }
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setApplicationContext(AbstractApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	public void init() {
		this.httpService = appContext.getBean(HttpService.class);
	}
}
