package org.arm.resource.mngt.service;

import java.util.List;
import java.util.Optional;

import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.exception.IDNotFoundException;
import org.arm.resource.mngt.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService implements ICampaignService {
	@Autowired
	CampaignRepository campaignRepository;

	public List<Campaign> getAllCampaign() {
		 List<Campaign> allCampaign= campaignRepository.findAll();
			if(allCampaign.isEmpty()) {
				throw new RuntimeException("no campaign found");
			}
			return allCampaign;

	}

	@Override
	public void createCampaign(Campaign campaign) {
		campaignRepository.save(campaign); 
	}

	@Override
	public Campaign findById(int id) throws IDNotFoundException{
		Optional<Campaign> campaignById=campaignRepository.findById(id);
		if(campaignById==null) {
			throw new IDNotFoundException("Id not found");
		}
		return campaignById.get();
	}
	
	@Override
	public List<Campaign> getAllResourcesCampaignDetails() {
		return campaignRepository.findAllResourcesCampaignDetails();
	}

}
