package lk.ijse.psm.service.custom.impl;

import lk.ijse.pms.dto.ClientDTO;
import lk.ijse.pms.observer.Observer;
import lk.ijse.pms.service.custom.ClientService;
import lk.ijse.psm.business.BusinessFactory;
import lk.ijse.psm.business.custom.ClientBusiness;
import lk.ijse.psm.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl extends UnicastRemoteObject implements ClientService {

    ClientBusiness clientBusiness = BusinessFactory.getInstance().getBusinessFor(BusinessFactory.BusinessTypes.CLIENT);
    private static List<Observer> allCustomerObs = new ArrayList<>();
    public static ReservationImpl<ClientService> clientServiceReservation = new ReservationImpl<>();

    public ClientServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addClient(ClientDTO clientDTO) throws Exception {

        boolean resp = clientBusiness.addClient(clientDTO);
        notifyAllObservers(clientDTO.getNic() + " client is added..!");
        return resp;
    }

    @Override
    public boolean updateClient(ClientDTO clientDTO) throws Exception {
        return clientBusiness.updateClient(clientDTO);
    }

    @Override
    public boolean deleteClient(ClientDTO clientDTO) throws Exception {
        return clientBusiness.deleteClient(clientDTO);
    }

    @Override
    public ClientDTO searchClient(String id) throws Exception {
        return clientBusiness.searchClient(id);
    }

    @Override
    public List<ClientDTO> getAllClients() throws Exception {
        return clientBusiness.getAllClients();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allCustomerObs.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allCustomerObs.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer observer : allCustomerObs){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        observer.update(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return clientServiceReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return clientServiceReservation.release(id,this);
    }
}
