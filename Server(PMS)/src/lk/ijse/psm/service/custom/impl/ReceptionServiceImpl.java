package lk.ijse.psm.service.custom.impl;

import lk.ijse.pms.dto.RecepitionDTO;
import lk.ijse.pms.observer.Observer;
import lk.ijse.pms.service.custom.ReceptionService;
import lk.ijse.psm.business.BusinessFactory;
import lk.ijse.psm.business.custom.RecepitonBusineess;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ReceptionServiceImpl extends UnicastRemoteObject implements ReceptionService {

    RecepitonBusineess recepitonBusineess = BusinessFactory.getInstance().getBusinessFor(BusinessFactory.BusinessTypes.RECEPTION);

    public ReceptionServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addReception(RecepitionDTO recepitionDTO) throws Exception {
        return recepitonBusineess.addReception(recepitionDTO);
    }

    @Override
    public boolean updateReception(RecepitionDTO recepitionDTO) throws Exception {
        return recepitonBusineess.updateReception(recepitionDTO);
    }

    @Override
    public boolean deleteReception(String id) throws Exception {
        return recepitonBusineess.deleteReception(id);
    }

    @Override
    public RecepitionDTO searchReception(String id) throws Exception {
        return recepitonBusineess.searchReception(id);
    }

    @Override
    public List<RecepitionDTO> getAllReceptions() throws Exception {
        return recepitonBusineess.getAllReceptions();
    }

    @Override
    public void register(Observer observer) throws Exception {

    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return false;
    }

    @Override
    public boolean released(Object id) throws Exception {
        return false;
    }
}
