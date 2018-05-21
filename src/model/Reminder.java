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
@Table(name = "tbl_reminder")
public class Reminder {

	@Id
	@GenericGenerator(name = "reminder", strategy = "increment")
	@GeneratedValue(generator = "reminder")
	@Column(name = "Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name="Product_Number")
	private String productNumber;
	
	@Column(name="Reminder_Category")
	private String reminderCategory;

	@Column(name = "Reminder_Details")
	private String reminderDetails;

	@Column(name = "Status")
	private Character status;

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

	public String getReminderCategory() {
		return reminderCategory;
	}

	public void setReminderCategory(String reminderCategory) {
		this.reminderCategory = reminderCategory;
	}

	public String getReminderDetails() {
		return reminderDetails;
	}

	public void setReminderDetails(String reminderDetails) {
		this.reminderDetails = reminderDetails;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}