package org.arm.resource.mngt.api;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.arm.resource.mngt.ArmRMSApplication;
import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.vo.CampaignVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmRMSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampaignApiTest {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("getCampaign Testing")
	public void testGetCampaign() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Campaign> response = restTemplate.exchange(createURLWithPort("/campaign/1"), HttpMethod.GET, entity,new ParameterizedTypeReference<Campaign>() {});
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getCampaignId(),1);
		assertNotNull(response.getBody().getCampaignName());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("getAllCampaign Testing")
	public void testGetAllCampaign() {
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<List<Campaign>> response = restTemplate.exchange(createURLWithPort("/campaigns"),HttpMethod.GET,entity,new ParameterizedTypeReference<List<Campaign>>() {
		});
		assertNotEquals(response.getBody().size(), 0);
		assertNotNull(response.getBody().get(0));
		assertEquals(response.getBody().get(1).getCreateDate(), null);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}


