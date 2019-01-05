package lk.ijse.psm.business.custom;

import lk.ijse.pms.dto.PassportDTO;
import lk.ijse.psm.business.SuperBusiness;

import java.util.List;

public interface PassportBusiness extends SuperBusiness {

    public boolean addPassport(PassportDTO passportDTO) throws Exception;

    public boolean updatePassport(PassportDTO passportDTO) throws Exception;

    public PassportDTO searchPassport(String id) throws Exception;

    public List<PassportDTO> getAllValidPassport() throws Exception;

    public List<PassportDTO> getAllNotValidPassport() throws Exception;

}
