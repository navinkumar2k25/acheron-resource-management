package org.arm.resource.mngt.repository;

import java.util.List;

import org.arm.resource.mngt.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	@Query(value = ("select * from campaign c left join project p on p.campaign_id=c.campaign_id left join task t on t.project_id=p.project_id right join resource r on t.resource_id=r.resource_id"),nativeQuery=true)
	List<Campaign> findAllResourcesCampaignDetails();
}
