package org.arm.resource.mngt.service;

import java.util.List;

import org.arm.resource.mngt.entity.Campaign;
import org.arm.resource.mngt.exception.IDNotFoundException;

public interface ICampaignService {

	public List<Campaign> getAllCampaign();

	public void createCampaign(Campaign campaign);

	public Campaign findById(int l) throws IDNotFoundException;
	
	List<Campaign> getAllResourcesCampaignDetails();
}
