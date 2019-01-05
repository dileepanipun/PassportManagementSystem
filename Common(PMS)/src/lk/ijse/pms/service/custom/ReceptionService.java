package lk.ijse.pms.service.custom;

import lk.ijse.pms.dto.RecepitionDTO;
import lk.ijse.pms.observer.Subject;
import lk.ijse.pms.reservation.Reservation;
import lk.ijse.pms.service.SuperService;

import java.util.List;

public interface ReceptionService extends SuperService, Reservation, Subject {

    public boolean addReception(RecepitionDTO recepitionDTO) throws Exception;

    public boolean updateReception(RecepitionDTO recepitionDTO) throws Exception;

    public boolean deleteReception(String id) throws Exception;

    public RecepitionDTO searchReception(String id) throws Exception;

    public List<RecepitionDTO> getAllReceptions() throws Exception;

}
