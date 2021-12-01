package org.arm.resource.mngt.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.arm.resource.mngt.entity.Task;
import org.arm.resource.mngt.service.ITaskService;
import org.arm.resource.mngt.vo.TaskVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	@Autowired
	private ITaskService taskService;

	@GetMapping("/tasks/{task-id}")
	Task getById(@PathVariable("task-id") int id) {
		return taskService.getById(id);
	}

	@GetMapping("/tasks")
	public List<TaskVO> allTaskVO() {
		List<TaskVO> taskVOs = new ArrayList<TaskVO>();
		List<Task> allTasks = taskService.getAllTask();
		for (Task task : allTasks) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			dozerBeanMapper.setMappingFiles(Arrays.asList("mapping\\mapper.xml"));
			TaskVO taskVO = dozerBeanMapper.map(task, TaskVO.class);
			taskVOs.add(taskVO);

		}
		return taskVOs;
	}

//	@GetMapping("/task/create")
//	public void createTask(){
//		Task task = new Task();
//		task.setCreateDate(new Timestamp(new Date().getTime()));
//		task.setDuration(10);
//		task.setEndDate(new Timestamp(new Date().getTime() + 15*86400 ));
//		task.setStartDate(new Timestamp(new Date().getTime()));
//		task.setIsDeleted(0);
//		task.setPriority(Priority.MEDIUM);
//		task.setStatus(Status.IN_PROGRESS);
//		task.setTaskName("Task5");
//		task.setTaskOwner("Test");
//		taskService.createTasks(task);
//	}

	@GetMapping("/tasks/availability/{available-hour}")
	ResponseEntity<List<Task>> findByDurationLessThan(@PathVariable("available-hour") float availableHours) {
		List<Task> taskList = taskService.getByDurationLessThan(availableHours);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get Brandlist By Name");
		return ResponseEntity.ok().headers(headers).body(taskList);

	}
}
