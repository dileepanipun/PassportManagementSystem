package lk.ijse.psm.repository.custom;

import lk.ijse.psm.entity.Passport;
import lk.ijse.psm.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface PassportRepo extends CrudRepository<Passport, String> {

    public List<Passport> getValdPassport()throws Exception;
    public List<Passport> getNotValdPassport()throws Exception;

}
