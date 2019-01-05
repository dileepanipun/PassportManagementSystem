package lk.ijse.psm.service.custom.impl;

import lk.ijse.pms.dto.PassportDTO;
import lk.ijse.pms.observer.Observer;
import lk.ijse.pms.service.custom.PassportService;
import lk.ijse.psm.business.BusinessFactory;
import lk.ijse.psm.business.custom.PassportBusiness;
import lk.ijse.psm.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PassportServiceImpl extends UnicastRemoteObject implements PassportService {

    PassportBusiness passportBusiness = BusinessFactory.getInstance().getBusinessFor(BusinessFactory.BusinessTypes.PASSORT);
    private static ArrayList<Observer> allPassportObs = new ArrayList<>();
    public static ReservationImpl<PassportService> passportServiceReservation = new ReservationImpl<>();

    public PassportServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addPassport(PassportDTO passportDTO) throws Exception {
        return passportBusiness.addPassport(passportDTO);
    }

    @Override
    public boolean updatePassport(PassportDTO passportDTO) throws Exception {
        return passportBusiness.updatePassport(passportDTO);
    }

    @Override
    public PassportDTO searchPassport(String id) throws Exception {
        return passportBusiness.searchPassport(id);
    }

    @Override
    public List<PassportDTO> getAllValidPassport() throws Exception {
        return passportBusiness.getAllValidPassport();
    }

    @Override
    public List<PassportDTO> getAllNotValidPassport() throws Exception {
        return passportBusiness.getAllNotValidPassport();
    }

    @Override
    public void register(Observer observer) throws Exception{
        allPassportObs.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allPassportObs.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

        for (Observer observer : allPassportObs){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
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
        return passportServiceReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return passportServiceReservation.release(id,this);
    }
}
