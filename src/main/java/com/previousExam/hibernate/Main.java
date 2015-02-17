package com.previousExam.hibernate;

import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.previousExam.hibernate.model.ITrack;

public class Main {
	public static void main(String[] args) {
		// the session factory
		SessionFactory factory = null;

		try {
			// have we loaded the postgres driver?
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		try {
			// ignore case of command
			if (args.length < 1) {
				throw new IllegalArgumentException(
						"Program hasn't been called correctly.");
			}

			// prepare a configuration file
			Configuration configuration = new Configuration();
			configuration.configure(); // useing hibernate.cfg.xml
			// create a service registry (for the factory)
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			// build the factory itself
			factory = configuration.buildSessionFactory(serviceRegistry);

			// create the service object
			MusicService service = new MusicService(factory);

			// get the command
			String command = args[0];
			boolean success;

			switch (command) {
			case "DisplayTrack":
				if (args.length != 2) {
					throw new IllegalArgumentException();
				}

				ITrack track = null;
				try {
					// get the track using the service
					track = service.getTrackById(args[1]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("The trackId: "
							+ args[1] + " is not a value integer.");
				}

				if (track == null) {
					System.err
							.println("There is no track available with 'id' of "
									+ args[1] + ".");
				} else {
					// only if we have a track with this id
					System.out
							.println(String
									.format("%-20s | %-8s | %-8s\n=======================================================",
											"Title", "Length", "Genre"));
					System.out.println(
					// http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax
							String.format(
									"%-20s | %2$tH:%2$tM:%2$tS | %3$-20s",
									track.getName(), track.getLength(), track
											.getGenre().getName()));
				}

				break;
			case "AddTrack":
				if (args.length != 4) {
					throw new IllegalArgumentException();
				}

				try {
					// attempt to add the track
					success = service.addTrack(args[1], args[2], args[3]);
					if (success) {
						System.out.println("Track has been added.");
					} else {
						System.err.println("Track not not been added.");
					}
				} catch (ParseException e) {
					throw new IllegalArgumentException(e.getMessage());
				}

			case "AddAlbum":
				if (args.length != 4) {
					throw new IllegalArgumentException();
				}
				success = service.addAlbum(args[1], args[2], args[3]);
				if (success) {
					System.out.println("Album has been added.");
				} else {
					System.err.println("Album not not added.");
				}

			case "SetRecordLabel":
				if (args.length != 3) {
					throw new IllegalArgumentException();
				}
				success = service.setRecordLabel(args[1], args[2]);
				if (success) {
					System.out.println("Record label  has been updated.");
				} else {
					System.err.println("Record label has not been updated");
				}

			case "AddRecordLabel":
				if (args.length != 2) {
					throw new IllegalArgumentException();
				}
				success = service.addRecordLabel(args[1]);
				if (success) {
					System.out.println("Record label  has been added.");
				} else {
					System.err.println("Record label has not been added");
				}

			case "PrintRecordLabel":
				if (args.length != 2) {
					throw new IllegalArgumentException();
				}
				success = service.printRecordLabel(args[1]);
				if (success) {
					System.out.println("Should of printed");
				} else {
					System.err.println("nothing to print");
				}

			}
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			// print out a specific error if we have one
			if (e.getMessage() != null) {
				System.err.println("Error: " + e.getMessage());
			}
			// always print out the usage
			System.err.println(Main.getUsage());
		} finally {
			// is the database open?
			if (factory != null && !factory.isClosed()) {
				factory.close(); // close it.
			}
		}
	}

	public static String getUsage() {
		return "DisplayTrack <trackId>\n"
				+ "AddTrack <trackName> <trackLength> <trackGenre>";
	}
}
