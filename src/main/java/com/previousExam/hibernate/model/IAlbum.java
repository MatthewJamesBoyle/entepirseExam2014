package com.previousExam.hibernate.model;

import java.util.Date;

public interface IAlbum {

	public abstract long getId();

	public abstract void setId(long id);

	public abstract Date getReleaseDate();

	public abstract void setReleaseDate(Date releaseDate);

	public abstract int getVersion();

	public abstract void setVersion(int version);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract void setPrice(double price);

	public abstract double getPrice();

	public void setLabel(Label l);

	public Label getLabel();

}