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
@Table(name="tbl_sale")
public class Sale {

	@Id
	@GenericGenerator(name="sale",strategy="increment")
	@GeneratedValue(generator="sale")
	@Column(name="Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name="Product_Number")
	private String productNumber;

	@Column(name="Sale_Date")
	private String saleDate;

	@Column(name="Sale_Quantity")
	private Double saleQuantity;
	
	@Column(name="SP")
	private Double sp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public Double getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Double saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public MallBranchGodown getMallBranchGodown() {
		return mallBranchGodown;
	}

	public void setMallBranchGodown(MallBranchGodown mallBranchGodown) {
		this.mallBranchGodown = mallBranchGodown;
	}

	public Double getSp() {
		return sp;
	}

	public void setSp(Double sp) {
		this.sp = sp;
	}

}