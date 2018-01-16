package app;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Represents an album in our project. An instance of an album contains a list of all the
 * photos in that album, and stores properties about that album.
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class Album implements Serializable {

	/**
	 * The name of the album.
	 */
	private String albumName;
	
	/**
	 * A list of all the photos in the album.
	 */
	private ArrayList<Photo> photos;
	
	/**
	 * Creates a new instance of an album.
	 */
	public Album() {
		
		this.photos = new ArrayList<Photo>();
		this.albumName = albumName;
	}
	
	/**
	 * Returns the name of the album
	 * 
	 * @return the name of the album
	 */
	public String getAlbumName() {
		
		return this.albumName;
	}
	
	/**
	 * Returns the list of photos in the album
	 * 
	 * @return the list of photos in the album
	 */
	public ArrayList<Photo> getPhotos() {
		
		return this.photos;
	}
	
	/**
	 * Sets the name of album to a new album name.
	 * 
	 * @param albumName the new name of the album
	 */
	public void setAlbumName(String albumName) {
		
		this.albumName = albumName;
	}

	/**
	 * Returns the size of the album
	 * 
	 * @return the size of the album
	 */
	public int getAlbumSize() {

		return photos.size();
	}

	/**
	 * Renames the album
	 * 
	 * @param x the new name of the album
	 */
	public void renameAlbum(String x) {

		this.albumName = x;

	}
	
	/**
	 * Sets the entire photos list to a new list
	 * 
	 * @param photos the list of photos in the album
	 */
	public void setPhotos(ArrayList<Photo> photos) {
		
		this.photos = photos;
	}
	
	/**
	 * Adds a photo to the list of photos in the album
	 * 
	 * @param photo the new photo to be added
	 */
	public void addPhoto(Photo photo) {
		
		if(photos == null) {
			
			photos = new ArrayList<Photo>();
		}
		
		// Scan through the photos in the album
		for(int i = 0; i < photos.size(); i++) {
			
			// Check to see if there are any other photos with the same name
			if(photos.get(i).getPhotoName().equalsIgnoreCase(photo.getPhotoName())) {
				
				Alert alert2 = new Alert(AlertType.ERROR);
	            alert2.setTitle("There is already a album with this name.");
	            Optional<ButtonType> z = alert2.showAndWait();
	            if (z.get() == ButtonType.OK) {
	                alert2.close();
	            }
				
				return;
			}
		}
		
		// If there are no photos of the same name, add the photo to the album
		photos.add(photo);
	}
	
	/**
	 * Removes a photo with a given photoName
	 * 
	 * @param photoName the name of the photo to be removed
	 */
	public void removePhoto(String photoName) {
		
		if(photos.size() == 0) {
			
			Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("No photos in this album.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
		}
		
		for(int i = 0; i < photos.size(); i++) {
			
			// if the photo name matches the photo that they want to remove
			// TODO: Photos can be of the same type, but have the same name. Add check for that.
			if(photos.get(i).getPhotoName().equalsIgnoreCase(photoName))
			{
				// remove the photo
				photos.remove(i);
				
				return;
			}
		}
		
		Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Photo not found.");
        Optional<ButtonType> z = alert2.showAndWait();
        if (z.get() == ButtonType.OK) {
            alert2.close();
        }
	}

	/**
	 * Sorts the albums by their date
	 */
	public void sortAlbumByDate() {
		if (this.getAlbumSize() == 0 || this.getAlbumSize() == 1) {
			return;
		}
		Collections.sort(photos, new Comparator<Photo>() {
			@Override
			public int compare(Photo p1, Photo p2) {
				return p1.getDate().compareTo(p2.getDate());
			}
		});
	}
}
