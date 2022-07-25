package model;

import model.datasource.UserDatasource;

public class UserRepository {

    UserDatasource userDatasource;

    String currentUser;

    private static UserRepository userRepository;

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public void login(String name, String psw) throws Exception {
        currentUser = userDatasource.login();
    }

    public void setDatasource(UserDatasource datasource) {
        this.userDatasource = datasource;
    }

    public String getUser() {
        return currentUser;
    }
}
