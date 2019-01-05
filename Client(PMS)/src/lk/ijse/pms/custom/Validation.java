package lk.ijse.pms.custom;

public class Validation {

    public static boolean numbersOnly(String input){
        if (input.trim().matches("^[\\d]*")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean nicValidation(String input) {
        if (input.trim().matches("[0-9]{9}[vV]")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpety(String text){
        if (text.trim().equals("")){
            return true;
        }else {
            return false;
        }
    }

}
