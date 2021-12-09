package org.arm.resource.mngt.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
	@Id
	private int campaignId;
	private String campaignName;
	private String campaignOwner;
	private Timestamp startDate;
	private Timestamp endDate;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@Enumerated(EnumType.STRING)
	private Status status;
	private Timestamp createDate;
	private Timestamp updateDate;
	private int isDeleted;
	private String createdBy;
	private String updatedBy;
	private String region;
	@OneToMany(mappedBy = "campaign")
	private Set<Project> projects;


	public Campaign(Set<Project> projects) {
		super();
		this.projects = projects;
	}
	
	@Override
	public String toString() {
		return "Campaign [campaignId=" + campaignId + ", campaignName=" + campaignName + ", campaignOwner="
				+ campaignOwner + ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority
				+ ", status=" + status + ", createDate=" + createDate + ", updateDate=" + updateDate + ", isDeleted="
				+ isDeleted + "]";
	}

	
}
