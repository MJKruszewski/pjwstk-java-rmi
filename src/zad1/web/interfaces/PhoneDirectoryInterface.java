package zad1.web.interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PhoneDirectoryInterface extends Remote {
    String serviceName = "PhoneDirectoryService";
    Integer registryPort = 1099;
    Integer servicePort = 4004;

    public String getPhoneNumber(String name) throws RemoteException ;

    public boolean addPhoneNumber(String name, String num) throws RemoteException;

    public boolean replacePhoneNumber(String name, String num) throws RemoteException ;
}
