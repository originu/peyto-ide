package peyto.ide.views;


import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import peyto.ide.core.service.HttpService;
import peyto.ide.views.erd.DBColumnListUI;
import peyto.ide.views.erd.DBTableListUI;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class DBErdView extends ViewPart {

	public static final String ID = DBErdView.class.getName(); //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	@Inject
	private HttpService httpService;
	
	public DBErdView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(container, SWT.NONE);
		toolkit.adapt(sashForm);
		toolkit.paintBordersFor(sashForm);
		
		DBTableListUI dbTableListUIComposite = new DBTableListUI(sashForm, SWT.NONE);
		dbTableListUIComposite.setHttpService(httpService);
		toolkit.adapt(dbTableListUIComposite);
		toolkit.paintBordersFor(dbTableListUIComposite);
		
		DBColumnListUI dbColumnListUIComposite = new DBColumnListUI(sashForm, SWT.NONE);
		dbColumnListUIComposite.setHttpService(httpService);
		dbTableListUIComposite.setDBColumnListUI(dbColumnListUIComposite);
		toolkit.adapt(dbColumnListUIComposite);
		toolkit.paintBordersFor(dbColumnListUIComposite);
		sashForm.setWeights(new int[] {332, 659});

		createActions();
		// Uncomment if you wish to add code to initialize the toolbar
		// initializeToolBar();
		initializeMenu();
		
	}

	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
//		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
//		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
//		httpService.callAsync( new IResponseHandler<DBSchemaDto>() {
//			@Override
//			public void completed(DBSchemaDto response) {
//				
//			}
//		});
	}
}
