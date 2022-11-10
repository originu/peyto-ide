package peyto.ide.editors.java.mybatis;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class JavaMybatisGenConfigComposite extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public JavaMybatisGenConfigComposite(Composite parent, int style) {
		super(parent, style);
		
		ExpandableComposite xpndblcmpstNewExpandablecomposite = formToolkit.createExpandableComposite(this, ExpandableComposite.TWISTIE);
		xpndblcmpstNewExpandablecomposite.setEnabled(false);
		xpndblcmpstNewExpandablecomposite.setTouchEnabled(true);
		xpndblcmpstNewExpandablecomposite.setBounds(10, 10, 430, 21);
		formToolkit.paintBordersFor(xpndblcmpstNewExpandablecomposite);
		xpndblcmpstNewExpandablecomposite.setText("New ExpandableComposite");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
