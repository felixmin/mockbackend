package model.datasource.remoteDatasource;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoteUserDatasourceTest {

    @Test
    void login() throws Exception {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setBody("STUDENT");

        server.enqueue(response);

        String url = server.url("/").toString();

        RemoteUserDatasource remoteUserDatasource = RemoteUserDatasource.getInstance();
        remoteUserDatasource.setUrl(url);

        assertEquals("STUDENT", remoteUserDatasource.login());


    }
}