package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_spoil")
public class Spoil {
	@Id
	@GenericGenerator(name="spoil",strategy="increment")
	@GeneratedValue(generator="spoil")
	@Column(name="Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name="Product_Number")
	private String productNumber;

	@Column(name="Spoil_Date")
	private String spoilDate;

	@Column(name="Spoil_Quantity")
	private Double spoilQuantity;

	@Column(name="Spoil_Reason")
	private String spoilReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MallBranchGodown getMallBranchGodown() {
		return mallBranchGodown;
	}

	public void setMallBranchGodown(MallBranchGodown mallBranchGodown) {
		this.mallBranchGodown = mallBranchGodown;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getSpoilDate() {
		return spoilDate;
	}

	public void setSpoilDate(String spoilDate) {
		this.spoilDate = spoilDate;
	}

	public Double getSpoilQuantity() {
		return spoilQuantity;
	}

	public void setSpoilQuantity(Double spoilQuantity) {
		this.spoilQuantity = spoilQuantity;
	}

	public String getSpoilReason() {
		return spoilReason;
	}

	public void setSpoilReason(String spoilReason) {
		this.spoilReason = spoilReason;
	}
}
