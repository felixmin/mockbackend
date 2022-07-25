package model.datasource.remoteDatasource;

import okhttp3.*;

import java.util.concurrent.ExecutionException;

public class Api {

    private static Api instance;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    private Api() {

    }

    public Response request(String url) throws ExecutionException, InterruptedException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();
        OkHttpResponseFuture callback = new OkHttpResponseFuture();

        client.newCall(request).enqueue(callback);

        return callback.future.get();
    }

    public Response requestProtected(String url, String mail, String psw) throws ExecutionException, InterruptedException {
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor(mail, psw))
            .build();

        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();
        OkHttpResponseFuture callback = new OkHttpResponseFuture();

        client.newCall(request).enqueue(callback);

        return callback.future.get();
    }
}
