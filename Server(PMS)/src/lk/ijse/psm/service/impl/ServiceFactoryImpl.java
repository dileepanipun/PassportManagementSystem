package lk.ijse.psm.service.impl;

import lk.ijse.pms.service.ServiceFactory;
import lk.ijse.psm.service.custom.impl.ClientServiceImpl;
import lk.ijse.psm.service.custom.impl.PassportServiceImpl;
import lk.ijse.psm.service.custom.impl.ReceptionServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    private ServiceFactoryImpl() throws RemoteException {
    }

    private static ServiceFactoryImpl serviceFactory;

    public static ServiceFactoryImpl getInstance() throws Exception{
        if (serviceFactory == null){
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types){
            case CLIENT:
                return (T) new ClientServiceImpl();
            case PASSPORT:
                return (T) new PassportServiceImpl();
            case RECEPTION:
                return (T) new ReceptionServiceImpl();
            case POLICE:
                return (T) new PassportServiceImpl();
                default:
                    return null;
        }
    }
}
