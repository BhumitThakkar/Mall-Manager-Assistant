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
@Table(name="tbl_product")
public class Product {
	
	@Id
	@GenericGenerator(name="product",strategy="increment")
	@GeneratedValue(generator="product")
	@Column(name="Id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name="Product_Number")
	private String productNumber;

	@Column(name="Company")
	private String company;

	@Column(name="Product_Name")
	private String productName;
	
	@Column(name="Color")
	private String color;

	@Column(name="Category")
	private String category;

	@Column(name="Size")
	private String size;
	
	@Column(name="Unit")
	private String unit;
	
	@Column(name="MP")
	private Double mp;

	@Column(name="Total_Quantity")
	private Double totalQuantity;
	
	@Column(name="Threshold_Quantity")
	private Double thresholdQuantity;
	
	@Column(name="Threshold_Sale_Per_Day")
	private Double thresholdSalePerDay;
	
	@Column(name="Threshold_Sale_Per_Week")
	private Double thresholdSalePerWeek;

	@Column(name="Threshold_Sale_Per_Month")
	private Double thresholdSalePerMonth;
	
	@Column(name="Threshold_Sale_Per_Mini_Sem")
	private Double thresholdSalePerMiniSem;

	@Column(name="Threshold_Sale_Per_Sem")
	private Double thresholdSalePerSem;

	@Column(name="Threshold_Sale_Per_Year")
	private Double thresholdSalePerYear;
	
	@Column(name="EXPIRY")
	private String expiry;

	@Column(name="Status")
	private Character status;

	@Column(name="TempCP")
	private Double tempCP;
	
	@Column(name="TempSP")
	private Double tempSP;
	
	@Column(name="Profit")
	private Double profit;

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getTempSP() {
		return tempSP;
	}

	public void setTempSP(Double tempSP) {
		this.tempSP = tempSP;
	}

	public Double getTempCP() {
		return tempCP;
	}

	public void setTempCP(Double tempCP) {
		this.tempCP = tempCP;
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

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getMp() {
		return mp;
	}

	public void setMp(Double mp) {
		this.mp = mp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getThresholdQuantity() {
		return thresholdQuantity;
	}

	public void setThresholdQuantity(Double thresholdQuantity) {
		this.thresholdQuantity = thresholdQuantity;
	}

	public Double getThresholdSalePerDay() {
		return thresholdSalePerDay;
	}

	public void setThresholdSalePerDay(Double thresholdSalePerDay) {
		this.thresholdSalePerDay = thresholdSalePerDay;
	}

	public Double getThresholdSalePerWeek() {
		return thresholdSalePerWeek;
	}

	public void setThresholdSalePerWeek(Double thresholdSalePerWeek) {
		this.thresholdSalePerWeek = thresholdSalePerWeek;
	}

	public Double getThresholdSalePerMonth() {
		return thresholdSalePerMonth;
	}

	public void setThresholdSalePerMonth(Double thresholdSalePerMonth) {
		this.thresholdSalePerMonth = thresholdSalePerMonth;
	}

	public Double getThresholdSalePerMiniSem() {
		return thresholdSalePerMiniSem;
	}

	public void setThresholdSalePerMiniSem(Double thresholdSalePerMiniSem) {
		this.thresholdSalePerMiniSem = thresholdSalePerMiniSem;
	}

	public Double getThresholdSalePerSem() {
		return thresholdSalePerSem;
	}

	public void setThresholdSalePerSem(Double thresholdSalePerSem) {
		this.thresholdSalePerSem = thresholdSalePerSem;
	}

	public Double getThresholdSalePerYear() {
		return thresholdSalePerYear;
	}

	public void setThresholdSalePerYear(Double thresholdSalePerYear) {
		this.thresholdSalePerYear = thresholdSalePerYear;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}