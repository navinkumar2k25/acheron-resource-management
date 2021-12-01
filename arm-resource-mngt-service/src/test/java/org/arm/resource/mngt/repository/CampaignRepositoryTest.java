package org.arm.resource.mngt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.arm.resource.mngt.ArmRMSApplication;
import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.entity.Priority;
import org.arm.resource.mngt.entity.Status;
import org.arm.resource.mngt.repository.CampaignRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmRMSApplication.class)
@Transactional
public class CampaignRepositoryTest {

	@Autowired
	private CampaignRepository campaignRepository;

	@Test
	@Rollback(false)
	@DisplayName("campaignRepoSave Testing")
	public void testSaveMethod() {
		campaignRepository.save(new Campaign(1, "Acheron", "Manager", Timestamp.valueOf("2020-03-27 09:03:01"),
				Timestamp.valueOf("2020-04-27 09:03:01"), Priority.HIGH, Status.DEFINED,
				Timestamp.valueOf("2020-03-27 09:03:01"), Timestamp.valueOf("2020-03-27 09:03:01"), 0, "Prasanna",
				"Naveen","IMEA", null));
	}

	@Test
	@DisplayName("getAllCampaignRepo Testing")
	public void testGetAllResource() {
		List<Campaign> exp = campaignRepository.findAll();
		assertEquals(exp.size(), 2);
	}
}

