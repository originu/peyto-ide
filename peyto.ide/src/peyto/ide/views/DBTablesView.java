package peyto.ide.views;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.springframework.context.support.AbstractApplicationContext;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;


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

public class DBTablesView extends ViewPart {

	public static final String ID = DBTablesView.class.getName(); //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	@Inject
	private AbstractApplicationContext appContext;
	
	public DBTablesView() {
	}
	
	@PostConstruct
	public void startup() {
	}
	
	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		toolkit.adapt(sashForm);
		toolkit.paintBordersFor(sashForm);
		
		DBTablesViewTablesComposite dbTablesViewTablesComposite = new DBTablesViewTablesComposite(sashForm, SWT.NONE);
		dbTablesViewTablesComposite.setApplicationContext(appContext);
		dbTablesViewTablesComposite.init();
		toolkit.adapt(dbTablesViewTablesComposite);
		toolkit.paintBordersFor(dbTablesViewTablesComposite);
		
		DBTablesViewColumnsComposite dbTablesViewColumnsComposite = new DBTablesViewColumnsComposite(sashForm, SWT.NONE);
		dbTablesViewColumnsComposite.setApplicationContext(appContext);
		dbTablesViewColumnsComposite.init();

		dbTablesViewTablesComposite.setDBTablesViewColumnsComposite(dbTablesViewColumnsComposite);

		toolkit.adapt(dbTablesViewColumnsComposite);
		toolkit.paintBordersFor(dbTablesViewColumnsComposite);
		sashForm.setWeights(new int[] { 10, 25 });

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
