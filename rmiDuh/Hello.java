//package tstrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    public String getMessage() throws RemoteException;
    public int suma(int a, int b) throws RemoteException ;
}