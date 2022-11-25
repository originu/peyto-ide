package peyto.ide.views.erd;

import java.util.List;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;

import peyto.ide.core.data.DBColumnDto;
import peyto.ide.core.data.DBTableDto;
import peyto.ide.core.data.ResData;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;
import peyto.ide.views.dnd.DBColumnDtoDragSourceListener;
import peyto.ide.views.dnd.DBColumnDtoDragAndDropTransfer;

public class DBColumnListUI extends Composite {
	
	private TableViewer tableViewer;
	private Table table;

	private ApplicationContext applicationContext;
	private HttpService httpService;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DBColumnListUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		new Label(this, SWT.NONE);
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderBackground( Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND) );
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setBounds(0, 0, 85, 85);
		tableViewer.setContentProvider( new ArrayContentProvider() );
		table.setHeaderVisible( true );
		table.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		DBColumnColumn[]	_columns	= new DBColumnColumn[] {
				new DBTableColumnColumnLogicalName(),
				new DBTableColumnColumnPhysicalName(),
				new DBTableColumnColumnDataType(),
				new DBTableColumnColumnDataLength(),
				new DBTableColumnColumnDefaultValue(),
				new DBTableColumnColumnPKOrder()
		};
		
		for ( DBColumnColumn column : _columns ) {
			TableViewerColumn col = new TableViewerColumn(tableViewer, SWT.NONE);
			col.getColumn().setWidth( column.getWidth() );
			col.getColumn().setResizable( column.isResizable() );
			col.getColumn().setText( column.getText());
			col.setLabelProvider(column.getColumnLabelProvider());
		}
		
		// drag and drop
		tableViewer.addDragSupport( 
				DND.DROP_MOVE | DND.DROP_COPY, 
				new Transfer[] { DBColumnDtoDragAndDropTransfer.getInstance() }, 
				new DBColumnDtoDragSourceListener( tableViewer ) {
			
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void updateTableData(DBTableDto dto) {
		String resourcePath = String.format( "/api/db/catalog/%s/schema/%s/table/%s", dto.getTableCatalog(), dto.getTableSchema(), dto.getTableName());
		httpService.callAsync(resourcePath, new ResponseHandler() {
			@Override
			public void completed(SimpleHttpResponse response) {
				try {
					ResData<List<DBColumnDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<DBColumnDto>>>() {});
					tableViewer.setInput(data.getBody());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void init() {
		this.httpService = this.applicationContext.getBean(HttpService.class);		
	}

}



// ========================


interface DBColumnColumn {

	int getWidth();

	boolean isResizable();

	String getText();

	ColumnLabelProvider getColumnLabelProvider();

//	"table_catalog" 
//	"table_schema" 
//	"table_name" 
//	"column_name" 
//	"ordinal_position" 
//	"column_default" 
//	"is_nullable" 
//	"data_type" 
//	"character_maximum_length" 
//	"numeric_precision" 
//	"numeric_precision_radix" 
//	"numeric_scale"
//	"datetime_precision" 

}

class DBTableColumnColumnLogicalName implements DBColumnColumn {
	@Override
	public int getWidth() {
		return 160;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public String getText() {
		return "LogicalName";
	}
	
	@Override
	public ColumnLabelProvider getColumnLabelProvider() {
		return new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBColumnDto dto = (DBColumnDto) element;
				return dto.getColumnName();
			}
		};
	}
}

class DBTableColumnColumnPhysicalName implements DBColumnColumn {
	@Override
	public int getWidth() {
		return 180;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public String getText() {
		return "PhysicalName";
	}
	
	@Override
	public ColumnLabelProvider getColumnLabelProvider() {
		return new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBColumnDto dto = (DBColumnDto) element;
				return dto.getColumnName();
			}
		};
	}
}

class DBTableColumnColumnDataType implements DBColumnColumn {
	@Override
	public int getWidth() {
		return 180;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public String getText() {
		return "DataType";
	}
	
	@Override
	public ColumnLabelProvider getColumnLabelProvider() {
		return new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBColumnDto dto = (DBColumnDto) element;
				return dto.getDataType();
			}
		};
	}
}

class DBTableColumnColumnDataLength implements DBColumnColumn {
	@Override
	public int getWidth() {
		return 180;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public String getText() {
		return "Data Length";
	}
	
	@Override
	public ColumnLabelProvider getColumnLabelProvider() {
		return new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBColumnDto dto = (DBColumnDto) element;
				return dto.getCharacterMaximumLength();
			}
		};
	}
}

class DBTableColumnColumnDefaultValue implements DBColumnColumn {
	@Override
	public int getWidth() {
		return 180;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public String getText() {
		return "Default Value";
	}
	
	@Override
	public ColumnLabelProvider getColumnLabelProvider() {
		return new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBColumnDto dto = (DBColumnDto) element;
				return dto.getColumnDefault();
			}
		};
	}
}

class DBTableColumnColumnPKOrder implements DBColumnColumn {
	@Override
	public int getWidth() {
		return 70;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public String getText() {
		return "PK Order";
	}
	
	@Override
	public ColumnLabelProvider getColumnLabelProvider() {
		return new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DBColumnDto dto = (DBColumnDto) element;
				return String.valueOf(dto.getPkOrder());
			}
		};
	}
}
