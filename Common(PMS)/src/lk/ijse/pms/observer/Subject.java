package lk.ijse.pms.observer;

import java.rmi.Remote;

public interface Subject extends Remote {

    public void register(Observer observer) throws Exception;

    public void unregister(Observer observer) throws Exception;

    public void notifyAllObservers(String message) throws Exception;

}
