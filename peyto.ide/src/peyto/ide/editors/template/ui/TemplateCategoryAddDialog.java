package peyto.ide.editors.template.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class TemplateCategoryAddDialog extends Dialog {

	private Text categoryCodeText;
	private Text categoryNameText;
	private Text descriptionText;

	public TemplateCategoryAddDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		
		Label categoryCodeLabel = new Label(container, SWT.NONE);
		categoryCodeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryCodeLabel.setText("**CategoryCode");
		
		categoryCodeText = new Text(container, SWT.BORDER);
		categoryCodeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label categoryNameLabel = new Label(container, SWT.NONE);
		categoryNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryNameLabel.setText("**CategoryName");
		
		categoryNameText = new Text(container, SWT.BORDER);
		categoryNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label descriptionLabel = new Label(container, SWT.NONE);
		descriptionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		descriptionLabel.setText("Description");
		
		descriptionText = new Text(container, SWT.BORDER);
		descriptionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return container;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Add a Templcate Category");
	}

	public String getCategoryCode() {
		return categoryCodeText.getText();
	}
	
	public String getCategoryName() {
		return categoryNameText.getText();
	}
	
	public String getDescription() {
		return descriptionText.getText();
	}
	
}
