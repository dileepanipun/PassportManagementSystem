package lk.ijse.pms.proxy;

import lk.ijse.pms.service.ServiceFactory;
import lk.ijse.pms.service.custom.ClientService;
import lk.ijse.pms.service.custom.PassportService;
import lk.ijse.pms.service.custom.PoliceService;
import lk.ijse.pms.service.custom.ReceptionService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyHandeler implements ServiceFactory {

    private static ProxyHandeler proxyHandeler;

    private ClientService clientService;
    private PassportService passportService;
    private PoliceService policeService;
    private ReceptionService receptionService;

    public static ProxyHandeler getInstance() throws Exception{
        if (proxyHandeler == null){
            proxyHandeler = new ProxyHandeler();
        }
        return proxyHandeler;
    }

    public ProxyHandeler(){

        try {
            ServiceFactory serviceFactory = (ServiceFactory) Naming.lookup("rmi://127.0.0.1:5050/passport");
            clientService = serviceFactory.getSuperService(ServiceTypes.CLIENT);
            passportService = serviceFactory.getSuperService(ServiceTypes.PASSPORT);
//            policeService = serviceFactory.getSuperService(ServiceTypes.POLICE);
            receptionService = serviceFactory.getSuperService(ServiceTypes.RECEPTION);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types){
            case CLIENT:
                return (T) clientService;
            case RECEPTION:
                return (T) receptionService;
//            case POLICE:
//                return (T) policeService;
            case PASSPORT:
                return (T) passportService;
                default:
                    return null;
        }
    }
}
