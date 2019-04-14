package zad1.gui;

import zad1.web.interfaces.PhoneDirectoryInterface;

import javax.rmi.PortableRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

class PhoneDirectoryClient {

    private Registry registry = java.rmi.registry.LocateRegistry.getRegistry(PhoneDirectoryInterface.registryPort);

    PhoneDirectoryClient() throws RemoteException {
    }

    PhoneDirectoryInterface preparePhoneDirectoryService() throws RemoteException, NotBoundException {
        return (PhoneDirectoryInterface) PortableRemoteObject.narrow(registry.lookup(PhoneDirectoryInterface.serviceName), PhoneDirectoryInterface.class);
    }
}