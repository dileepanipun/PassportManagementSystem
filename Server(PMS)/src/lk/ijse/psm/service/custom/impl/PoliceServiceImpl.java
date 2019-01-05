package lk.ijse.psm.service.custom.impl;

import lk.ijse.pms.dto.PoliceDTO;
import lk.ijse.pms.observer.Observer;
import lk.ijse.pms.service.custom.PoliceService;
import lk.ijse.psm.business.BusinessFactory;
import lk.ijse.psm.business.custom.PoliceBusiness;
import lk.ijse.psm.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PoliceServiceImpl extends UnicastRemoteObject implements PoliceService {

    PoliceBusiness policeBusiness = BusinessFactory.getInstance().getBusinessFor(BusinessFactory.BusinessTypes.POLICE);
    private static ArrayList<Observer> allPassportObs = new ArrayList<>();
    public static ReservationImpl<PoliceService> policeServiceReservation =  new ReservationImpl<>();

    protected PoliceServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addPolice(PoliceDTO policeDTO) throws Exception {
        return policeBusiness.addPolice(policeDTO);
    }

    @Override
    public boolean updatePolice(PoliceDTO policeDTO) throws Exception {
        return policeBusiness.updatePolice(policeDTO);
    }

    @Override
    public boolean deletePolice(String id) throws Exception {
        return policeBusiness.deletePolice(id);
    }

    @Override
    public PoliceDTO searchPolice(String id) throws Exception {
        return policeBusiness.searchPolice(id);
    }

    @Override
    public List<PoliceDTO> getAllPolice() throws Exception {
        return policeBusiness.getAllPolice();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allPassportObs.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allPassportObs.remove(observer);
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
