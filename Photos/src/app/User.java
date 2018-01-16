package app;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is used to create and manage user objects. Each user
 * has a user name and a password that can be used to authenticate
 * and load their data.
 *
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class User implements Serializable {

	/**
	 * The username used by the user. Case sensitive.
	 */
	private String username;

	/**
	 * The password used to authenticate the user. Case sensitive.
	 */
	private String password;

	/**
	 * A list of albums that a user has
	 */
	private ArrayList<Album> albums;

	/**
	 * Creates a new user instance (only takes in a username, as the password is optional)
	 *
	 * @param username the username of the user
	 */
	public User(String username) {

		this.username = username;
		this.albums = new ArrayList<Album>();
	}

	/**
	 * Creates a new user instance.
	 *
	 * @param username the username of the new user
	 * @param password the password of the new user (null if no password)
	 */
	public User(String username, String password) {

		this.username = username;
		this.password = password;
		this.albums = new ArrayList<Album>();
	}
	
	/**
	 * Returns the username of a given user
	 *
	 * @return the username of a given user
	 */
	public String getUsername() {

		return this.username;
	}

	/**
	 * Returns the password for a given user.
	 *
	 * @return the password of a given user
	 */
	private String getPassword() {

		return this.password;
	}

	/**
	 * Returns the list of albums associated with that user
	 * 
	 * @return the list of albums
	 */
	public ArrayList<Album> getAlbums() {
		return this.albums;
	}

	/**
	 * Sets a new username for the user.
	 *
	 * @param username the new username for the user
	 */
	public void setUsername(String username) {

		this.username = username;
	}

	/**
	 * Sets the password of the user. The user must authenticate their
	 * current password before changing it.
	 *
	 * @param password the new password to be set.
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * Adds an album to the user
	 * 
	 * @param album the album to be added
	 */
	public void addAlbum(Album album) {
		// Scan through the albums
		for(int i = 0; i < albums.size(); i++) {

			// Check to see if there are any other albums with the same name
			if(albums.get(i).getAlbumName().equalsIgnoreCase(album.getAlbumName())) {

				Alert alert2 = new Alert(AlertType.ERROR);
	            alert2.setTitle("There is already a photo with this name.");
	            Optional<ButtonType> z = alert2.showAndWait();
	            if (z.get() == ButtonType.OK) {
	                alert2.close();
	            }

				return;
			}
		}

		// If there are no albums of the same name, add the album
		albums.add(album);
	}
	
	/**
	 * Attempts to authenticate the user (compare username and password)
	 * 
	 * @param username the inputted username
	 * @param password the inputted password
	 * @return true/false depending on the evaluation
	 */
	public boolean authenticate(String username, String password) {

		if(this.username.equals(username) && this.password.equals(password)) {

			return true;
		}

		return false;
	}

	/**
	 * Removes an album from the user
	 * 
	 * @param album the album to be removed
	 */
	public void removeAlbum(Album album) {

		if(albums.size() == 0) {

			Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("No photos in album.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
		}

		for(int i = 0; i < albums.size(); i++) {

			// if the photo name matches the photo that they want to remove
			// TODO: Photos can be of the same type, but have the same name. Add check for that.
			if(albums.get(i).getAlbumName().equalsIgnoreCase(album.getAlbumName()))
			{
				// remove the photo
				albums.remove(i);

				return;
			}
		}

		Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Album not found.");
        Optional<ButtonType> z = alert2.showAndWait();
        if (z.get() == ButtonType.OK) {
            alert2.close();
        }

	}
}
