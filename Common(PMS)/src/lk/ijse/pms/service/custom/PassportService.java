package lk.ijse.pms.service.custom;

import lk.ijse.pms.dto.PassportDTO;
import lk.ijse.pms.observer.Subject;
import lk.ijse.pms.reservation.Reservation;
import lk.ijse.pms.service.SuperService;

import java.util.List;

public interface PassportService extends SuperService, Reservation, Subject {

    public boolean addPassport(PassportDTO passportDTO) throws Exception;

    public boolean updatePassport(PassportDTO passportDTO) throws Exception;

    public PassportDTO searchPassport(String id) throws Exception;

    public List<PassportDTO> getAllValidPassport() throws Exception;

    public List<PassportDTO> getAllNotValidPassport() throws Exception;

}
