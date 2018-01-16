package app;

import java.io.Serializable;

/**
 * The Tag class is used to make tag objects, which are used to sort pictures
 * and/or albums.
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class Tag implements Serializable {

	/**
	 * The name of the tag.
	 */
	private String tagName;
	
	/**
	 * The value of the tag.
	 */
	private String tagValue;
	
	/**
	 * Creates a new instance of a tag object, used for tagging pictures and albums.
	 * 
	 * @param tagName the name or category of the tag
	 * @param tagValue the actual tag value
	 */
	public Tag(String tagName, String tagValue) {
		
		this.tagName = tagName;
		this.tagValue = tagValue;
	}
	
	public String getTag() {
		
		return "(" + this.tagName + ", " + this.tagValue + ")";
	}
	
	/**
	 * Returns the name of the tag.
	 * 
	 * @return the name of the tag
	 */
	public String getTagName() {
		
		return this.tagName;
	}
	
	/**
	 * Returns the value of the tag
	 * 
	 * @return the value of the tag
	 */
	public String getTagValue() {
		
		return this.tagValue;
	}
	
	/**
	 * Sets the name of the tag.
	 * 
	 * @param tagName the new name of the tag
	 */
	public void setTagName(String tagName) {
		
		this.tagName = tagName;
	}
	
	/**
	 * Sets the value of the tag.
	 * 
	 * @param tagValue the new value of the tag
	 */
	public void setTagValue(String tagValue) {
		
		this.tagValue = tagValue;
	}
}
