import model.UserRepository;
import model.datasource.remoteDatasource.RemoteUserDatasource;

public class Main {
    public static void main(String[] args) {
        UserRepository repo = UserRepository.getInstance(); // create repo

        repo.setDatasource(RemoteUserDatasource.getInstance()); // dependency injection

        try{
            repo.login("name", "psw");
        } catch (Exception e) {

        }

        System.out.println(repo.getUser());
    }
}