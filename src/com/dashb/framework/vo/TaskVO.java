package com.dashb.framework.vo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by zhongqinng on 27/7/15.
 */
public class TaskVO {
    private long id;
    private String description;
    private String defect;
    private String incident;
    private String status;
    private Timestamp createddt;
    private String owner;
    private String remarks;
    private Date datedue;

    public TaskVO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateddt() {
        return createddt;
    }

    public void setCreateddt(Timestamp createddt) {
        this.createddt = createddt;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks){
        this.remarks=remarks;
    }

    public Date getDatedue() {
        return datedue;
    }

    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }
}
