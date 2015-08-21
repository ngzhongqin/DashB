package com.dashb.framework.vo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by zhongqinng on 3/8/15.
 * SslVO
 */
public class SslVO {

    private long id;
    private String po;
    private String poStatus;
    private String country;
    private String environment;
    private String application;
    private String server;
    private String organization;
    private String organizational_unit;
    private String common_name;
    private Date start_date;
    private Date end_date;
    private Date datedue;
    private String key_strength;
    private String cert_type;
    private String team_involved;
    private String owner;

    public SslVO(){

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizational_unit() {
        return organizational_unit;
    }

    public void setOrganizational_unit(String organizational_unit) {
        this.organizational_unit = organizational_unit;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getDatedue() {
        return datedue;
    }

    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }

    public String getKey_strength() {
        return key_strength;
    }

    public void setKey_strength(String key_strength) {
        this.key_strength = key_strength;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public String getTeam_involved() {
        return team_involved;
    }

    public void setTeam_involved(String team_involved) {
        this.team_involved = team_involved;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
