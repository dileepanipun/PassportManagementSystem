package lk.ijse.psm.business.custom.impl;

import lk.ijse.pms.dto.PassportDTO;
import lk.ijse.psm.business.custom.PassportBusiness;
import lk.ijse.psm.entity.Passport;
import lk.ijse.psm.entity.Payment;
import lk.ijse.psm.repository.RepoFactory;
import lk.ijse.psm.repository.custom.PassportRepo;
import lk.ijse.psm.repository.custom.PaymentRepo;
import lk.ijse.psm.resources.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PassportBusinessImpl implements PassportBusiness {

    PassportRepo passportRepo = RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.PASSPORT);
    PaymentRepo paymentRepo = RepoFactory.getInstance().getRepositoryFor(RepoFactory.RepoTypes.PAYMENT);

    public PassportBusinessImpl() {
    }

    @Override
    public boolean addPassport(PassportDTO passportDTO) throws Exception {

        Session openSession = HibernateUtil.getSessionFactory().openSession();

        passportRepo.setSession(openSession);
        paymentRepo.setSession(openSession);
        openSession .getTransaction().begin();

        Payment payment = new Payment( passportDTO.getDate(), passportDTO.getPrize());
        boolean resp1 = paymentRepo.add(payment);

        Passport passport = new Passport(
                passportDTO.getApplicationID(),
                passportDTO.getServiceType(),
                passportDTO.getDocumentType(),
                passportDTO.getPassportID(),
                passportDTO.getOriginalBC(),
                passportDTO.getPhotoDetail(),
                passportDTO.getPhotoLocation(),
                passportDTO.getMerrageCetificate(),
                passportDTO.getProfCetificate(),
                passportDTO.getStateOfPassport(),
                passportDTO.getErrorLog(),
                passportDTO.isPoliceApprove(),
                passportDTO.getClientNic()
        );

        boolean resp2 = passportRepo.add(passport);

        boolean finalResp = false;

        if (resp1 && resp2){
            openSession.getTransaction().commit();
            finalResp = true;
        }else {
            finalResp = false;
        }

        openSession.close();
        return finalResp;

    }

    @Override
    public boolean updatePassport(PassportDTO passportDTO) throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(new Passport(
                    passportDTO.getApplicationID(),
                    passportDTO.getServiceType(),
                    passportDTO.getDocumentType(),
                    passportDTO.getPassportID(),
                    passportDTO.getOriginalBC(),
                    passportDTO.getPhotoDetail(),
                    passportDTO.getPhotoLocation(),
                    passportDTO.getMerrageCetificate(),
                    passportDTO.getProfCetificate(),
                    passportDTO.getStateOfPassport(),
                    passportDTO.getErrorLog(),
                    passportDTO.isPoliceApprove(),
                    passportDTO.getClientNic()
            ));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PassportDTO searchPassport(String id) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            passportRepo.setSession(session);
            session.getTransaction().begin();
            Passport passport = passportRepo.search(id);
            session.getTransaction().commit();
            return new PassportDTO(
                    passport.getApplicationID(),
                    passport.getServiceType(),
                    passport.getDocumentType(),
                    passport.getPassportID(),
                    passport.getOriginalBC(),
                    passport.getPhotoDetail(),
                    passport.getPhotoLocation(),
                    passport.getMerrageCetificate(),
                    passport.getProfCetificate(),
                    passport.getStateOfPassport(),
                    passport.getErrorLog(),
                    passport.isPoliceApprove(),
                    passport.getClientNic()
            );
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PassportDTO> getAllValidPassport() throws Exception {
        List<PassportDTO> passportList = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            passportRepo.setSession(session);
            session.getTransaction().begin();

            List<Passport> passports = passportRepo.getValdPassport();
            for (Passport passport : passports){
                passportList.add(new PassportDTO(
                        passport.getApplicationID(),
                        passport.getServiceType(),
                        passport.getDocumentType(),
                        passport.getPassportID(),
                        passport.getOriginalBC(),
                        passport.getPhotoDetail(),
                        passport.getPhotoLocation(),
                        passport.getMerrageCetificate(),
                        passport.getProfCetificate(),
                        passport.getStateOfPassport(),
                        passport.getErrorLog(),
                        passport.isPoliceApprove(),
                        passport.getClientNic()
                ));
            }
            session.getTransaction().commit();
            return passportList;
        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PassportDTO> getAllNotValidPassport() throws Exception {
        List<PassportDTO> passportList = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            passportRepo.setSession(session);
            session.getTransaction().begin();

            List<Passport> passports = passportRepo.getNotValdPassport();
            for (Passport passport : passports){
                passportList.add(new PassportDTO(
                        passport.getApplicationID(),
                        passport.getServiceType(),
                        passport.getDocumentType(),
                        passport.getPassportID(),
                        passport.getOriginalBC(),
                        passport.getPhotoDetail(),
                        passport.getPhotoLocation(),
                        passport.getMerrageCetificate(),
                        passport.getProfCetificate(),
                        passport.getStateOfPassport(),
                        passport.getErrorLog(),
                        passport.isPoliceApprove(),
                        passport.getClientNic()
                ));
            }
            session.getTransaction().commit();
            return passportList;

        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
}
