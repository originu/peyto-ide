package peyto.ide.editors.template;

import java.util.List;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;

import peyto.ide.core.data.ResData;
import peyto.ide.core.data.TemplateCategoryDto;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;
import peyto.ide.editors.template.ui.TemplateCategoryAddDialog;

public class TemplateEditorCategoriesComposite extends Composite {

	private ApplicationContext applicationContext;
	private TableViewer tableViewer = null;
	private HttpService httpService = null;
	private TemplateEditor templateEditor = null;
	private TemplateEditorTemplatesComposite templateEditorTemplatesComposite = null;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TemplateEditorCategoriesComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Button addButton = new Button(this, SWT.NONE);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TemplateCategoryAddDialog dialog = new TemplateCategoryAddDialog(getShell());
				dialog.create();
				if (dialog.open() == Window.OK) {
					System.out.println(TemplateEditorCategoriesComposite.class);
				}
			}
		});
		
		GridData gd_addButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_addButton.widthHint = 80;
		addButton.setLayoutData(gd_addButton);
		addButton.setText("Add");
		
		Button updateButton = new Button(this, SWT.NONE);
		updateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		GridData gd_updateButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_updateButton.widthHint = 80;
		updateButton.setLayoutData(gd_updateButton);
		updateButton.setText("Update");
		
		Button removeButton = new Button(this, SWT.NONE);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				MessageDialog.openWarning(getShell(), "Warning", "Are you sure to delete a template category?");
			}
		});
		GridData gd_removeButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_removeButton.widthHint = 80;
		removeButton.setLayoutData(gd_removeButton);
		removeButton.setText("Remove");
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setContentProvider( new ArrayContentProvider() );
		tableViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText(Object element) {
				return super.getText(element);
			}
		});
		Table table = tableViewer.getTable();
		table.setHeaderBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_table.widthHint = 380;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		TableViewerColumn categoryCodeColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		categoryCodeColumn.getColumn().setWidth(250);
		categoryCodeColumn.getColumn().setResizable(true);
		categoryCodeColumn.getColumn().setText("CategoryCode");
		categoryCodeColumn.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public String getText(Object element) {
				TemplateCategoryDto dto = ((TemplateCategoryDto) element);
		    	return dto.getCategoryCode();
		    }
		});
		TableViewerColumn categoryNameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		categoryNameColumn.getColumn().setWidth(250);
		categoryNameColumn.getColumn().setResizable(true);
		categoryNameColumn.getColumn().setText("CategoryName");
		categoryNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TemplateCategoryDto dto = ((TemplateCategoryDto) element);
		    	return dto.getCategoryName();
			}
		});
		TableViewerColumn descriptionColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		descriptionColumn.getColumn().setWidth(300);
		descriptionColumn.getColumn().setResizable(true);
		descriptionColumn.getColumn().setText("Description");
		descriptionColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TemplateCategoryDto dto = ((TemplateCategoryDto) element);
		    	return dto.getDescription();
			}
		});
		tableViewer.addDoubleClickListener( new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				TemplateCategoryDto dto = (TemplateCategoryDto) selection.getFirstElement();
				templateEditor.openPage(1);
				templateEditorTemplatesComposite.updateTemplatesByCategoryCode(dto);
			}
		});
	}

	@Override
	protected void checkSubclass() {
	}

	public ApplicationContext getAppContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext appContext) {
		this.applicationContext = appContext;
	}
	
	public void init() {
		this.httpService = this.applicationContext.getBean(HttpService.class);
		updateContent();
	}
	
	private void updateContent() {
		String resourcePath = String.format("/api/template-category");
		httpService.get(resourcePath, new ResponseHandler() {
			@Override
			public void completed(SimpleHttpResponse response) {
				try {
					ResData<List<TemplateCategoryDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<TemplateCategoryDto>>>() {});
					tableViewer.setInput(data.getBody());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	public void setTemplateEditorTemplatesComposite(TemplateEditorTemplatesComposite templateEditorTemplatesComposite) {
		this.templateEditorTemplatesComposite = templateEditorTemplatesComposite;
	}

	public void setTemplateEditor(TemplateEditor templateEditor) {
		this.templateEditor = templateEditor;
	}

}