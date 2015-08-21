package com.dashb.framework.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cxcount", schema = "pssdash")
public class CxCountEntity {
	
	@Id
	@Column(name = "id")
    private int id;
	
	@Basic
    @Column(name = "monyear")
    private String monyear;
	
	@Basic
    @Column(name = "performedtxn")
    private String performedtxn;
	
	@Basic
    @Column(name = "newcxcount")
    private String newcxcount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonyear() {
		return monyear;
	}

	public void setMonyear(String monyear) {
		this.monyear = monyear;
	}

	public String getPerformedtxn() {
		return performedtxn;
	}

	public void setPerformedtxn(String performedtxn) {
		this.performedtxn = performedtxn;
	}

	public String getNewcxcount() {
		return newcxcount;
	}

	public void setNewcxcount(String newcxcount) {
		this.newcxcount = newcxcount;
	}
    
	

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActCustEntity that = (ActCustEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (defect != null ? !defect.equals(that.defect) : that.defect != null) return false;
        if (incident != null ? !incident.equals(that.incident) : that.incident != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createddt != null ? !createddt.equals(that.createddt) : that.createddt != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (defect != null ? defect.hashCode() : 0);
        result = 31 * result + (incident != null ? incident.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createddt != null ? createddt.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }*/
}
