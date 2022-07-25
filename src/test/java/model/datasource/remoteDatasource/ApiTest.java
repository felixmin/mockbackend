package model.datasource.remoteDatasource;

import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ApiTest {


    @Test
    void request() throws ExecutionException, InterruptedException, IOException {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setBody("STUDENT");

        server.enqueue(response);
        String url = server.url("/").toString();

        Api api = Api.getInstance();
        Response res = api.request(url);
        String body = res.body().string();

        assertEquals(200, res.code());
        assertNotNull(body);
        assertEquals("STUDENT", body);
    }

    @Test
    void requestProtected() throws InterruptedException, ExecutionException {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();

        server.enqueue(response);
        String url = server.url("/").toString();

        Api api = Api.getInstance();
        Response res = api.requestProtected(url, "mail", "psw");

        RecordedRequest request = server.takeRequest();

        assertEquals("Basic bWFpbDpwc3c=", request.getHeader("Authorization"));
    }

    @Test
    void req() {
        MockInstantServer server = new MockInstantServer();
        try{
            server.start();
            Api api = Api.getInstance();
            Response res = api.request(server.getUrl());
            assertEquals(200, res.code());
        }catch (Exception e) {

        }

    }
}