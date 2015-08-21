package com.dashb.framework.vo;

import java.sql.Timestamp;

public class ActCustVO {
	
    private int id;
    private String segmentdesc;
    private String segment;
    private Timestamp createddt;
    private String monyear;
    private String count;
    private String channeind;
    private String country;
    private String txnperformed;

    public ActCustVO(){
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSegmentdesc() {
		return segmentdesc;
	}

	public void setSegmentdesc(String segmentdesc) {
		this.segmentdesc = segmentdesc;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public Timestamp getCreateddt() {
		return createddt;
	}

	public void setCreateddt(Timestamp createddt) {
		this.createddt = createddt;
	}

	public String getMonyear() {
		return monyear;
	}

	public void setMonyear(String monyear) {
		this.monyear = monyear;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getChanneind() {
		return channeind;
	}

	public void setChanneind(String channeind) {
		this.channeind = channeind;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTxnperformed() {
		return txnperformed;
	}

	public void setTxnperformed(String txnperformed) {
		this.txnperformed = txnperformed;
	}

}
