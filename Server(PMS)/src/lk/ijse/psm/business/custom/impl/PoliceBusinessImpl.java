package lk.ijse.psm.business.custom.impl;

import lk.ijse.pms.dto.PoliceDTO;
import lk.ijse.psm.business.custom.PoliceBusiness;
import lk.ijse.psm.entity.Police;
import lk.ijse.psm.repository.RepoFactory;
import lk.ijse.psm.repository.custom.PoliceRepo;
import lk.ijse.psm.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PoliceBusinessImpl implements PoliceBusiness {

    PoliceRepo policeRepo = RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.POLICE);

    @Override
    public boolean addPolice(PoliceDTO policeDTO) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            policeRepo.setSession(session);
            session.getTransaction().begin();
            boolean resp = policeRepo.add(new Police(
                    policeDTO.getPolID(),
                    policeDTO.getName(),
                    policeDTO.getAddress(),
                    policeDTO.getTelephone(),
                    policeDTO.getSalary(),
                    policeDTO.getUsername(),
                    policeDTO.getPassword()
            ));
            session.getTransaction().commit();
            return resp;
        }
    }

    @Override
    public boolean updatePolice(PoliceDTO policeDTO) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            policeRepo.setSession(session);
            session.getTransaction().begin();
            boolean resp = policeRepo.upate(new Police(
                    policeDTO.getPolID(),
                    policeDTO.getName(),
                    policeDTO.getAddress(),
                    policeDTO.getTelephone(),
                    policeDTO.getSalary(),
                    policeDTO.getUsername(),
                    policeDTO.getPassword()
            ));
            session.getTransaction().commit();
            return resp;
        }
    }

    @Override
    public boolean deletePolice(String id) throws Exception {
        return false;
    }

    @Override
    public PoliceDTO searchPolice(String id) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            policeRepo.setSession(session);
            session.getTransaction().begin();
            Police search = policeRepo.search(id);
            session.getTransaction().commit();
            return new PoliceDTO(
                    search.getPolId(),
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
    public List<PoliceDTO> getAllPolice() throws Exception {
        List<PoliceDTO> dtos = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            policeRepo.setSession(session);
            session.getTransaction().begin();
            List<Police> all = policeRepo.getAll();
            for (Police police : all){
                dtos.add(new PoliceDTO(
                        police.getPolId(),
                        police.getName(),
                        police.getAddress(),
                        police.getTelephone(),
                        police.getSalary(),
                        police.getUsername(),
                        police.getPassword()
                ));
            }
            session.getTransaction().commit();
            return dtos;
        }
    }
}
