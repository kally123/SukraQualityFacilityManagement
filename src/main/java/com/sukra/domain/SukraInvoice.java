package com.sukra.domain;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import io.springlets.format.EntityFormat;

/**
 * = SukraInvoice
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class SukraInvoice {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String sukraNo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date invoiceDate;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String paidTo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String purposeOf;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String sumOfRupees;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof SukraInvoice)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((SukraInvoice) obj).getId());
    }

    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Integer
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getSukraNo() {
        return this.sukraNo;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraNo
     */
    public void setSukraNo(String sukraNo) {
        this.sukraNo = sukraNo;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Date
     */
    public Date getInvoiceDate() {
        return this.invoiceDate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param invoiceDate
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getPaidTo() {
        return this.paidTo;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param paidTo
     */
    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getPurposeOf() {
        return this.purposeOf;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param purposeOf
     */
    public void setPurposeOf(String purposeOf) {
        this.purposeOf = purposeOf;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getSumOfRupees() {
        return this.sumOfRupees;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sumOfRupees
     */
    public void setSumOfRupees(String sumOfRupees) {
        this.sumOfRupees = sumOfRupees;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String toString() {
		return "SukraInvoice {" + "id='" + id + '\'' + ", sukraNo='" + sukraNo + '\'' + ", invoiceDate='" + invoiceDate
				+ '\'' + ", paidTo='" + paidTo + '\'' + ", purposeOf='" + purposeOf + '\'' + ", sumOfRupees='"
				+ sumOfRupees + "}";
    }
}
