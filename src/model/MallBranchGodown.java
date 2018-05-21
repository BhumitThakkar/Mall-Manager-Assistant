package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_mall_branch_godown")
public class MallBranchGodown {

	@Id
	@GenericGenerator(name = "mbg", strategy = "increment")
	@GeneratedValue(generator = "mbg")
	@Column(name = "Id")
	private Long id;

	@Column(name = "M_B_G_MB")
	private String m_b_g_mb;

	@Column(name = "Mall_Name")
	private String mall_name;

	@Column(name = "Landmark")
	private String landmark;

	@Column(name = "Area")
	private String area;

	@Column(name = "City")
	private String city;

	@Column(name = "State")
	private String state;

	@Column(name = "Country")
	private String country;

	@Column(name = "Pin_Code")
	private Long pincode;

	@Column(name = "Mobile_Number")
	private Long mobile_number;

	@Column(name = "Status")
	private Character status;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "manager_mall", inverseJoinColumns = { @JoinColumn(name = "manager_id") }, joinColumns = {
			@JoinColumn(name = "mall_branch_godown_id") })
	private Set<Manager> managers = new HashSet<Manager>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Company> companies = new HashSet<Company>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Category> categories = new HashSet<Category>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Product> products = new HashSet<Product>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Sale> sales = new HashSet<Sale>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<AddProductQuantity> addProductQuantity = new HashSet<AddProductQuantity>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Unit> units = new HashSet<Unit>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Size> sizes = new HashSet<Size>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Spoil> spoils = new HashSet<Spoil>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Profit> profits = new HashSet<Profit>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mallBranchGodown", fetch = FetchType.EAGER)
	private Set<Reminder> reminders = new HashSet<Reminder>();

	public Set<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(Set<Reminder> reminders) {
		this.reminders = reminders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getM_b_g_mb() {
		return m_b_g_mb;
	}

	public void setM_b_g_mb(String m_b_g_mb) {
		this.m_b_g_mb = m_b_g_mb;
	}

	public String getMall_name() {
		return mall_name;
	}

	public void setMall_name(String mall_name) {
		this.mall_name = mall_name;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}

	public Set<Manager> getManagers() {
		return managers;
	}

	public void setManagers(Set<Manager> managers) {
		this.managers = managers;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Unit> getUnits() {
		return units;
	}

	public void setUnits(Set<Unit> units) {
		this.units = units;
	}

	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	public Set<Sale> getSales() {
		return sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Set<AddProductQuantity> getAddProductQuantity() {
		return addProductQuantity;
	}

	public void setAddProductQuantity(Set<AddProductQuantity> addProductQuantity) {
		this.addProductQuantity = addProductQuantity;
	}

	public Set<Spoil> getSpoils() {
		return spoils;
	}

	public void setSpoils(Set<Spoil> spoils) {
		this.spoils = spoils;
	}

	public Set<Profit> getProfits() {
		return profits;
	}

	public void setProfits(Set<Profit> profits) {
		this.profits = profits;
	}
	
}