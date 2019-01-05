package lk.ijse.pms.service.custom;

import lk.ijse.pms.dto.ClientDTO;
import lk.ijse.pms.observer.Subject;
import lk.ijse.pms.reservation.Reservation;
import lk.ijse.pms.service.SuperService;

import java.util.List;

public interface ClientService extends SuperService, Reservation, Subject {

    public boolean addClient(ClientDTO clientDTO) throws Exception;

    public boolean updateClient(ClientDTO clientDTO) throws Exception;

    public boolean deleteClient(ClientDTO clientDTO) throws Exception;

    public ClientDTO searchClient(String id) throws Exception;

    public List<ClientDTO> getAllClients() throws Exception;

}
