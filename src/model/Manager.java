package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_manager")
public class Manager {
	
	@Id
	@GenericGenerator(name="manager",strategy="increment")
	@GeneratedValue(generator="manager")
	@Column(name="Id")
	private Long id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Mobile_Number")
	private Long mobile_number;
	
	@Column(name="Mail_Id")
	private String mail_id;
	
	@Column(name="Password")
	private String password;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="manager_mall",joinColumns={@JoinColumn(name="manager_id")},
			inverseJoinColumns={@JoinColumn(name="mall_branch_godown_id")})
	private Set<MallBranchGodown> mallBranchGodowns = new HashSet<MallBranchGodown>();
	
	@Column(name="Status")
	private Character status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Set<MallBranchGodown> getMallBranchGodowns() {
		return mallBranchGodowns;
	}
	public void setMallBranchGodowns(Set<MallBranchGodown> mallBranchGodowns) {
		this.mallBranchGodowns = mallBranchGodowns;
	}
}