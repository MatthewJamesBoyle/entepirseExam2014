package com.previousExam.hibernate.model;

import java.util.Set;

/**
 * A PARTIAL interface of a Label.
 * 
 * @author John T. Saxon
 */
public interface ILabel {
	/**
	 * The ID of the Label.
	 * 
	 * @return
	 */
	public Long getId();

	/**
	 * Set the ID of a Label.
	 * 
	 * @param length
	 */
	public void setId(Long id);

	/**
	 * The version of the Label.
	 * 
	 * @return
	 */
	public Integer getVersion();

	/**
	 * Set the version of a Label.
	 * 
	 * @param length
	 */
	public void setVersion(Integer version);

	/**
	 * The name of the Label
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Set the name of the Label.
	 * 
	 * @param name
	 */
	public void setName(String name);

	public void setAlbums(Set<Album> albums);

	public Set<Album> getAlbums();

}
