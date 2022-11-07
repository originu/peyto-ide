package peyto.ide.views.erd;

import java.util.List;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import peyto.ide.core.data.DBSchemaDto;
import peyto.ide.core.data.DBTableDto;
import peyto.ide.core.data.ResData;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;

public class DBTableListUI extends Composite {
	private Text filterText;
	private Table table;
	private TableViewer tableViewer;

	
	private HttpService httpService;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DBTableListUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));

		httpService = new HttpService();
		httpService.startup();
		
		Label schemaLabel = new Label(this, SWT.NONE);
		schemaLabel.setText("Schema");
		
		Combo tableCombo = new Combo(this, SWT.READ_ONLY);
		GridData gd_tableCombo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_tableCombo.widthHint = 258;
		tableCombo.setLayoutData(gd_tableCombo);
		tableCombo.select(0);
		tableCombo.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String schemaName = ( ( Combo )e.getSource() ).getText();
				String resourcePath = String.format( "/api/db/catalog/%s/schema/%s", "postgres", schemaName);
				httpService.callAsync(resourcePath, new ResponseHandler() {
					@Override
					public void completed(SimpleHttpResponse response) {
						try {
							ResData<List<DBTableDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<DBTableDto>>>() {});
							tableViewer.setInput(data.getBody());
						} catch (JsonMappingException e) {
							e.printStackTrace();
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
					}
				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
//				updateDBTables( e.text );
				System.out.println();
			}
		});
		
		Button refreshButton = new Button(this, SWT.NONE);
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String resourcePath = String.format( "/api/db/catalog/%s/schema", "postgres");
				httpService.callAsync(resourcePath, new ResponseHandler() {
					@Override
					public void completed(SimpleHttpResponse response) {
						try {
							ResData<List<DBSchemaDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<DBSchemaDto>>>() {});
							tableCombo.removeAll();
							data.getBody().forEach( (item) -> {
								tableCombo.add(item.getSchemaName());
							});
							tableCombo.update();
						} catch (JsonMappingException e) {
							e.printStackTrace();
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		refreshButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		refreshButton.setText("Refresh");
		
		Label filterLabel = new Label(this, SWT.NONE);
		filterLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		filterLabel.setText("Filter");
		
		filterText = new Text(this, SWT.BORDER);
		filterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setContentProvider( new ArrayContentProvider() );
		tableViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText(Object element) {
				return super.getText(element);
			}
		});
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		table.setLinesVisible(true);
		table.setHeaderBackground( Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND) );
		
		table.setHeaderVisible( true );
		TableViewerColumn logicalTableNameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		logicalTableNameColumn.getColumn().setWidth(160);
		logicalTableNameColumn.getColumn().setResizable(true);
		logicalTableNameColumn.getColumn().setText("LogicalName");
		logicalTableNameColumn.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public String getText(Object element) {
				DBTableDto dto = ((DBTableDto) element);
		    	return dto.getLogicalTableName();
		    }
		});
		
		TableViewerColumn physicalTableNameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		physicalTableNameColumn.getColumn().setWidth(180);
		physicalTableNameColumn.getColumn().setResizable(true);
		physicalTableNameColumn.getColumn().setText("PhysicalName");
		physicalTableNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBTableDto dto = ((DBTableDto) element);
		    	return dto.getTableName();
			}
		});
		tableViewer.addDoubleClickListener( new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				System.out.println();
			}
		});
		table.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'c')) {
//					TableItem[] selections = tableViewerDBTableName.getTable().getSelection();
//					if ( selections != null ) {
//						if ( selections.length > 0 ) {
//							
//							for ( TableItem tableItem : selections ) {
//								JsonNode data = ( JsonNode )tableItem.getData();
//								String physicalName	= data.findValue( "table_name" ).asText();
//								String logicalName	= data.findValue( "table_name" ).asText();
//								Clipboard clipboard = new Clipboard( Display.getCurrent() );
//								String copiedString = physicalName + "\t" + logicalName;
//								TextTransfer textTransfer = TextTransfer.getInstance();
//								clipboard.setContents(new String[] { copiedString}, new Transfer[] { textTransfer });
//								clipboard.dispose();
//							}
//						}
//					}
				} else if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'v')) {
					
				}
			}
		});		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}