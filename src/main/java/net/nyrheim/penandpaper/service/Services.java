package net.nyrheim.penandpaper.service;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.exception.UnregisteredServiceException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import static org.bukkit.plugin.ServicePriority.Normal;

public final class Services {

    private final PenAndPaper plugin;

    public Services(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    public <T> T get(Class<T> serviceClass) {
        RegisteredServiceProvider<T> serviceProvider = plugin.getServer().getServicesManager().getRegistration(serviceClass);
        if (serviceProvider == null) throw new UnregisteredServiceException(serviceClass);
        return serviceProvider.getProvider();
    }

    public <T> void register(Class<T> serviceClass, T service) {
        register(serviceClass, service, Normal);
    }

    public <T> void register(Class<T> serviceClass, T service, ServicePriority priority) {
        plugin.getServer().getServicesManager().register(serviceClass, service, plugin, priority);
    }

}
