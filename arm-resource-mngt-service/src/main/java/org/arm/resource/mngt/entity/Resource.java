package org.arm.resource.mngt.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIdentityInfo(
		scope = Task.class,
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "resourceId")
public class Resource {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resourceId;
	private String resourceName;
	private String resourceType;
	private Timestamp createDate;
	private Timestamp updateDate;
	private int isDeleted;
		
	@OneToMany(mappedBy="resource")
	private List<Task> taskList;
	private String resourceImage;
	private String region;
	@OneToOne
	@JoinColumn(name="availableId")
	private Availability availability;
	
	@OneToOne
	@JoinColumn(name="leaveId")
	private Leaves leave;


//	public int getResourceId() {
//		return resourceId;
//	}
//
//	public void setResourceId(int resourceId) {
//		this.resourceId = resourceId;
//	}
//
//	public String getResourceName() {
//		return resourceName;
//	}
//
//	public void setResourceName(String resourceName) {
//		this.resourceName = resourceName;
//	}
//
//	public String getResourceType() {
//		return resourceType;
//	}
//
//	public void setResourceType(String resourceType) {
//		this.resourceType = resourceType;
//	}
//
//	public Timestamp getCreateDate() {
//		return createDate;
//	}
//
//	public void setCreateDate(Timestamp createDate) {
//		this.createDate = createDate;
//	}
//
//	public Timestamp getUpdateDate() {
//		return updateDate;
//	}
//
//	public void setUpdateDate(Timestamp updateDate) {
//		this.updateDate = updateDate;
//	}
//
//	public int getIsDeleted() {
//		return isDeleted;
//	}
//
//	public void setIsDeleted(int isDeleted) {
//		this.isDeleted = isDeleted;
//	}
//
//	public List<Task> getTaskList() {
//		return taskList;
//	}
//
//	public void setTaskList(List<Task> taskList) {
//		this.taskList = taskList;
//	}
//
//	public Availability getAvailability() {
//		return availability;
//	}
//
//	public void setAvailability(Availability availability) {
//		this.availability = availability;
//	}
//
//	public Leaves getLeave() {
//		return leave;
//	}
//
//	public void setLeave(Leaves leave) {
//		this.leave = leave;
//	}
//	
	
}
