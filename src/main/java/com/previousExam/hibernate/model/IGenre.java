package com.previousExam.hibernate.model;

/**
 * A PARTIAL interface of a Genre.
 * 
 * @author John T. Saxon
 */
public interface IGenre {
	/**
	 * The ID of the Genre.
	 * 
	 * @return
	 */
	public Long getId();
	
	/**
	 * Set the ID of a Genre.
	 * 
	 * @param length
	 */
	public void setId(Long id);
	
	/**
	 * The version of the Genre.
	 * 
	 * @return
	 */
	public Integer getVersion();
	
	/**
	 * Set the version of a Genre.
	 * 
	 * @param length
	 */
	public void setVersion(Integer id);
	
	
	/**
	 * The name of the Genre
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * Set the name of the Genre.
	 * 
	 * @param name
	 */
	public void setName(String name);
}
