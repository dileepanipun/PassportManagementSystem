package lk.ijse.pms.service.custom;

import lk.ijse.pms.dto.PoliceDTO;
import lk.ijse.pms.observer.Subject;
import lk.ijse.pms.reservation.Reservation;
import lk.ijse.pms.service.SuperService;

import java.util.List;

public interface PoliceService extends SuperService, Reservation, Subject {

    public boolean addPolice(PoliceDTO policeDTO) throws Exception;

    public boolean updatePolice(PoliceDTO policeDTO) throws Exception;

    public boolean deletePolice(String id) throws Exception;

    public PoliceDTO searchPolice(String id) throws Exception;

    public List<PoliceDTO> getAllPolice() throws Exception;

}
