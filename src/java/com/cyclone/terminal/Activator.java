package com.cyclone.terminal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public final class Activator extends AbstractUIPlugin
{
    // The shared instance
    private static Activator s_plugin;

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext a_context) throws Exception
    {
        super.start(a_context);
        s_plugin = this;
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext a_context) throws Exception
    {
        s_plugin = null;
        super.stop(a_context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault()
    {
        return s_plugin;
    }
}
