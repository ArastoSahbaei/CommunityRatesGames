
package com.communityratesgames.platform;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Platform implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;

	@Column(length=80)
	String name;

	int releaseYear;

	public Platform(String name, int releaseYear) {
		this.name = name;
		this.releaseYear = releaseYear;
	}

	protected Platform() { }

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
