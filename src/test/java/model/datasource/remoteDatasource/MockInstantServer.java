package model.datasource.remoteDatasource;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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

    public String getUrl() {
        return server.url("/").toString();
    }

    @NotNull
    @Override
    public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
        return new MockResponse().setResponseCode(492);
    }
}
