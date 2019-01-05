package lk.ijse.psm.business;

import lk.ijse.psm.business.custom.impl.ClientBusinessImpl;
import lk.ijse.psm.business.custom.impl.PassportBusinessImpl;
import lk.ijse.psm.business.custom.impl.PoliceBusinessImpl;
import lk.ijse.psm.business.custom.impl.ReceptionBusinessImpl;

public class BusinessFactory {

    private static BusinessFactory businessFactory;

    public static BusinessFactory getInstance(){
        if (businessFactory == null){
            businessFactory = new BusinessFactory();
        }
        return businessFactory;
    }

    public enum BusinessTypes{
        CLIENT,PASSORT,POLICE,RECEPTION
    }

    public <T>T getBusinessFor(BusinessTypes businessTypes){
        switch (businessTypes) {
            case CLIENT:
                return (T) new ClientBusinessImpl();
            case POLICE:
                return (T) new PoliceBusinessImpl();
            case RECEPTION:
                return (T) new ReceptionBusinessImpl();
            case PASSORT:
                return (T) new PassportBusinessImpl();
                default:
                    return null;
        }
    }

}
