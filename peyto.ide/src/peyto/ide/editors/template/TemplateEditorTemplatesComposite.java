package peyto.ide.editors.template;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;

import peyto.ide.core.data.ResData;
import peyto.ide.core.data.TemplateCategoryDto;
import peyto.ide.core.data.TemplateDto;
import peyto.ide.core.service.HttpService;
import peyto.ide.core.service.ResponseHandler;
import peyto.ide.core.util.JsonUtil;

public class TemplateEditorTemplatesComposite extends Composite {

	private ApplicationContext applicationContext;

	private HttpService httpService = null;
	
	private Text categoryCodeText;
	private Text categoryNameText;
	private ComboViewer revisionComboViewer;
	private Text revisionNameText;
	private StyledText contentStyledText;
	
	private TemplateCategoryDto templateCategoryDto;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TemplateEditorTemplatesComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout(5, false);
		setLayout(gridLayout);
		
		Label categoryCodeLabel = new Label(this, SWT.NONE);
		categoryCodeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryCodeLabel.setText("CategoryCode");
		
		categoryCodeText = new Text(this, SWT.BORDER);
		categoryCodeText.setEditable(false);
		GridData gd_categoryCodeText = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_categoryCodeText.widthHint = 250;
		categoryCodeText.setLayoutData(gd_categoryCodeText);
		
		Label categoryNameLabel = new Label(this, SWT.NONE);
		categoryNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryNameLabel.setText("CategoryName");
		
		categoryNameText = new Text(this, SWT.BORDER);
		categoryNameText.setEditable(false);
		GridData gd_categoryNameText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_categoryNameText.widthHint = 250;
		categoryNameText.setLayoutData(gd_categoryNameText);
		
		Label revisionLabel = new Label(this, SWT.NONE);
		revisionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		revisionLabel.setText("Revision");
		
		revisionComboViewer = new ComboViewer(this, SWT.NONE | SWT.READ_ONLY);
		revisionComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		revisionComboViewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
		    	TemplateDto dto = (TemplateDto) element;
				return dto.isLast()
						? String.format("lastest(%d)", dto.getRevision())
						: String.valueOf(dto.getRevision()); 
		    }
		});
		revisionComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		    @Override
		    public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		        if (selection.size() > 0){
		        	TemplateDto dto = (TemplateDto)selection.getFirstElement();
		        	updateContent(dto.getId());
		        }
		    }
		});
		Combo revisionCombo = revisionComboViewer.getCombo();
		GridData gd_revisionCombo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_revisionCombo.widthHint = 100;
		revisionCombo.setLayoutData(gd_revisionCombo);
		
		Button refreshButton = new Button(this, SWT.NONE);
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (templateCategoryDto != null) {
					updateTemplatesByCategoryCode(templateCategoryDto);
				}
			}
		});
		refreshButton.setText("Refresh");
		
		Label revisionNameLabel = new Label(this, SWT.NONE);
		revisionNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		revisionNameLabel.setText("RevisionName");
		
		revisionNameText = new Text(this, SWT.BORDER);
		revisionNameText.setEditable(false);
		GridData gd_revisionNameText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_revisionNameText.widthHint = 250;
		revisionNameText.setLayoutData(gd_revisionNameText);
		
		contentStyledText = new StyledText(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		contentStyledText.setEditable(false);
		GridData gd_contentStyledText = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1);
		gd_contentStyledText.widthHint = 698;
		contentStyledText.setLayoutData(gd_contentStyledText);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ApplicationContext getAppContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext appContext) {
		this.applicationContext = appContext;
	}
	
	public void init() {
		this.httpService = this.applicationContext.getBean(HttpService.class);
	}
	
	public void updateTemplatesByCategoryCode(TemplateCategoryDto templateCategoryDto) {
		this.templateCategoryDto = templateCategoryDto;		
		String resourcePath = String.format("/api/template?categoryCode=%s", templateCategoryDto.getCategoryCode());
		httpService.callAsync(resourcePath, new ResponseHandler() {
			@Override
			public void completed(SimpleHttpResponse response) {
				try {
					categoryCodeText.setText(templateCategoryDto.getCategoryCode());
					categoryNameText.setText(templateCategoryDto.getCategoryName());
					ResData<List<TemplateDto>> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<List<TemplateDto>>>() {});
					if (data.getBody().size() > 0) {
						TemplateDto dto = data.getBody().get(0);
						data.getBody().get(0).setLast(true);
						revisionComboViewer.setInput(data.getBody());
						revisionComboViewer.getCombo().select(0);
						revisionNameText.setText(dto.getRevisionName());
						contentStyledText.setText(dto.getContent());
					} else {
						revisionComboViewer.getCombo().removeAll();
						revisionNameText.setText("");
						contentStyledText.setText("");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
	}
	
	private void updateContent(long id) {
		String resourcePath = String.format("/api/template/%s", id);
		httpService.callAsync(resourcePath, new ResponseHandler() {
			@Override
			public void completed(SimpleHttpResponse response) {
				try {
					ResData<TemplateDto> data = JsonUtil.MAPPER.readValue(response.getBodyText(), new TypeReference<ResData<TemplateDto>>() {});
					revisionNameText.setText(data.getBody().getRevisionName());
					contentStyledText.setText(data.getBody().getContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
	}

}