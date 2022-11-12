package peyto.ide.editors.generation;

import java.util.ArrayList;

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

public class SourceCodeGenConfigComposite extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	private AbstractApplicationContext appContext;
	
	private SourceCodeGenEditor sourceCodeGenEditor;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SourceCodeGenConfigComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		new Label(this, SWT.NONE);
		
		ComboViewer comboViewer = new ComboViewer(this, SWT.READ_ONLY);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {
		    @Override
		    public String getText(Object element) {
//		        if (element instanceof Person) {
//		            Person person = (Person) element;
//		            return person.getFirstName();
//		        }
		        return super.getText(element);
		    }
		});
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		    @Override
		    public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		        if (selection.size() > 0){
		        	sourceCodeGenEditor.updatePages((String)selection.getFirstElement());
		        }
		    }
		});
		ArrayList<String>	items = new ArrayList<>();
		items.add("mybatis");
		items.add("spring jpa");
		items.add("sql");
		comboViewer.setInput(items);
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
}
