package org.arm.resource.mngt.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.arm.resource.mngt.entity.Project;
import org.arm.resource.mngt.service.IProjectService;
import org.arm.resource.mngt.vo.ProjectVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@GetMapping("/projects/{project-id}")
	Project getById(@PathVariable("project-id") int id) {
		return projectService.getById(id);
	}

	@GetMapping("/projects")
	public List<ProjectVO> allProjectVO() {
		List<ProjectVO> projectVOs = new ArrayList<ProjectVO>();
		List<Project> allProjects = projectService.getAllProject();
		for (Project project : allProjects) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			dozerBeanMapper.setMappingFiles(Arrays.asList("mapping\\mapper.xml"));
			ProjectVO projectVO = dozerBeanMapper.map(project, ProjectVO.class);
			projectVOs.add(projectVO);

		}
		return projectVOs;
	}

//	@GetMapping("/project/create")
//	public void createProject(){
//		Project project = new Project();
//		// project.setCampaignId(1);
//		project.setCreateDate(new Timestamp(new Date().getTime()));
//		project.setEndDate(new Timestamp(new Date().getTime() + 15*86400 ));
//		project.setIsDeleted(0);
//		project.setStartDate(new Timestamp(new Date().getTime()));
//		project.setPriority(Priority.HIGH);
//		project.setProjectName("New Project");
//		project.setProjectOwner("Test");
//		project.setStatus(Status.IN_PROGRESS);
//		
//		projectService.save(project);
//	}
}
