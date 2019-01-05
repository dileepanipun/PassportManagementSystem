package lk.ijse.psm.repository.custom.impl;

import lk.ijse.psm.entity.Passport;
import lk.ijse.psm.repository.CrudRepositoryImpl;
import lk.ijse.psm.repository.custom.PassportRepo;
import lk.ijse.psm.resources.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;

public class PassportRepoImpl extends CrudRepositoryImpl<Passport, String> implements PassportRepo {

    @Override
    public List<Passport> getValdPassport() {
        ArrayList<Passport> list = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();

            Query query = session.createNativeQuery("SELECT * FROM passport WHERE policeApprove=TRUE ");
            query.setResultTransformer(Transformers.aliasToBean(Passport.class));
            list = (ArrayList<Passport>) query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Passport> getNotValdPassport(){
        ArrayList<Passport> list = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();

            Query query = session.createNativeQuery("SELECT * FROM passport WHERE policeApprove=false");
            query.setResultTransformer(Transformers.aliasToBean(Passport.class));
            list = (ArrayList<Passport>) query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
