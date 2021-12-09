package org.arm.resource.mngt.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.arm.resource.mngt.ArmRMSApplication;
import org.arm.resource.mngt.entity.Campaign;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = ArmRMSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampaignApiTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	@DisplayName("Testing getAllCampaignApi")
	public void testGetAllCampaign() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<List<Campaign>> campaignList = restTemplate.exchange(createURLWithPort("/campaigns"),
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Campaign>>() {
				});

		assertNotNull(campaignList.getBody());
		assertEquals(campaignList.getBody().size(), 4);

	}

	@Test
	@DisplayName("Testing getCampaignByIdApi")
	public void testGetCampaign() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Campaign> campaign = restTemplate.exchange(createURLWithPort("/campaign/1"), HttpMethod.GET,
				entity, new ParameterizedTypeReference<Campaign>() {
				});

		assertNotNull(campaign.getBody());
		assertEquals(campaign.getBody().getCampaignId(), 1);
		assertNotNull(campaign.getBody().getCampaignName());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
