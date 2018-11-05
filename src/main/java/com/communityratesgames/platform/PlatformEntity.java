
package com.communityratesgames.platform;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class PlatformEntity implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(length=80)
	private String name;

	private int releaseYear;

	public PlatformEntity(String name, int releaseYear) {
		this.name = name;
		this.releaseYear = releaseYear;
	}

	protected PlatformEntity() { }

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
