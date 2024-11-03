package com.kafka.app.model;

import java.io.Serializable;

/**
 * Model class representing user data that will be processed in the pipeline.
 * Implements Serializable to allow data transfer and storage.
 */
public class UserData implements Serializable {

	/**
	 * Default serialVersionID
	 */
	private static final long serialVersionUID = 1L;

	// Name of the user
	private String name;

	// Address of the user
	private String address;

	// Date of birth of the user in format: YYYY-MM-DD
	private String dateOfBirth;

	/**
	 * Constructor to initialize UserData with the specified name, address, and date
	 * of birth.
	 *
	 * @param name        the name of the user
	 * @param address     the address of the user
	 * @param dateOfBirth the date of birth of the user in YYYY-MM-DD format
	 */
	public UserData(String name, String address, String dateOfBirth) {
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the name of the user.
	 *
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the address of the user.
	 *
	 * @return the address of the user
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets the date of birth of the user.
	 *
	 * @return the date of birth in YYYY-MM-DD format
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
}
