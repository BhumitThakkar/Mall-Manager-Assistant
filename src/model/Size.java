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
@Table(name="tbl_size")
public class Size {
		
		@Id
		@GenericGenerator(name="size",strategy="increment")
		@GeneratedValue(generator="size")
		@Column(name="Id")
		private Long id;

		@ManyToOne
		@JoinColumn(name="MallBranchGodown_Id")
		private MallBranchGodown mallBranchGodown;
		
		@Column(name="Size")
		private String size;

		@Column(name="Status")
		private Character status;

		public Long getId() {
			return id;
		}

		public MallBranchGodown getMallBranchGodown() {
			return mallBranchGodown;
		}

		public void setMallBranchGodown(MallBranchGodown mallBranchGodown) {
			this.mallBranchGodown = mallBranchGodown;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public Character getStatus() {
			return status;
		}

		public void setStatus(Character status) {
			this.status = status;
		}

		public void setId(Long id) {
			this.id = id;
		}
}