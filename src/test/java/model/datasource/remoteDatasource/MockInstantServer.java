package model.datasource.remoteDatasource;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class MockInstantServer extends Dispatcher{

    private MockWebServer server = new MockWebServer();
    private boolean isStarted = false;

    public MockInstantServer() {
        server.setDispatcher(this);
    }

    public void start() throws IOException {
        if (!isStarted) {
            server.start();
            isStarted = true;
        }
    }

    public void stop() throws IOException {
        if (isStarted) {
            server.shutdown();
            isStarted = false;
        }
    }

    private String body;

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return server.url("/").toString();
    }

    @NotNull
    @Override
    public MockResponse dispatch(@NotNull RecordedRequest request) throws InterruptedException {
        String path = request.getRequestUrl().encodedPath();

        switch (request.getMethod() + " " + path) {
            case "POST /users":
                return new MockResponse();
            case "GET /users/current":
                return new MockResponse().setBody(body);
            case "POST /users/resetPassword":
                // todo check url parameter for mail
                return new MockResponse();
            case "GET /users/resetPassword":
                // todo check url parameter for mail
                return new MockResponse();
            case "GET /users/current/getRole":
                return new MockResponse().setBody(body);
            case "PUT /users/current":
                return new MockResponse();
            case "DELETE /users/current":
                return new MockResponse();
            case "POST /users/verify":
                return new MockResponse();
            default:
                throw new IllegalArgumentException("Illegal Path (" + path + ")");
        }
    }
}