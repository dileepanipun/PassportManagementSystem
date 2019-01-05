package lk.ijse.psm.repository;

import lk.ijse.psm.repository.custom.PaymentRepo;
import lk.ijse.psm.repository.custom.impl.*;

public class RepoFactory  {

    public static RepoFactory repoFactory;

    public static RepoFactory getInstance() {
        if (repoFactory == null) {
            repoFactory = new RepoFactory();
        }
        return repoFactory;
    }

    public enum RepoTypes {
        CLIENT, PASSPORT, PAYMENT, POLICE, RECEPTION
    }

    public <T> T getRepositoryFor(RepoTypes repoTypes){

        switch (repoTypes){
            case CLIENT:
                return (T) new ClientRepoImpl();
            case PASSPORT:
                return (T) new PassportRepoImpl();
            case POLICE:
                return (T) new PoliceRepoImpl();
            case PAYMENT:
                return (T) new PaymentRepoImpl();
            case RECEPTION:
                return (T) new ReceptionRepoImpl();
                default:
                    return null;
        }
    }

}
