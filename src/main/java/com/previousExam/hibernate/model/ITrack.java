package com.previousExam.hibernate.model;

import java.sql.Time;

/**
 * A PARTIAL interface of a Track.
 * 
 * @author John T. Saxon
 */
public interface ITrack {
	/**
	 * The ID of the track.
	 * 
	 * @return
	 */
	public Long getId();
	
	/**
	 * Set the ID of a track.
	 * 
	 * @param length
	 */
	public void setId(Long id);
	
	/**
	 * The version of the track.
	 * 
	 * @return
	 */
	public Integer getVersion();
	
	/**
	 * Set the version of a track.
	 * 
	 * @param length
	 */
	public void setVersion(Integer id);
	
	/**
	 * The length of a track.
	 * 
	 * @return
	 */
	public Time getLength();
	
	/**
	 * Set the length of a track.
	 * 
	 * @param length
	 */
	public void setLength(Time length);
	
	/**
	 * The title of the Track
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * Set the title of the track.
	 * 
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * The Genre of the Track
	 * 
	 * @return
	 */
	public IGenre getGenre();
	
	/**
	 * Set the Genre of the Track.
	 * 
	 * @param name
	 */
	public void setGenre(IGenre genre);
}
