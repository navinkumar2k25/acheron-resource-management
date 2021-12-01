package org.arm.resource.mngt.repository;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.entity.Priority;
import org.arm.resource.mngt.entity.Status;
import org.arm.resource.mngt.repository.CampaignRepository;
import org.arm.resource.mngt.service.CampaignService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceImplTest {
	@InjectMocks
	CampaignService campaignService;

	@Mock
	CampaignRepository campaignRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testing campaignService")
	public void getAllCampaign() {
		List<Campaign> campaignList = new ArrayList<>();
		Timestamp timestamp;
		Campaign campaign = (new Campaign(1, "Acheron", "Manager", Timestamp.valueOf("2020-03-27 09:03:01"),
				Timestamp.valueOf("2020-04-27 09:03:01"), Priority.HIGH, Status.DEFINED,
				Timestamp.valueOf("2020-03-27 09:03:01"), Timestamp.valueOf("2020-03-27 09:03:01"), 0, "Prasanna",
				"Naveen","NAC", null));
		campaignList.add(campaign);

		when(campaignRepository.findAll()).thenReturn(campaignList);
		List<Campaign> actual = campaignService.getAllCampaign();

		assertEquals(actual.size(), 1);

	}

}


