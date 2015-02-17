package com.previousExam.hibernate.model;

import java.util.Set;

/**
 * A PARTIAL implementation of a Label.
 * 
 * @author John T. Saxon
 */
public class Label implements ILabel {

	@Override
	public String toString() {
		return "Label [id=" + id + ", version=" + version + ", name=" + name
				+ ", albums=" + albums + "]";
	}

	private long id;
	private int version;
	private String name;
	private Set<Album> albums;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public void setAlbums(Set<Album> albums) {
		this.albums = albums;

	}

	@Override
	public Set<Album> getAlbums() {
		return albums;

	}

}
