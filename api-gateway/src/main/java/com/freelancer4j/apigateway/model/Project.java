package com.freelancer4j.apigateway.model;

import java.io.Serializable;

public class Project implements Serializable {


    private String projectId;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerEmail;
    private String projectTitle;
    private String projectDesc;
    private String projectStatus;

    public Project() {

    }
    
    public Project(String projectId) {
    	this.projectId = projectId;
    }
    
    public Project(String projectId, String projectStatus) {
    	this.projectId = projectId;
    	this.projectStatus = projectStatus;
    }

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
    
	@Override
    public String toString() {
        return "Project [projectId=" + projectId + ", projectStatus=" + projectStatus+"]";
    }

}
