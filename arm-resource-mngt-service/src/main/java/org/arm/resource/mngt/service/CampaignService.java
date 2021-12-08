package org.arm.resource.mngt.service;

import java.util.List;
import java.util.Optional;

import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.exception.CampaignNotFoundException;
import org.arm.resource.mngt.exception.IDNotFoundException;
import org.arm.resource.mngt.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService implements ICampaignService {
	@Autowired
	CampaignRepository campaignRepository;
	
   // to display the list of Campaigns
	public List<Campaign> getAllCampaign() throws CampaignNotFoundException {
		 List<Campaign> allCampaign= campaignRepository.findAll();
			if(allCampaign.isEmpty()) {
				throw new CampaignNotFoundException("No campaign found");
			}
			return allCampaign;

	}
	// to add Campaigns
	@Override
	public void createCampaign(Campaign campaign) {
		campaignRepository.save(campaign); 
	}
    
	// to retrieve Campaigns by Id
	@Override
	public Campaign findById(int id) throws IDNotFoundException{
		return campaignRepository.findById(id).orElseThrow(() -> new IDNotFoundException("Campaign Id not Found"));
	}
	
	//to retrieve all Resources which assigned with particular campaigns
	@Override
	public List<Campaign> getAllResourcesCampaignDetails()throws CampaignNotFoundException {
		List<Campaign> campaignList= campaignRepository.findAllResourcesCampaignDetails();
		if(campaignList.isEmpty())
		{
			throw new CampaignNotFoundException("No campaign found");
		}
		return campaignList;
	}

}
