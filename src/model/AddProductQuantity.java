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
@Table(name="tbl_add_product_quantity")
public class AddProductQuantity {

	@Id
	@GenericGenerator(name="productQuantity",strategy="increment")
	@GeneratedValue(generator="productQuantity")
	@Column(name="Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name="Product_Number")
	private String productNumber;

	@Column(name="Product_Entry_Date")
	private String productEntryDate;

	@Column(name="Add_Product_Quantity")
	private Double addProductQuantity;
	
	@Column(name="CP")
	private Double cp;

	@Column(name="SP")
	private Double sp;

	@Column(name="Sold",columnDefinition = "double default 0.0")
	private Double sold;

	@Column(name="Spoil",columnDefinition = "double default 0.0")
	private Double spoil;

	public Double getSpoil() {
		return spoil;
	}

	public void setSpoil(Double spoil) {
		this.spoil = spoil;
	}

	public Double getSold() {
		return sold;
	}

	public void setSold(Double sold) {
		this.sold = sold;
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

	public String getProductEntryDate() {
		return productEntryDate;
	}

	public void setProductEntryDate(String productEntryDate) {
		this.productEntryDate = productEntryDate;
	}

	public Double getAddProductQuantity() {
		return addProductQuantity;
	}

	public void setAddProductQuantity(Double addProductQuantity) {
		this.addProductQuantity = addProductQuantity;
	}

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

}