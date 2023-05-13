package peyto.ide.editors.generation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TemplateConfigComposite extends Composite {

	private Text nameText;
	private Text projectNameText;
	private Text targetPathText;
	private Text targetNameText;
	private Text targetExtensionText;
	private Text targetPackageNameText;
	private Text templateCategoryCodeText;
	private Text templateRevisionText;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TemplateConfigComposite(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setLayout(new GridLayout(2, false));
		
		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		nameLabel.setText("Name");
		
		nameText = new Text(this, SWT.BORDER);
		nameText.setEditable(false);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label projectNameLabel = new Label(this, SWT.NONE);
		projectNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		projectNameLabel.setText("ProjectName");
		
		projectNameText = new Text(this, SWT.BORDER);
		projectNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		projectNameText.setEditable(false);
		
		Label targetPathLabel = new Label(this, SWT.NONE);
		targetPathLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		targetPathLabel.setText("TargetPath");
		
		targetPathText = new Text(this, SWT.BORDER);
		targetPathText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		targetPathText.setEditable(false);
		
		Label targetNameLabel = new Label(this, SWT.NONE);
		targetNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		targetNameLabel.setText("TargetName");
		
		targetNameText = new Text(this, SWT.BORDER);
		targetNameText.setEditable(false);
		targetNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label targetExtensionLabel = new Label(this, SWT.NONE);
		targetExtensionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		targetExtensionLabel.setText("TargetExtension");
		
		targetExtensionText = new Text(this, SWT.BORDER);
		targetExtensionText.setEditable(false);
		targetExtensionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label targetPackageNameLabel = new Label(this, SWT.NONE);
		targetPackageNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		targetPackageNameLabel.setText("TargetPackageName");
		
		targetPackageNameText = new Text(this, SWT.BORDER);
		targetPackageNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		targetPackageNameText.setEditable(false);
		
		Label templateCategoryCodeLabel = new Label(this, SWT.NONE);
		templateCategoryCodeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		templateCategoryCodeLabel.setText("TemplateCategoryCode");
		
		templateCategoryCodeText = new Text(this, SWT.BORDER);
		templateCategoryCodeText.setEditable(false);
		templateCategoryCodeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label templateRevisionLabel = new Label(this, SWT.NONE);
		templateRevisionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		templateRevisionLabel.setText("TemplateRevision");
		
		templateRevisionText = new Text(this, SWT.BORDER);
		templateRevisionText.setEditable(false);
		templateRevisionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		setLayoutData( new RowData(600, 215));
	}

	@Override
	protected void checkSubclass() {

	}
	
	public void setData(
			String name,
			String projectName,
			String targetPath,
			String targetName,
			String targetExtension,
			String targetPackageName,
			String templateCategoryCode,
			int templateRevision) {
		nameText.setText(name);
		projectNameText.setText(projectName);
		targetPathText.setText(targetPath);
		targetNameText.setText(targetName);
		targetExtensionText.setText(targetExtension);
		targetPackageNameText.setText(targetPackageName);
		templateCategoryCodeText.setText(templateCategoryCode);
		templateRevisionText.setText(String.valueOf(templateRevision));
	}

}