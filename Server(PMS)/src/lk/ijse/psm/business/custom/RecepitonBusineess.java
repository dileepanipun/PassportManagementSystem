package lk.ijse.psm.business.custom;

import lk.ijse.pms.dto.RecepitionDTO;
import lk.ijse.psm.business.SuperBusiness;

import java.util.List;

public interface RecepitonBusineess extends SuperBusiness {

    public boolean addReception(RecepitionDTO recepitionDTO) throws Exception;

    public boolean updateReception(RecepitionDTO recepitionDTO) throws Exception;

    public boolean deleteReception(String id) throws Exception;

    public RecepitionDTO searchReception(String id) throws Exception;

    public List<RecepitionDTO> getAllReceptions() throws Exception;

}
