package peyto.ide;

import java.util.Hashtable;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import peyto.ide.core.service.HttpService;

/**
 * The activator class controls the plug-in life cycle
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
		
		HttpService	service = new HttpService();
		service.startup();
		registerService = context.registerService( HttpService.class.getName(), service, new Hashtable<>());
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		ServiceReference<HttpService> serviceReference = context.getServiceReference(HttpService.class);
		HttpService service = context.getService( serviceReference);
		service.shutdown();
		
		registerService.unregister();
		
		context.ungetService(serviceReference);
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
