package org.arm.resource.mngt.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.service.ICampaignService;
import org.arm.resource.mngt.vo.CampaignVO;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignController {
	@Autowired
	private ICampaignService campaignService;

	Logger logger = LoggerFactory.getLogger(CampaignController.class);

	@GetMapping("/campaigns")
	public ResponseEntity<List<CampaignVO>> allCampaignVO() {
		logger.info("All Campaign inside controller");
		List<CampaignVO> campaignVOs = new ArrayList<CampaignVO>();
		List<Campaign> allCampaigns = campaignService.getAllCampaign(); 
		for (Campaign campaign : allCampaigns) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			dozerBeanMapper.setMappingFiles(Arrays.asList("mapping\\mapper.xml"));
			CampaignVO campaignVO = dozerBeanMapper.map(campaign, CampaignVO.class);
			campaignVOs.add(campaignVO);

		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get All Campaign List");
		return ResponseEntity.ok().headers(headers).body(campaignVOs);
	}

	@GetMapping("/campaign/{id}")
	public ResponseEntity<Campaign> findById(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get Campaign By ID");
		Campaign campaignById=campaignService.findById(id);
		return ResponseEntity.ok().headers(headers).body(campaignById);
	}

//	@PostMapping("/campaign/create")
//	public void createCampaign(){
//		logger.info("Campaign logging");
//		Campaign campaign = new Campaign();
//		campaign.setCampaignName("Campaign1");
//		campaign.setCampaignOwner("Test");
//		campaign.setCreateDate(new Timestamp(new Date().getTime()));
//		campaign.setEndDate(new Timestamp(new Date().getTime() + 15*86400 ));
//		campaign.setIsDeleted(0);
//		campaign.setPriority(Priority.MEDIUM);
//		campaign.setStatus(Status.IN_PROGRESS);
//		campaign.setStartDate(new Timestamp(new Date().getTime()));
//		
//		campaignService.createCampaign(campaign);
//		
//	}
}
