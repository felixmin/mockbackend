package model.datasource.remoteDatasource;

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
    void getRequestOpen() throws IOException, ExecutionException, InterruptedException {
        MockInstantServer server = new MockInstantServer();
        server.start();
        Api api = Api.getInstance();
        server.setBody(FileReader.readStringFromFile("getUserResponse.json"));
        Response res = api.getRequestOpen(server.getUrl() + "users/current");
        assertEquals(200, res.code());
    }
}