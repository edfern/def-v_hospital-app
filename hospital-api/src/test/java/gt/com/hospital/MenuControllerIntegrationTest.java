package gt.com.hospital;

import static org.junit.jupiter.api.Assertions.assertTrue;

import gt.com.model.dto.ResponseMenuDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(classes = HospitalApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles
public class MenuControllerIntegrationTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        public void getMenusIntegrationTest() throws URISyntaxException
        {
                final String baseUrl = "http://localhost:"+ port +"/menu";
                URI uri = new URI(baseUrl);

                HttpHeaders headers = new HttpHeaders();

                ResponseEntity<ResponseMenuDto> result = this.restTemplate.getForEntity(uri,ResponseMenuDto.class);

                Assert.assertEquals("success", result.getBody().getMessage());
                assertTrue(
                        this.restTemplate
                                .getForEntity("http://localhost:" + port + "/menu", ResponseMenuDto.class)
                                .getBody().getMenusEntities().size() == 1);
                System.out.println("TODO BIEN CHINO");
                System.out.println("PRUEBA EXITOSA");
        }
}
