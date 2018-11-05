
package com.communityratesgames.platform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Getter
@AllArgsConstructor
public class PlatformModel {
	private Integer id;
	private String name;
	private int releaseYear;

	public PlatformModel(String name, int releaseYear) {
		this.name = name;
		this.releaseYear = releaseYear;
	}

	public PlatformModel(PlatformEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.releaseYear = entity.getReleaseYear();
	}

	protected PlatformModel() { }

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getReleaseYear() {
		return this.releaseYear;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
}
