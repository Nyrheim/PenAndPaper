package net.nyrheim.penandpaper.exception;

public class UnregisteredServiceException extends RuntimeException {

    private final Class<?> serviceClass;

    public UnregisteredServiceException(Class<?> serviceClass) {
        super("No service was registered for " + serviceClass.getTypeName());
        this.serviceClass = serviceClass;
    }
}
