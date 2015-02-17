package com.previousExam.hibernate.model;

/**
 * A PARTIAL implementation of a Genre.
 * 
 * @author John T. Saxon
 */
public class Genre implements IGenre {
	/**
	 * @see Genre.getId()
	 */
	private Long id;
	
	/**
	 * @see Genre.getVersion()
	 */
	private Integer version;
	
	
	/**
	 * @see Track.getName()
	 */
	private String name;
	
	/**
	 * 
	 */
	public Genre() {
		
	}
	
	/**
	 * 
	 * @param name
	 * @param length
	 */
	public Genre(String name) {
		this.name = name;
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
	public String getName() {
		return this.name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
}
