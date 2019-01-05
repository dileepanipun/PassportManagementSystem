package lk.ijse.psm.server;

import lk.ijse.psm.service.impl.ServiceFactoryImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(5050);
            registry.rebind("passport", ServiceFactoryImpl.getInstance());
            System.out.println("Good to go....");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
