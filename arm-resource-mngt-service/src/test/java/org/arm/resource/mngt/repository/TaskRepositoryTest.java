package org.arm.resource.mngt.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.arm.resource.mngt.ArmRMSApplication;
import org.arm.resource.mngt.entity.Priority;
import org.arm.resource.mngt.entity.Resource;
import org.arm.resource.mngt.entity.Status;
import org.arm.resource.mngt.entity.Task;
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
public class TaskRepositoryTest {

	@Autowired
	TaskRepository taskRepository;

	@Test
	@Rollback(false)
	@DisplayName("TaskSave test")
	public void taskSaveMethodTest() {
		taskRepository.save(new Task(1, "Alpha", "Emma", Timestamp.valueOf("2021-05-13 09:03:01"),
				Timestamp.valueOf("2021-05-19 14:30:01"), 24, Priority.HIGH, Status.COMPLETED,
				Timestamp.valueOf("2021-05-13 09:03:01"), null, 0, null, null, null, null));
	}

	@Test
	@DisplayName("getAllTaskRepo Testing")
	public void testGetAllResource() {
		List<Task> exp = taskRepository.findAll();
		assertEquals(exp.size(), 7);
	}
}
