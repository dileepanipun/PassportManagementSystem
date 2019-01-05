package lk.ijse.psm.business.custom.impl;

import lk.ijse.pms.dto.ClientDTO;
import lk.ijse.psm.business.custom.ClientBusiness;
import lk.ijse.psm.entity.Client;
import lk.ijse.psm.repository.RepoFactory;
import lk.ijse.psm.repository.custom.ClientRepo;
import lk.ijse.psm.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ClientBusinessImpl implements ClientBusiness {

    ClientRepo clientRepo = RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.CLIENT);

    @Override
    public boolean addClient(ClientDTO c) throws Exception {

        Session openSession = HibernateUtil.getSessionFactory().openSession();
        clientRepo.setSession(openSession);
        openSession .getTransaction().begin();
        boolean response = clientRepo.add(new Client(
                c.getNic(),
                c.getFullName(),
                c.getAddress(),
                c.getDob(),
                c.getbODetails(),
                c.getGender(),
                c.getProffession(),
                c.getEmail(),
                c.getTelephone(),
                c.getdCitizen()
        ));
        openSession.getTransaction().commit();
        openSession.close();
        return response;
    }

    @Override
    public boolean updateClient(ClientDTO client) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            boolean responce = clientRepo.upate(new Client(
                    client.getNic(),
                    client.getFullName(),
                    client.getAddress(),
                    client.getDob(),
                    client.getbODetails(),
                    client.getGender(),
                    client.getProffession(),
                    client.getEmail(),
                    client.getTelephone(),
                    client.getdCitizen()
            ));
            session.getTransaction().commit();

            return responce;
        }
    }

    @Override
    public boolean deleteClient(ClientDTO clientDTO) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            clientRepo.setSession(session);
            session.getTransaction().begin();
            boolean resp = clientRepo.delete(new Client(
                    clientDTO.getNic(),
                    clientDTO.getFullName(),
                    clientDTO.getAddress(),
                    clientDTO.getDob(),
                    clientDTO.getbODetails(),
                    clientDTO.getGender(),
                    clientDTO.getProffession(),
                    clientDTO.getEmail(),
                    clientDTO.getTelephone(),
                    clientDTO.getdCitizen()
            ));
            session.getTransaction().commit();
            return resp;
        }
    }

    @Override
    public ClientDTO searchClient(String id) throws Exception {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            clientRepo.setSession(session);
            session.getTransaction().begin();
            Client client = clientRepo.search(id);
            session.getTransaction().commit();
            return new ClientDTO(
                    client.getNic(),
                    client.getFullName(),
                    client.getAddress(),
                    client.getDob(),
                    client.getbODetails(),
                    client.getGender(),
                    client.getProffession(),
                    client.getEmail(),
                    client.getTelephone(),
                    client.getdCitizen()
            );
        }
    }

    @Override
    public List<ClientDTO> getAllClients() throws Exception {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            clientRepo.setSession(session);
            session.getTransaction().begin();
            List<Client> all = clientRepo.getAll();
            for (Client client : all){
                clientDTOS.add(new ClientDTO(
                        client.getNic(),
                        client.getFullName(),
                        client.getAddress(),
                        client.getDob(),
                        client.getbODetails(),
                        client.getGender(),
                        client.getProffession(),
                        client.getEmail(),
                        client.getTelephone(),
                        client.getdCitizen()
                ));
            }
            session.getTransaction().commit();
        }

        return clientDTOS;
    }
}
