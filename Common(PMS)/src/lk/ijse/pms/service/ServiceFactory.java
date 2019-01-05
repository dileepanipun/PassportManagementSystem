package lk.ijse.pms.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {

    public enum ServiceTypes{
        CLIENT,PASSPORT,RECEPTION,POLICE
    }

    public <T>T getSuperService(ServiceTypes types) throws Exception;

}
