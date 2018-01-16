package app;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.image.Image;

/**
 * This class represents a photo, which contains numerous properties
 * to store and identify an individual photo.
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 */
public class Photo implements Serializable {
	
	/**
	 * The actual photo stored in a file
	 */
	private transient Image photo;
	
	private String location;
	
	/**
	 * The name of the photo.
	 */
	private String photoName;
	
	/**
	 * The caption of the photo.
	 */
	private String caption;

	/**
	 * The file type of the photo.
	 */
	private String fileType; // .jpg, .png, etc.

	/**
	 * A list of tags associated with the photo.
	 */
	private ArrayList<Tag> tags;
	
	/**
	 * A list of albums this photo is in.
	 */
	private ArrayList<Album> albums;

	/**
	 * date and time for photo
	 */
	private Date date;

	/**
	 * The type of photo (stock photo or user photo)
	 */
	
	/**
	 * Creates a new instance of a photo.
	 */
	public Photo() {
	
		this.date = Calendar.getInstance().getTime();
		photo = null;
		this.tags = new ArrayList<Tag>();
	}
	
	/**
	 * Creates a new instance of a photo.
	 */
	public Photo(Image photo) {
	
		this.photo = photo;
		this.tags = new ArrayList<Tag>();
	}
	
	/**
	 * Returns the image
	 * 
	 * @return the image
	 */
	public Image getPhoto() {
		
		return this.photo;
	}
	
	/**
	 * Gets the location of the image
	 * 
	 * @return the location of the image
	 */
	public String getLocation() {
		
		return this.location;
	}
	
	/**
	 * Returns the name of the photo
	 * 
	 * @return the name of the photo
	 */
	public String getPhotoName() {
	
		return this.photoName;
	}
	
	/**
	 * Returns the photo's caption
	 * 
	 * @return the caption of the photo
	 */
	public String getCaption() {
		
		return this.caption;
	}
	
	/**
	 * Returns the type of file
	 * 
	 * @return the type of the file
	 */
	public String getFileType() {
		
		return this.fileType;
	}
	
	/**
	 * Returns a list of tags the photo is associated with
	 * 
	 * @return a list of tags the photo is associated with
	 */
	public ArrayList<Tag> getTags() {
		
		return this.tags;
	}
	
	/**
	 * Returns a list of albums that the photo is in
	 * 
	 * @return a list of albums that the photo is in
	 */
	public ArrayList<Album> getAlbums() {
		
		return this.albums;
	}
	
	/**
	 * Sets the photo
	 * 
	 * @param image the image to be associated with this photo
	 */
	public void setPhoto(Image image) {
		
		this.photo = image;
	}
	
	/**
	 *  Sets the location of the image
	 *  
	 *  @param location the location of the image
	 */
	public void setLocation(String location) {
		
		this.location = location;
	}
	
	/**
	 * Sets the name of the photo
	 * 
	 * @param photoName the new name of the photo
	 */
	public void setPhotoName(String photoName) {
		
		this.photoName = photoName;
	}
	
	/**
	 * Sets the caption of the photo
	 * 
	 * @param caption the new caption of the photo
	 */
	public void setCaption(String caption) {
		
		this.caption = caption;
	}
	
	/**
	 * Sets the file type of the photo
	 * 
	 * @param fileType the file type of the photo
	 */
	public void setFileType(String fileType) {
		
		this.fileType = fileType;
	}
	
	/**
	 * Sets the entire tags list for a photo
	 * 
	 * @param tags the tags list for a photo
	 */
	public void setTags(ArrayList<Tag> tags) {
		
		this.tags = tags;
	}
	
	/**
	 * Sets the entire albums list for a photo (list of albums that
	 * particular photo is in)
	 * 
	 * @param albums the list of albums the photo is in
	 */
	public void setAlbums(ArrayList<Album> albums) {
		
		this.albums = albums;
	}
	
	/**
	 * Adds a tag to the tags list
	 */
	public void addTag(String name, String value) {
		if (name==null || value == null) {
			return;
		}
		for (int i = 0; i < tags.size(); i++) {
			if (tags.get(i).getTagName().equalsIgnoreCase(name.toLowerCase()) && tags.get(i).getTagValue().equalsIgnoreCase(value.toLowerCase())) {
				return;
			}
		}
		tags.add(new Tag(name, value));
		return;
	}
	
	/**
	 * Adds an album to the albums list
	 */
	public void addAlbum() {

		// TODO: Implement add album
	}
	
	/**
	 * Removes a tag from the tags list
	 */
	public void removeTag(Tag tag) {
		if (tag.getTagName()==null || tag.getTagValue() == null) {
			return;
		}
		for (int i = 0; i < tags.size(); i++) {
			if (tags.get(i).getTagName().equalsIgnoreCase(tag.getTagName().toLowerCase()) && tags.get(i).getTagValue().equalsIgnoreCase(tag.getTagValue().toLowerCase())) {
				tags.remove(i);
				return;

			}
		}
	}
	
	/**
	 * Removes an album from the albums list
	 */
	public void removeAlbum() {

		// TODO: Implement remove album
	}

	/**
	 * Gets Date of last time picture was modified
	 *
	 * @return date last photo was modified
	 */
	public Date getDate() {
		return this.date;
	}
}
