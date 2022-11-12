package peyto.ide.editors.generation;

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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.context.support.AbstractApplicationContext;

import peyto.ide.core.service.SimpleSourceCodeService;
import peyto.ide.core.service.types.SourceGroupType;

public class SourceCodeGenConfigComposite extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	private AbstractApplicationContext appContext;
	
	private SourceCodeGenEditor sourceCodeGenEditor;
	
	private ComboViewer comboViewer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SourceCodeGenConfigComposite(Composite parent, int style) {
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
}
