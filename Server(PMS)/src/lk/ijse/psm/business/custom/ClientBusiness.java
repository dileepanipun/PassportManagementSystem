package lk.ijse.psm.business.custom;

import lk.ijse.pms.dto.ClientDTO;
import lk.ijse.psm.business.SuperBusiness;

import java.io.Serializable;
import java.util.List;

public interface ClientBusiness extends SuperBusiness, Serializable {

    public boolean addClient(ClientDTO clientDTO) throws Exception;

    public boolean updateClient(ClientDTO clientDTO) throws Exception;

    public boolean deleteClient(ClientDTO clientDTO) throws Exception;

    public ClientDTO searchClient(String id) throws Exception;

    public List<ClientDTO> getAllClients() throws Exception;

}
