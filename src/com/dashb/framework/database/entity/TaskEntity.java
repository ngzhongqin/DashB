package com.dashb.framework.database.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by zhongqinng on 27/7/15.
 */
@Entity
@Table(name = "task", schema = "pssdash", catalog = "pssdash")
public class TaskEntity {
    private String description;
    private String defect;
    private String incident;
    private String status;
    private Timestamp createddt;
    private String owner;
    private String remarks;
    private Date datedue;


    @Id
    @SequenceGenerator(name="task_id_seq",
            sequenceName="task_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="task_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;

    public long getId(){return this.id;}
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "defect")
    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    @Basic
    @Column(name = "incident")
    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createddt")
    public Timestamp getCreateddt() {
        return createddt;
    }

    public void setCreateddt(Timestamp createddt) {
        this.createddt = createddt;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "datedue")
    public Date getDatedue() {
        return datedue;
    }

    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (defect != null ? !defect.equals(that.defect) : that.defect != null) return false;
        if (incident != null ? !incident.equals(that.incident) : that.incident != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createddt != null ? !createddt.equals(that.createddt) : that.createddt != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;

        return true;
    }


}
