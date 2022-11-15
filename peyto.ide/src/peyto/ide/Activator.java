package peyto.ide;

import java.util.Hashtable;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * The activator class controls the plug-in life cycle
 * https://www.vogella.com/tutorials/Eclipse4ContextUsage/article.html
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "peyto.ide"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private ServiceRegistration<?> registerService; 
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		AbstractApplicationContext	appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		registerService = context.registerService( AbstractApplicationContext.class.getName(), appContext, new Hashtable<>());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		ServiceReference<AbstractApplicationContext> serviceReference = context.getServiceReference(AbstractApplicationContext.class);
		AbstractApplicationContext appContext = context.getService(serviceReference);
		appContext.close();
		context.ungetService(serviceReference);
		registerService.unregister();
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
