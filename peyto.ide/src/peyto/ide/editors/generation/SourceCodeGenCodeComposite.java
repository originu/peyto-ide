package peyto.ide.editors.generation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class SourceCodeGenCodeComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SourceCodeGenCodeComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		new Label(this, SWT.NONE);
		
		StyledText styledText = new StyledText(this, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));


		ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(mylistener);
		
//		IProject currentProject = getCurrentProject();

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public IProject getCurrentProject() {

//		ISelectionService selectionService =  Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();
		ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(mylistener);
		ISelection selection = selectionService.getSelection();
		System.out.println(">> " + selection);
		IProject project = null;
		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection).getFirstElement();

			System.out.println(">> " + element);
			
//			if (element instanceof IResource) {
//				project = ((IResource) element).getProject();
//			} else if (element instanceof PackageFragmentRootContainer) {
//				IJavaProject jProject = ((PackageFragmentRootContainer) element).getJavaProject();
//				project = jProject.getProject();
//			} else if (element instanceof IJavaElement) {
//				IJavaProject jProject = ((IJavaElement) element).getJavaProject();
//				project = jProject.getProject();
//			}
		}
		return project;
	}
	
    private ISelectionListener mylistener = new ISelectionListener() {
        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
//        if (sourcepart != MyView.this &&
//            selection instanceof IStructuredSelection) {
//            doSomething(((IStructuredSelection) selection).toList());
//            }
//        }
        	
        	if( selection instanceof IStructuredSelection) {
        		Object firstElement = ((IStructuredSelection) selection).getFirstElement();
        		System.err.println(">>>> " + firstElement );
        	}
        	
        	
//        	if( selection instanceof TreeSelection ) {
//        		Object firstElement = ((TreeSelection)selection).getFirstElement();
//        		if (firstElement instanceof IResource) {
//        			IProject project = ((IResource) firstElement).getProject();
//        			System.err.println(">>>> " + firstElement );
//        		}
//         		System.err.println(">>>> " + firstElement );
//        	}
        }
    };
	
}
