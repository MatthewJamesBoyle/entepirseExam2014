package com.previousExam.hibernate.model;

import java.sql.Time;

/**
 * A PARTIAL implementation of a Track.
 * 
 * @author John T. Saxon
 */
public class Track implements ITrack {
	/**
	 * @see Track.getId()
	 */
	private Long id;
	
	/**
	 * @see Track.getVersion()
	 */
	private Integer version;
	
	/**
	 * @see Track.getLength()
	 */
	private Time length;
	
	/**
	 * @see Track.getName()
	 */
	private String name;
	
	/**
	 * @see Track.getGenre()
	 */
	private IGenre genre;
	
	/**
	 * 
	 */
	public Track() {
		
	}
	
	/**
	 * 
	 * @param name
	 * @param length
	 */
	public Track(String name, Time length, IGenre genre) {
		this.setName(name);
		this.setLength(length);
		this.setGenre(genre);
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Integer getVersion() {
		return this.version;
	}
	
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Time getLength() {
		return this.length;
	}
	
	@Override
	public void setLength(Time length) {
		this.length = length;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public IGenre getGenre() {
		return this.genre;
	}
	
	@Override
	public void setGenre(IGenre genre) {
		this.genre = genre;
	}
}
