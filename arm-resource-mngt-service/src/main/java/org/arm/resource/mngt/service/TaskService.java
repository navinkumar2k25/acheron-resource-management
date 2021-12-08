package org.arm.resource.mngt.service;

import java.util.List;
import java.util.Optional;

import org.arm.resource.mngt.entity.Task;
import org.arm.resource.mngt.exception.IDNotFoundException;
import org.arm.resource.mngt.exception.TaskNotFoundException;
import org.arm.resource.mngt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Task> getAllTask() throws TaskNotFoundException {
		List<Task> taskList= taskRepository.findAll();
		if(taskList.isEmpty())
			throw new TaskNotFoundException("Task is not available");
		return taskList;
	}
   // to add tasks
	@Override
	public void createTasks(Task tasks) {
		taskRepository.save(tasks);
		
	}
	
	//to retrieve the list of task which is having less duration than the user Input
	@Override
	public List<Task> getByDurationLessThan(float availableHours) throws TaskNotFoundException{
		List<Task> taskDuration=taskRepository.findByDurationLessThan(availableHours);
		if(taskDuration.isEmpty())
			throw new TaskNotFoundException("Duration is not available");
		return taskDuration;
	}
 
	//to retrieve the Task by passing Id
	@Override
	public Task getById(int id) throws IDNotFoundException {
		return taskRepository.findById(id).orElseThrow(() -> new IDNotFoundException("Task Id not Found"));

	}
}
