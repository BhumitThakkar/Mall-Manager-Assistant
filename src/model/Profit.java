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
@Table(name="tbl_profit")
public class Profit {

	@Id
	@GenericGenerator(name="profit",strategy="increment")
	@GeneratedValue(generator="profit")
	@Column(name="Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name="Product_Number")
	private String productNumber;
	
	@Column(name="CP")
	private Double cp;

	@Column(name="SP")
	private Double sp;
	
	@Column(name="Sold_Quantity")
	private Double soldQuantity;
	
	@Column(name="Spoil_Quantity")
	private Double spoilQuantity;

	@Column(name="Today_Date")
	private String todayDate;

	@Column(name="Profit")
	private Double profit;

	public Double getCp() {
		return cp;
	}

	public void setCp(Double cp) {
		this.cp = cp;
	}

	public Double getSp() {
		return sp;
	}

	public void setSp(Double sp) {
		this.sp = sp;
	}

	public Double getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Double soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Double getSpoilQuantity() {
		return spoilQuantity;
	}

	public void setSpoilQuantity(Double spoilQuantity) {
		this.spoilQuantity = spoilQuantity;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

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

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}
	
}