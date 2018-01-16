package view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import app.Album;
import app.Photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class handles the open photo screen
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class OpenPhotoController implements Serializable {

	// Open photo controller fxml
	@FXML Label nameLabel;
	@FXML Label captionLabel;
	@FXML Label dateLabel;
	@FXML Label tagsListLabel;
	@FXML ImageView imageView;
	@FXML Button forwardButton;
	@FXML Button backwardButton;
	
	/**
	 * The album from which the photo is being displayed
	 */
	Album album = null;
	
	/**
	 * The current photo index in the album that is being displayed
	 */
	private int currentPhotoIndex = 0;
	
	/**
	 * Starts the controller
	 * 
	 * @param primaryStage the main stage being shown to the user
	 * @param photo the photo that is being displayed
	 */
	public void start(Stage primaryStage, Album album, int currentPhotoIndex) {
		
		this.album = album;
		this.currentPhotoIndex = currentPhotoIndex;
		populatePhoto();
		
		// ON STAGE CLOSE...
		primaryStage.setOnCloseRequest( event -> {} );
	}
	
	/**
	 * Populates the photo screen
	 */
	public void populatePhoto() {
		
		nameLabel.setText(album.getPhotos().get(currentPhotoIndex).getPhotoName());
		captionLabel.setText(album.getPhotos().get(currentPhotoIndex).getCaption());
		dateLabel.setText(album.getPhotos().get(currentPhotoIndex).getDate().toString());
		imageView.setImage(new Image(album.getPhotos().get(currentPhotoIndex).getLocation()));
		
		if(album.getPhotos().get(currentPhotoIndex).getTags().size() == 0) {
			
			tagsListLabel.setText("None");
		}
		else {
			
			String tags = "";
			
			for(int i = 0; i < album.getPhotos().get(currentPhotoIndex).getTags().size(); i++) {
				
				tags = tags.concat(album.getPhotos().get(currentPhotoIndex).getTags().get(i).getTag() + "\n");
			}
			
			tagsListLabel.setText(tags);
		}
	}
	
	/**
	 * Increments to the next photo
	 */
	public void nextPicture(ActionEvent e) {
		
		if(currentPhotoIndex + 1 != album.getPhotos().size()) {
			
			currentPhotoIndex++;
			populatePhoto();
		}
	}
	
	/**
	 * Decrements to the previous photo
	 * 
	 * @param e the event that invoked the method
	 */
	public void previousPicture(ActionEvent e) {
		
		if(currentPhotoIndex - 1 != -1) {
			
			currentPhotoIndex--;
			populatePhoto();
		}
	}
	
	

}
