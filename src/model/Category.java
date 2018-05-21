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
@Table(name = "tbl_category")
public class Category {

	@Id
	@GenericGenerator(name = "category", strategy = "increment")
	@GeneratedValue(generator = "category")
	@Column(name = "Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MallBranchGodown_Id")
	private MallBranchGodown mallBranchGodown;

	@Column(name = "Category")
	private String category;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
	
}