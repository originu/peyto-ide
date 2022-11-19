package peyto.ide.editors.message;

import java.util.List;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;

import peyto.ide.Activator;
import peyto.ide.core.data.ApplicationDto;
import peyto.ide.core.data.MessageDto;
import peyto.ide.core.data.ResData;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;

public class MessageBuilderComposite extends Composite {

	private AbstractApplicationContext appContext;
	private HttpService httpService;
	
	private ComboViewer comboViewer;
	private TableViewer tableViewer;
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
		lblNewLabel.setText("Application");
		
		comboViewer = new ComboViewer(this, SWT.NONE | SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 79;
		combo.setLayoutData(gd_combo);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {
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
		comboViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				if ( arg0.getSelection() instanceof StructuredSelection) {
					StructuredSelection s = (StructuredSelection)arg0.getSelection();
					ApplicationDto dto = (ApplicationDto)s.getFirstElement();
					
					String resourcePath = String.format( "/api/message?applicationId=%s", dto.getApplicationId());
					httpService.callAsync(resourcePath, new ResponseHandler() {
						@Override
						public void completed(SimpleHttpResponse response) {
							try {
								ResData<List<MessageDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<MessageDto>>>() {});
								tableViewer.setInput(data.getBody());
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
		
		
		Button refreshButton = new Button(this, SWT.NONE);
		refreshButton.setText("Refresh");
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String resourcePath = String.format( "/api/application");
				httpService.callAsync(resourcePath, new ResponseHandler() {
					@Override
					public void completed(SimpleHttpResponse response) {
						try {
							ResData<List<ApplicationDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<ApplicationDto>>>() {});
							comboViewer.setInput(data.getBody());
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
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setContentProvider( new ArrayContentProvider() );
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		
		TableViewerColumn messageIdColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
		
		TableViewerColumn messageNameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
		
		TableViewerColumn messageDescColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
		
		TableViewerColumn messageUpdatedColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
						return dto.getUpdatedDate().toString();
					}
				} else {
					return dto.getCreatedDate().toString();
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
