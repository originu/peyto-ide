package peyto.ide.editors.template.ui;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TemplateCategoryAddOrUpdateDialog extends Dialog {

	private Text categoryCodeText;
	private Text categoryNameText;
	private Text descriptionText;
	
	private String categoryCode = "";
	private String categoryName = "";
	private String description = "";
	
	private boolean isUpdateMode = false;

	/**
	 * @wbp.parser.constructor
	 */
	public TemplateCategoryAddOrUpdateDialog(Shell parentShell) {
		super(parentShell);
		isUpdateMode = false;
	}
	
	public TemplateCategoryAddOrUpdateDialog(
			Shell parentShell,
			String categoryCode,
			String categoryName,
			String description) {
		super(parentShell);
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.description = StringUtils.defaultString(description, "");
		isUpdateMode = true;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		
		Label categoryCodeLabel = new Label(container, SWT.NONE);
		categoryCodeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryCodeLabel.setText("**CategoryCode");
		
		categoryCodeText = new Text(container, SWT.BORDER);
		GridData gd_categoryCodeText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_categoryCodeText.widthHint = 255;
		categoryCodeText.setLayoutData(gd_categoryCodeText);
		categoryCodeText.setText(categoryCode);
		categoryCodeText.setEditable(!isUpdateMode);
		
		Label categoryNameLabel = new Label(container, SWT.NONE);
		categoryNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryNameLabel.setText("**CategoryName");
		
		categoryNameText = new Text(container, SWT.BORDER);
		GridData gd_categoryNameText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_categoryNameText.widthHint = 255;
		categoryNameText.setLayoutData(gd_categoryNameText);
		categoryNameText.setText(categoryName);
		
		Label descriptionLabel = new Label(container, SWT.NONE);
		descriptionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		descriptionLabel.setText("Description");
		
		descriptionText = new Text(container, SWT.BORDER);
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_descriptionText.widthHint = 255;
		descriptionText.setLayoutData(gd_descriptionText);
		descriptionText.setText(description);
		
		return container;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if(isUpdateMode) {
			newShell.setText("Update a Templcate Category");
		} else {
			newShell.setText("Add a Templcate Category");
		}
	}
	
	@Override
	protected void okPressed() {
		categoryCode = categoryCodeText.getText();
		categoryName = categoryNameText.getText();
		description = descriptionText.getText();
		super.okPressed();
	}

	public String getCategoryCode() {
		return categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public String getDescription() {
		return description;
	}
	
}
