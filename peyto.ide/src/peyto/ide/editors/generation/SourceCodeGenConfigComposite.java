package peyto.ide.editors.generation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import peyto.ide.core.data.DBTableDto;
import peyto.ide.core.data.MessageFieldDto;
import peyto.ide.core.model.GroupModel;
import peyto.ide.core.model.ItemModel;
import peyto.ide.core.model.ManifestModel;
import peyto.ide.core.service.SimpleSourceCodeService;
import peyto.ide.core.service.types.SourceGroupType;
import peyto.ide.editors.generation.ui.ManifestContentProvider;
import peyto.ide.editors.message.ui.MessageFieldTreeElement;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;

public class SourceCodeGenConfigComposite extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	private AbstractApplicationContext appContext;
	
	private SourceCodeGenEditor sourceCodeGenEditor;
	
	private TreeViewer treeViewer;

	private ManifestModel manifestModel; 
	
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
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		formToolkit.adapt(composite_1);
		formToolkit.paintBordersFor(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		treeViewer = new TreeViewer(composite_1, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
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
		TreeViewerColumn messageFieldNameColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		messageFieldNameColumn.getColumn().setWidth(160);
		messageFieldNameColumn.getColumn().setResizable(true);
		messageFieldNameColumn.getColumn().setText("template");
		messageFieldNameColumn.setLabelProvider(new ColumnLabelProvider() {
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
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		sashForm.setWeights(new int[] {1, 4});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setJavaMybatisGenEditor(SourceCodeGenEditor javaMybatisGenEditor) {
		this.sourceCodeGenEditor = javaMybatisGenEditor;		
	}

	public void setApplicationContext(AbstractApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	public void init() {
//		SimpleSourceCodeService service = appContext.getBean(SimpleSourceCodeService.class);
//		SourceGroupType[] values = SourceGroupType.values();
		treeViewer.setInput(manifestModel);
		treeViewer.expandAll();
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
	
}
