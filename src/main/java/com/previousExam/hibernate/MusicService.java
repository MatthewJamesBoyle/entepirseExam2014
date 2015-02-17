package com.previousExam.hibernate;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.previousExam.hibernate.model.Album;
import com.previousExam.hibernate.model.IAlbum;
import com.previousExam.hibernate.model.IGenre;
import com.previousExam.hibernate.model.ILabel;
import com.previousExam.hibernate.model.ITrack;
import com.previousExam.hibernate.model.Label;
import com.previousExam.hibernate.model.Track;

public class MusicService {
	/**
	 * A session factory.
	 */
	private SessionFactory factory = null;

	/**
	 * Setup a music service with an open session factory.
	 * 
	 * @param factory
	 */
	public MusicService(SessionFactory factory) {
		this.factory = factory;
	}

	/**
	 * 
	 * @return
	 */
	public SessionFactory getFactory() {
		return factory;
	}

	/**
	 * 
	 * @param trackId
	 * @return
	 */
	public ITrack getTrackById(String trackId) {
		Integer id = null;
		try {
			// make sure we can parse the input
			id = Integer.parseInt(trackId);
		} catch (NumberFormatException e) {
			throw e;
		}

		ITrack track = null;

		// open a session to the database
		Session session = this.getFactory().openSession();
		Transaction transaction = null;

		try {
			// start a transaction
			transaction = session.beginTransaction();

			// attempt to get the track from the database
			track = (ITrack) session
					.createQuery("from Track t where t.id = :id")
					.setLong("id", id).uniqueResult();

			if (track != null) {
				Hibernate.initialize(track.getGenre());
			}

			// write the transaction
			transaction.commit();
		} catch (HibernateException e) {
			// if our transaction still lives, close it
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		} finally {
			// close the session
			if (session.isOpen()) {
				session.close();
			}
		}

		return track;
	}

	/**
	 * 
	 * @param genreName
	 * @return
	 */
	public IGenre getGenreByName(String genreName) {
		IGenre genre = null;

		// open a session to the database
		Session session = this.getFactory().openSession();
		Transaction transaction = null;

		try {
			// start a transaction
			transaction = session.beginTransaction();

			// attempt to get the track from the database
			genre = (IGenre) session
					.createQuery("from Genre g where g.name = :name")
					.setString("name", genreName).uniqueResult();

			// write the transaction
			transaction.commit();
		} catch (HibernateException e) {
			// if our transaction still lives, close it
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		} finally {
			// close the session
			if (session.isOpen()) {
				session.close();
			}
		}

		return genre;
	}

	public boolean addAlbum(String albumName, String releaseDate, String price) {
		// begin the transaction
		// open a session to the database
		Session session = this.getFactory().openSession();
		Transaction transaction = null;
		Date dateToCheck = null;
		Label randomLabel = (Label) session.createQuery("from Label").list()
				.get(0);

		double priceChecked;
		transaction = session.beginTransaction();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dateToCheck = date.parse(releaseDate);
		} catch (ParseException e1) {
			throw new IllegalArgumentException("date wasn't a valid format");
		}

		try {
			priceChecked = Double.valueOf(price);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Not a valid price");
		}

		Query q = session.createQuery("from Album where name=:aname");
		q.setString("aname", albumName);
		List albumNameResults = q.list();
		if (albumNameResults.size() > 0) {
			throw new IllegalArgumentException("Album is already in DB");

		}

		IAlbum albumToAdd = new Album();
		albumToAdd.setName(albumName);
		albumToAdd.setReleaseDate(dateToCheck);
		albumToAdd.setPrice(priceChecked);
		albumToAdd.setLabel(randomLabel);
		session.save(albumToAdd);
		transaction.commit();
		return true;

	}

	public boolean printRecordLabel(String recordLabel) {
		Session session = this.getFactory().openSession();
		Label l = null;

		Query q = session.createQuery("FROM Label where name=:lname");
		q.setString("lname", recordLabel);
		List recordLabelList = q.list();

		if (recordLabelList.size() < 1) {
			throw new IllegalArgumentException(
					"There was no record label with this name");

		} else {
			l = (Label) recordLabelList.get(0);
			System.out.println("albums on the label " + recordLabel);
			for (Album n : l.getAlbums()) {

				System.out.println(n.toString());
			}
			return true;
		}

	}

	public boolean setRecordLabel(String albumName, String recordLabel) {
		Session session = this.getFactory().openSession();
		Transaction tx = null;
		Album toUpdate = null;
		Label label = null;

		Query q = session.createQuery("from Album where name=:aname");
		q.setString("aname", albumName);
		List albumNameResults = q.list();
		if (albumNameResults.size() <= 0) {
			throw new IllegalArgumentException("Album doesn't exist");

		} else {
			toUpdate = (Album) albumNameResults.get(0);

		}

		Query q2 = session.createQuery("from Label where name=:lname");
		q2.setString("lname", recordLabel);
		List recordLabelResults = q2.list();
		if (recordLabelResults.size() <= 0) {
			throw new IllegalArgumentException("Label  doesn't exist");

		} else {
			label = (Label) recordLabelResults.get(0);

		}

		tx = session.beginTransaction();
		toUpdate.setLabel(label);
		session.save(label);
		tx.commit();
		return true;

	}

	public boolean addRecordLabel(String recordLabel) {
		Session session = this.getFactory().openSession();
		Transaction tx = null;
		ILabel labelToAdd = null;
		Query q = session.createQuery("from Label where name=:lname");
		q.setString("lname", recordLabel);
		List toCheck = q.list();
		if (toCheck.size() > 0) {
			throw new IllegalArgumentException("label already exists");
		} else {
			tx = session.beginTransaction();
			labelToAdd = new Label();
			labelToAdd.setName(recordLabel);
			session.save(labelToAdd);
			tx.commit();

			return true;
		}

	}

	public boolean addTrack(String trackName, String trackLength,
			String genreName) throws IllegalArgumentException, ParseException {
		// open a session to the database
		Session session = this.getFactory().openSession();
		Transaction transaction = null;

		try {
			// begin the transaction
			transaction = session.beginTransaction();

			Time length = Time.valueOf(trackLength);

			IGenre genre = this.getGenreByName(genreName);
			if (genre == null) {
				throw new IllegalArgumentException("Genre " + genreName
						+ " doesn't exist.");
			}

			// reconnect to our session
			session.update(genre);

			ITrack track = new Track();
			track.setGenre(genre);
			track.setLength(length);
			track.setName(trackName);

			session.save(track);

			transaction.commit();

			return true;
		} catch (HibernateException e) {
			// if our transaction still lives, close it
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			if (e.getCause() instanceof SQLException) {
				SQLException se = (SQLException) e.getCause();
				if (se.getSQLState().equals("23505")) {
					throw new IllegalArgumentException("Track " + trackName
							+ " already exists.");
				}
			}
		} finally {
			// should never happen
			// but just in case ...
			if (transaction.isActive()) {
				transaction.rollback();
			}

			// close the session
			if (session.isOpen()) {
				session.close();
			}
		}

		return false;
	}

}
