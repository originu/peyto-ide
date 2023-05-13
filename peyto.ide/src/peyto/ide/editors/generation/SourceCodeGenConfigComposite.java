package peyto.ide.editors.generation;

import java.util.ArrayList;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.context.ApplicationContext;

import peyto.ide.core.model.GroupModel;
import peyto.ide.core.model.ItemModel;
import peyto.ide.core.model.ManifestModel;
import peyto.ide.core.model.MetadataModel;
import peyto.ide.editors.generation.ui.ManifestContentProvider;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SourceCodeGenConfigComposite extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	private ApplicationContext appContext;
	
	private SourceCodeGenEditor sourceCodeGenEditor;
	
	private TreeViewer treeViewer;

	private ManifestModel manifestModel;
	
	private Composite templateItemComposite;
	private Text schemaText;
	private Text tableText;
	
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SourceCodeGenConfigComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		GridData gd_sashForm = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_sashForm.widthHint = 864;
		sashForm.setLayoutData(gd_sashForm);
		formToolkit.adapt(sashForm);
		formToolkit.paintBordersFor(sashForm);
		
		Composite templateGroupComposite = new Composite(sashForm, SWT.NONE);
		formToolkit.adapt(templateGroupComposite);
		formToolkit.paintBordersFor(templateGroupComposite);
		templateGroupComposite.setLayout(new GridLayout(1, false));
		
		treeViewer = new TreeViewer(templateGroupComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		treeViewer.setContentProvider(new ManifestContentProvider());
		Tree tree = treeViewer.getTree();
		GridData gd_tree = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_tree.widthHint = 183;
		tree.setLayoutData(gd_tree);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setHeaderBackground( Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND) );
		formToolkit.paintBordersFor(tree);
		treeViewer.setContentProvider(new ManifestContentProvider());
		
		treeViewer.addDoubleClickListener( new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection	selection	= (IStructuredSelection)event.getSelection();
				Object firstElement = selection.getFirstElement();
				System.out.println();
			}
		});
		
		// add columns of table
		TreeViewerColumn manifestColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		manifestColumn.getColumn().setWidth(160);
		manifestColumn.getColumn().setResizable(true);
		manifestColumn.getColumn().setText("manifest");
		manifestColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof ManifestModel) {
					return "template";
				} else if (element instanceof GroupModel) {
					return ((GroupModel) element).getName();
				} else if (element instanceof ItemModel) {
					return ((ItemModel) element).getName();
				} else {
					System.out.println(element);
					return "unknown";
				}
			}
		});
		
		templateItemComposite = new Composite(sashForm, SWT.NONE);
		formToolkit.adapt(templateItemComposite);
		formToolkit.paintBordersFor(templateItemComposite);
		RowLayout rl_templateItemComposite = new RowLayout(SWT.HORIZONTAL);
		rl_templateItemComposite.pack = false;
		templateItemComposite.setLayout(rl_templateItemComposite);
		
		Composite composite = new Composite(templateItemComposite, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new RowData(600, 160));
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		Label schemaLabel = new Label(composite, SWT.NONE);
		schemaLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(schemaLabel, true, true);
		schemaLabel.setText("Schema");
		
		schemaText = new Text(composite, SWT.BORDER);
		schemaText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(schemaText, true, true);
		
		Label tableLabel = new Label(composite, SWT.NONE);
		tableLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(tableLabel, true, true);
		tableLabel.setText("Table");
		
		tableText = new Text(composite, SWT.BORDER);
		tableText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(tableText, true, true);
		new Label(composite, SWT.NONE);
		
		Button generateButton = new Button(composite, SWT.NONE);
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generate();                                                                                                                                                                                                                           
			}
		});
		formToolkit.adapt(generateButton, true, true);
		generateButton.setText("Generate");
		sashForm.setWeights(new int[] {1, 4});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setSourceCodeGenEditor(SourceCodeGenEditor sourceCodeGenEditor) {
		this.sourceCodeGenEditor = sourceCodeGenEditor;		
	}

	public void setApplicationContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	public void init() {
//		SimpleSourceCodeService service = appContext.getBean(SimpleSourceCodeService.class);
//		SourceGroupType[] values = SourceGroupType.values();
		treeViewer.setInput(manifestModel);
		treeViewer.expandAll();
		
		
		MetadataModel metadata = manifestModel.getMetadata();
		ArrayList<GroupModel> groups = metadata.getGroups();
		for (GroupModel groupModel : groups) {
			ArrayList<ItemModel> items = groupModel.getItems();
			for (ItemModel itemModel : items) {
				TemplateConfigComposite comp = new TemplateConfigComposite(templateItemComposite, SWT.None);
				comp.setData(
						itemModel.getName(), 
						itemModel.getSpec().getProject().getName(),
						itemModel.getSpec().getTarget().getPath(), 
						itemModel.getSpec().getTarget().getName(), 
						itemModel.getSpec().getTarget().getExtension(), 
						itemModel.getSpec().getTarget().getPackageName(),
						itemModel.getSpec().getTemplate().getCategoryCode(),
						itemModel.getSpec().getTemplate().getRevision()
						);
			}
		}
	}
		
	// ========================================================================
	private IFolder createOrGetFolder(IProject project, String path) throws CoreException {
		String[] folderNames = path.split("/");
		IFolder folder = null;
		String folderPath = "";
		for (String folderName : folderNames) {
			folderPath = folderPath + folderName;
			folder = project.getFolder(folderPath);
			if (!folder.exists()) {
				try {
					folder.create(true, true, null);
				} catch (CoreException e) {
					e.printStackTrace();
					throw e;
				}
			}
			folderPath = folderPath + "/";
		}
		return folder;
	}

	public void setManifestModel(ManifestModel manifestModel) {
		this.manifestModel = manifestModel;
	}
	
	private void generate() {
		String schema = schemaText.getText();
		String table = tableText.getText();
				
		
		MetadataModel metadata = manifestModel.getMetadata();
		ArrayList<GroupModel> groups = metadata.getGroups();
		for (GroupModel groupModel : groups) {
			ArrayList<ItemModel> items = groupModel.getItems();
			for (ItemModel itemModel : items) {
				TemplateConfigComposite comp = new TemplateConfigComposite(templateItemComposite, SWT.None);
				comp.setData(
						itemModel.getName(), 
						itemModel.getSpec().getProject().getName(),
						itemModel.getSpec().getTarget().getPath(), 
						itemModel.getSpec().getTarget().getName(), 
						itemModel.getSpec().getTarget().getExtension(), 
						itemModel.getSpec().getTarget().getPackageName(),
						itemModel.getSpec().getTemplate().getCategoryCode(),
						itemModel.getSpec().getTemplate().getRevision()
						);
			}
		}
	}
}
