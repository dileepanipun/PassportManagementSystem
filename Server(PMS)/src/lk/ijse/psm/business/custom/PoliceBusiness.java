package lk.ijse.psm.business.custom;

import lk.ijse.pms.dto.PoliceDTO;
import lk.ijse.psm.business.SuperBusiness;

import java.util.List;

public interface PoliceBusiness extends SuperBusiness {

    public boolean addPolice(PoliceDTO policeDTO) throws Exception;

    public boolean updatePolice(PoliceDTO policeDTO) throws Exception;

    public boolean deletePolice(String id) throws Exception;

    public PoliceDTO searchPolice(String id) throws Exception;

    public List<PoliceDTO> getAllPolice() throws Exception;

}
