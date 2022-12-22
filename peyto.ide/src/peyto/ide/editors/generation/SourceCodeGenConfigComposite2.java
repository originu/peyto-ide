package peyto.ide.editors.generation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.progress.IProgressService;
import org.springframework.context.support.AbstractApplicationContext;

import peyto.ide.core.service.SimpleSourceCodeService;
import peyto.ide.core.service.types.SourceGroupType;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class SourceCodeGenConfigComposite2 extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	private AbstractApplicationContext appContext;
	
	private SourceCodeGenEditor sourceCodeGenEditor;
	
	private ComboViewer comboViewer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SourceCodeGenConfigComposite2(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		new Label(this, SWT.NONE);
		
		comboViewer = new ComboViewer(this, SWT.READ_ONLY);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
		        if (element instanceof SourceGroupType) {
		        	SourceGroupType type = (SourceGroupType) element;
		            return type.name();
		        } else {
		        	return "unknown";
		        }
		    }
		});
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		    @Override
		    public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		        if (selection.size() > 0){
		        	sourceCodeGenEditor.updatePages((SourceGroupType)selection.getFirstElement());
		        }
		    }
		});
		Combo combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 170;
		combo.setLayoutData(gd_combo);
		formToolkit.paintBordersFor(combo);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("ODPfrontend");
				IFolder folder = null;
				try {
					folder = createOrGetFolder(project, "src/main/java/sample/web/resource");
				} catch (CoreException e1) {
					e1.printStackTrace();
				}

				StringBuffer msg = new StringBuffer();
				msg.append("package sample.web.resource;").append("\n");
				msg.append("").append("\n");
				msg.append("public class HelloResource {").append("\n");
				msg.append("").append("\n");
				msg.append("}");
				
				IFile file = folder.getFile( "HelloResource.java" );
				if (file.exists()) {
					boolean openConfirm = MessageDialog.openConfirm(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
							"Title",
							"already existed. overwrite?"
							);
					if (openConfirm) {
						msg.append("\n");
						msg.append("/* hello sample */");
						try ( InputStream targetStream = new ByteArrayInputStream(msg.toString().getBytes()); ) {
							file.setContents(targetStream, true, true, null);
						} catch (IOException | CoreException e2) {
							e2.printStackTrace();
						}
					}
				} else {
					try ( InputStream targetStream = new ByteArrayInputStream(msg.toString().getBytes()); ) {
						file.create(targetStream, true, null);
					} catch (IOException | CoreException e2) {
						e2.printStackTrace();
					}
				}
				
				System.out.println();
			}
		});
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("New Button");
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
		SimpleSourceCodeService service = appContext.getBean(SimpleSourceCodeService.class);
		SourceGroupType[] values = SourceGroupType.values();
		comboViewer.setInput(values);
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
	
}
