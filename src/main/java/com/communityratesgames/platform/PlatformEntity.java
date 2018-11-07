
package com.communityratesgames.platform;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

import com.communityratesgames.company.CompanyEntity;

@Getter
@Setter
@Entity
public class PlatformEntity implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(length=80)
	private String name;

	private int releaseYear;

	@ManyToOne
	@JoinColumn
	private CompanyEntity company;

	public PlatformEntity(String name, int releaseYear, CompanyEntity company) {
		this.name = name;
		this.releaseYear = releaseYear;
		this.company = company;
	}

	public PlatformEntity(PlatformModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.releaseYear = model.getReleaseYear();
		this.company = new CompanyEntity(model.getCompany());
	}

	protected PlatformEntity() { }
}
