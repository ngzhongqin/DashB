package com.dashb.framework.vo;

import java.sql.Timestamp;

public class TxnCountVO {
	
    private int id;
    private String txndesc;
    private String txncode;
    private Timestamp createddt;
    private String monyear;
    private String count;
    private String channeind;
    private String country;
    private String finnonfin;

    public TxnCountVO(){
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTxndesc() {
		return txndesc;
	}

	public void setTxndesc(String txndesc) {
		this.txndesc = txndesc;
	}

	public String getTxncode() {
		return txncode;
	}

	public void setTxncode(String txncode) {
		this.txncode = txncode;
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

	public String getFinnonfin() {
		return finnonfin;
	}

	public void setFinnonfin(String finnonfin) {
		this.finnonfin = finnonfin;
	}

}
