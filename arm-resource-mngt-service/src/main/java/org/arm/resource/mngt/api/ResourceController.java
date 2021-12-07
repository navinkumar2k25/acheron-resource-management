package org.arm.resource.mngt.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.arm.resource.mngt.entity.Resource;
import org.arm.resource.mngt.entity.Task;
import org.arm.resource.mngt.exception.ResourceNotFoundException;
import org.arm.resource.mngt.service.ResourceService;
import org.arm.resource.mngt.vo.ResourceVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@GetMapping("/resources")
	public ResponseEntity<List<ResourceVO>> getAllResource() throws ResourceNotFoundException{
		List<ResourceVO> resourceVOs = new ArrayList<ResourceVO>();
		List<Resource> resourceList = resourceService.findAll();
		for (Resource resource : resourceList) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			dozerBeanMapper.setMappingFiles(Arrays.asList("mapping\\mapper.xml"));
			ResourceVO resourceVO = dozerBeanMapper.map(resource, ResourceVO.class);
			resourceVOs.add(resourceVO);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get All Resources");
		return ResponseEntity.ok().headers(headers).body(resourceVOs);
	}

//	@PostMapping("/resource/create")
//	public void createResource(){
//		Resource resource = new Resource();
//		resource.setCreateDate(new Timestamp(new Date().getTime()));
//		resource.setIsDeleted(0);
//		resource.setResourceName("T User 2");
//		resource.setResourceType("B");
//		
//		resourceService.save(resource);
//	}
//	
	@GetMapping("/resources/without-task")
	public ResponseEntity<List<ResourceVO>> getResourceWithoutTaskAssigned() {
		List<ResourceVO> resourceVOs = new ArrayList<ResourceVO>();
		List<Resource> resourceList = resourceService.findResourceWithoutTaskAssigned();
		for (Resource resource : resourceList) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			dozerBeanMapper.setMappingFiles(Arrays.asList("mapping\\mapper.xml"));
			ResourceVO resourceVO = dozerBeanMapper.map(resource, ResourceVO.class);
			resourceVOs.add(resourceVO);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get All Resources with free task");
		return ResponseEntity.ok().headers(headers).body(resourceVOs);
	}

	@GetMapping("/resources/{resource-id}")
	Resource getById(@PathVariable("resource-id") int id) {
		return resourceService.getById(id);
	}

}
