package com.dashb.framework.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "countbyseg", schema = "pssdash")
public class CountBySegEntity {
	
	@Id
    private int id;
   
	/**
	 * monyear varchar(10) default null,
	segment varchar(2) default null,
	segmentdesc varchar(100) default null,
	totalact varchar(20) default null,
	actinmonth varchar(20) default null
	 */
	private String monyear;
	private String segmentdesc;
    private String segment;
    private String totalact;
    private String actinmonth;
    
    /*private Timestamp createddt;
    private String count;
    private String channelind;
    private String country;
    private String txnperformed;
*/
	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
    @Column(name = "segmentdesc")
	public String getSegmentdesc() {
		return segmentdesc;
	}

	public void setSegmentdesc(String segmentdesc) {
		this.segmentdesc = segmentdesc;
	}

    
	@Basic
    @Column(name = "segment")
	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	@Basic
    @Column(name = "monyear")
	public String getMonyear() {
		return monyear;
	}

	public void setMonyear(String monyear) {
		this.monyear = monyear;
	}

	@Basic
    @Column(name = "totalact")
	public String getTotalact() {
		return totalact;
	}

	public void setTotalact(String totalact) {
		this.totalact = totalact;
	}

	@Basic
    @Column(name = "actinmonth")
	public String getActinmonth() {
		return actinmonth;
	}

	public void setActinmonth(String actinmonth) {
		this.actinmonth = actinmonth;
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
