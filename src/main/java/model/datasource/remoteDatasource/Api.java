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

    public Response getRequestOpen(String url) throws ExecutionException, InterruptedException {
        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        return buildOpenRequest(request);
    }

    public Response getRequestProtected(String url, String mail, String psw)
            throws ExecutionException, InterruptedException {
        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        return buildProtectedRequest(request, mail, psw);
    }



    private Response buildOpenRequest(Request request) throws ExecutionException, InterruptedException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return sendRequest(client, request);
    }

    private Response buildProtectedRequest(Request request, String mail, String psw)
            throws ExecutionException, InterruptedException {
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor(mail, psw))
            .build();
        return sendRequest(client, request);
    }



    private Response sendRequest(OkHttpClient client, Request request) throws ExecutionException, InterruptedException {
        OkHttpResponseFuture callback = new OkHttpResponseFuture();
        client.newCall(request).enqueue(callback);
        return callback.future.get();
    }
}
