package model.datasource.remoteDatasource;

import model.datasource.UserDatasource;
import okhttp3.Response;

public class RemoteUserDatasource implements UserDatasource {
    private static RemoteUserDatasource remoteUserDatasource;

    private String url = "https://reqres.in/api/users?delay=3";

    Api api = Api.getInstance();

    private RemoteUserDatasource() {

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static RemoteUserDatasource getInstance() {
        if (remoteUserDatasource == null) {
            remoteUserDatasource = new RemoteUserDatasource();
        }
        return remoteUserDatasource;
    }

    @Override
    public String login() throws Exception {
        Response res;
        try {
            res = api.request(url);
        } catch (Exception e) {
            throw new Exception("hi"); // throw adequate exceptions
        }
        return res.body().string();
    }
}
