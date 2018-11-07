
package com.communityratesgames.platform;

import lombok.*;

import java.io.Serializable;
import java.util.List;

import com.communityratesgames.company.CompanyModel;
import com.communityratesgames.company.CompanyEntity;

@Getter
@Setter
@NoArgsConstructor
public class PlatformModel {
	private Integer id;
	private String name;
	private int releaseYear;
	private CompanyModel company;

	public PlatformModel(PlatformEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.releaseYear = entity.getReleaseYear();
		this.company = new CompanyModel(entity.getCompany());
	}
}
