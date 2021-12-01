package org.arm.resource.mngt.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.arm.resource.mngt.ArmRMSApplication;
import org.arm.resource.mngt.entity.Resource;
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
public class ResourceRepositoryTest {
	
	@Autowired
	private ResourceRepository resourceRepository;

	@Test
	@Rollback(false)
	@DisplayName("resourceRepoSave Testing")
	public void testSaveMethod() {
		resourceRepository.save(new Resource(1, "Navin", "Manager", Timestamp.valueOf("2020-03-27 09:03:01"),
				Timestamp.valueOf("2020-04-27 09:03:01"), 0, null, "resource.jpj", "Madras", null, null));
	}

	@Test
	@DisplayName("getAllReourceRepo Testing")
	public void testGetAllResource() {
		List<Resource> exp = resourceRepository.findAll();
		assertEquals(exp.size(), 28);
	}

}
