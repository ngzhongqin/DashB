package com.dashb.framework.database.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by zhongqinng on 27/7/15.
 */
@Entity
@Table(name = "ssl", schema = "pssdash", catalog = "pssdash")
public class SslEntity {

    private String po;
    private String poStatus;
    private String country;
    private String environment;
    private String application;
    private String server;
    private String organization;
    private String organizational_unit;

    private String common_name;
    private String key_strength;
    private String cert_type;
    private String team_involved;
    private String owner;


    private Date start_date;
    private Date end_date;
    private Date datedue;


    @Id
    @SequenceGenerator(name="ssl_id_seq",
            sequenceName="ssl_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="ssl_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;

    public long getId(){return this.id;}
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "po")
    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }


    @Basic
    @Column(name = "postatus")
    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "organization")
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


    @Basic
    @Column(name = "application")
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "server")
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Basic
    @Column(name = "environment")
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }




    @Basic
    @Column(name = "organizational_unit")
    public String getOrganizational_unit() {
        return organizational_unit;
    }

    public void setOrganizational_unit(String organizational_unit) {
        this.organizational_unit = organizational_unit;
    }


    @Basic
    @Column(name = "common_name")
    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    @Basic
    @Column(name = "key_strength")
    public String getKey_strength() {
        return key_strength;
    }

    public void setKey_strength(String key_strength) {
        this.key_strength = key_strength;
    }

    @Basic
    @Column(name = "cert_type")
    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }







    @Basic
    @Column(name = "start_date")
    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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
    @Column(name = "team_involved")
    public String getTeam_involved() {
        return team_involved;
    }

    public void setTeam_involved(String team_involved) {
        this.team_involved = team_involved;
    }

    @Basic
    @Column(name = "datedue")
    public Date getDatedue() {
        return datedue;
    }

    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }

}
