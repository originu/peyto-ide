package peyto.ide.editors.template;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.springframework.context.support.AbstractApplicationContext;

public class TemplateEditor extends MultiPageEditorPart {

	private AbstractApplicationContext appContext;
	
	private TemplateEditorTemplatesComposite templateEditorTemplatesComposite;
	
	public TemplateEditor() {
		super();
	}
	
	protected void createPages() {
		TemplateEditorCategoriesComposite templateEditorCategoriesComposite = new TemplateEditorCategoriesComposite( getContainer(), SWT.NONE);
		templateEditorCategoriesComposite.setApplicationContext(appContext);
		templateEditorCategoriesComposite.setTemplateEditor(this);
		templateEditorCategoriesComposite.init();
		int index = addPage(templateEditorCategoriesComposite );
		setPageText(index, "Category");
		
		templateEditorTemplatesComposite = new TemplateEditorTemplatesComposite(getContainer(), SWT.NONE);
		templateEditorCategoriesComposite.setTemplateEditorTemplatesComposite(templateEditorTemplatesComposite);
		templateEditorTemplatesComposite.setApplicationContext(appContext);
		templateEditorTemplatesComposite.init();
		index = addPage(templateEditorTemplatesComposite );
		setPageText(index, "Template");
		
//			ErrorDialog.openError(
//				getSite().getShell(),
//				"Error creating nested text editor",
//				null,
//				e.getStatus());
		
	}
	
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
		// DI
		appContext = getSite().getService(AbstractApplicationContext.class);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
//			sortWords();
		}
	}
	
	public void openPage(int pageIndex) {
		super.setActivePage(pageIndex);
	}
}
