package com.previousExam.hibernate.model;

import java.util.Date;

public class Album implements IAlbum {

	@Override
	public String toString() {
		return "Album [id=" + id + ", releaseDate=" + releaseDate
				+ ", version=" + version + ", name=" + name + ", price="
				+ price + "]";
	}

	private long id;
	private Date releaseDate;
	private int version;
	private String name;
	private double price;
	private Label label;

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Date getReleaseDate() {
		return releaseDate;
	}

	@Override
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;

	}

	@Override
	public int getVersion() {
		return version;

	}

	@Override
	public void setVersion(int version) {
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
	public void setPrice(double price) {
		this.price = price;

	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setLabel(Label l) {
		label = l;
	}

	@Override
	public Label getLabel() {
		return label;
	}

}
