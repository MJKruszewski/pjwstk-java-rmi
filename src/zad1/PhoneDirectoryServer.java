package zad1;

import zad1.web.interfaces.PhoneDirectory;
import zad1.web.interfaces.PhoneDirectoryInterface;

import java.io.File;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PhoneDirectoryServer {

    public static void main(String[] args) {
        try {
            Registry registry = java.rmi.registry.LocateRegistry.createRegistry(PhoneDirectoryInterface.registryPort);

            PhoneDirectory ref = new PhoneDirectory(new File(PhoneDirectoryServer.class.getResource("phoneBook.csv").toURI()));
            UnicastRemoteObject.exportObject(ref, PhoneDirectoryInterface.servicePort);
            registry.rebind(PhoneDirectoryInterface.serviceName, ref );

            System.out.println("Server is ready.");
        } catch (RemoteException | URISyntaxException exc) {
            exc.printStackTrace();
        }
    }
}
