package lk.VehicleSparePartsSellingShop.pos.entity;

public class LoginHistory  implements SuperEntity  {
    private  int LoginID;
    private String UserName;
    private String Password;
    private String LoginDate;
    private String LoginTime;
    private String LoginStates;

    public LoginHistory() {
    }

    public LoginHistory(int loginID, String userName, String password, String loginDate, String loginTime, String loginStates) {
        LoginID = loginID;
        UserName = userName;
        Password = password;
        LoginDate = loginDate;
        LoginTime = loginTime;
        LoginStates = loginStates;
    }

    public int getLoginID() {
        return LoginID;
    }

    public void setLoginID(int loginID) {
        LoginID = loginID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLoginDate() {
        return LoginDate;
    }

    public void setLoginDate(String loginDate) {
        LoginDate = loginDate;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String loginTime) {
        LoginTime = loginTime;
    }

    public String getLoginStates() {
        return LoginStates;
    }

    public void setLoginStates(String loginStates) {
        LoginStates = loginStates;
    }
}
