package lk.ijse.psm.business.custom.impl;

import lk.ijse.pms.dto.RecepitionDTO;
import lk.ijse.psm.business.custom.RecepitonBusineess;
import lk.ijse.psm.entity.Reception;
import lk.ijse.psm.repository.RepoFactory;
import lk.ijse.psm.repository.custom.ReceptionRepo;
import lk.ijse.psm.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ReceptionBusinessImpl implements RecepitonBusineess {

    ReceptionRepo receptionRepo = RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.RECEPTION);

    @Override
    public boolean addReception(RecepitionDTO recepitionDTO) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            receptionRepo.setSession(session);
            session.getTransaction().begin();
            boolean resp = receptionRepo.add(new Reception(
                    recepitionDTO.getRid(),
                    recepitionDTO.getName(),
                    recepitionDTO.getAddress(),
                    recepitionDTO.getTelephone(),
                    recepitionDTO.getSalary(),
                    recepitionDTO.getUsername(),
                    recepitionDTO.getPassword()
            ));
            session.getTransaction().commit();
            return resp;
        }
    }

    @Override
    public boolean updateReception(RecepitionDTO recepitionDTO) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            receptionRepo.setSession(session);
            session.getTransaction().begin();
            boolean resp = receptionRepo.upate(new Reception(
                    recepitionDTO.getRid(),
                    recepitionDTO.getName(),
                    recepitionDTO.getAddress(),
                    recepitionDTO.getTelephone(),
                    recepitionDTO.getSalary(),
                    recepitionDTO.getUsername(),
                    recepitionDTO.getPassword()
            ));
            session.getTransaction().commit();
            return resp;
        }
    }

    @Override
    public boolean deleteReception(String id) throws Exception {
        return false;
    }

    @Override
    public RecepitionDTO searchReception(String id) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            receptionRepo.setSession(session);
            session.getTransaction().begin();
            Reception search = receptionRepo.search(id);
            session.getTransaction().commit();
            return new RecepitionDTO(
                    search.getRid(),
                    search.getName(),
                    search.getAddress(),
                    search.getTelephone(),
                    search.getSalary(),
                    search.getUsername(),
                    search.getPassword()
            );
        }
    }

    @Override
    public List<RecepitionDTO> getAllReceptions() throws Exception {
        List<RecepitionDTO> dtos = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            receptionRepo.setSession(session);
            session.getTransaction().begin();
            List<Reception> all = receptionRepo.getAll();
            for (Reception search : all){
                dtos.add(new RecepitionDTO(
                        search.getRid(),
                        search.getName(),
                        search.getAddress(),
                        search.getTelephone(),
                        search.getSalary(),
                        search.getUsername(),
                        search.getPassword()
                ));
            }
            session.getTransaction().commit();
            return dtos;
        }
    }
}
