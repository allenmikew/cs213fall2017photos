package view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import app.Album;
import app.Photo;
import app.Tag;
import app.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.SerializableController;

/**
 * This class handles the photo view screen
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 * 
 */
public class PhotoViewController implements Serializable{
	
	/**
	 * The primary stage that is shown to the user
	 */
	Stage primaryStage;
	
	// Photos screen FXML
	@FXML GridPane photoGrid;
	@FXML Pane pane00;
	@FXML Pane pane10;
	@FXML Pane pane01;
	@FXML Pane pane11;
	@FXML Pane pane02;
	@FXML Pane pane12;
	@FXML ImageView imageView00;
	@FXML ImageView imageView10;
	@FXML ImageView imageView01;
	@FXML ImageView imageView11;
	@FXML ImageView imageView02;
	@FXML ImageView imageView12;
	@FXML Label photoName00;
	@FXML Label photoName10;
	@FXML Label photoName01;
	@FXML Label photoName11;
	@FXML Label photoName02;
	@FXML Label photoName12;
	@FXML Label caption00;
	@FXML Label caption10;
	@FXML Label caption01;
	@FXML Label caption11;
	@FXML Label caption02;
	@FXML Label caption12;
	@FXML Label albumNameLabel;
	
	
	/**
	 * The current album being shown on screen
	 */
	Album currentAlbum;
	
	User currentUser;
	
	ArrayList<User> users;
	
	/**
	 * The current index of the base photo
	 */
	int	currentPhotoIndex = 0;
	
	int userIndex;
	
	/**
	 * Stars the photo view controller
	 * 
	 * @param primaryStage the main stage being shown to the user
	 * @param album the current album that is being shown
	 * @throws Exception
	 */
	public void start(Stage primaryStage, int albumIndex, int userIndex) throws Exception {
		
		this.userIndex = userIndex;
		users = SerializableController.read();
		currentAlbum = users.get(userIndex).getAlbums().get(albumIndex);
		
		
		this.primaryStage = primaryStage;
		this.currentUser = users.get(userIndex);
	
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    	
    	AnchorPane root = (AnchorPane)loader.load();

    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Photos.java");
    	primaryStage.setResizable(false);
    	primaryStage.show();
	
    	resetScreen();
		populateImages();
		
		// Pane event handlers
		pane00.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 1) {
					
					pane00.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
					
					pane10.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane01.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane11.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane02.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane12.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				}
				else if(event.getClickCount() == 2) {
					
					if(currentPhotoIndex < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
				        
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            OpenPhotoController openphotoview = loader.getController();
				            Stage stage = new Stage();
				            
				            openphotoview.start(stage, currentAlbum, currentPhotoIndex);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (Exception exception) {
				            exception.printStackTrace();
				        }
					}
				}
			
			    event.consume();
			}
		});
		
		pane10.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 1) {
					
					pane00.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane10.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
					
					pane01.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane11.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane02.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane12.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
				}
				else if(event.getClickCount() == 2) {
					
					if(currentPhotoIndex + 1 < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
				        
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            OpenPhotoController openphotoview = loader.getController();
				            Stage stage = new Stage();
				            
				            openphotoview.start(stage, currentAlbum, currentPhotoIndex + 1);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (Exception exception) {
				            exception.printStackTrace();
				        }
					}
				}
			
			    event.consume();
			}
		});
		
		pane01.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 1) {
					
					pane00.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane10.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane01.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
					
					pane11.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane02.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane12.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				}
				else if(event.getClickCount() == 2) {
					
					if(currentPhotoIndex + 2 < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
				        
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            OpenPhotoController openphotoview = loader.getController();
				            Stage stage = new Stage();
				            
				            openphotoview.start(stage, currentAlbum, currentPhotoIndex + 2);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (Exception exception) {
				            exception.printStackTrace();
				        }
					}
				}
			
			    event.consume();
			}
		});
		
		pane11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 1) {
					
					pane00.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane10.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane01.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane11.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
					
					pane02.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane12.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				}
				else if(event.getClickCount() == 2) {
					
					if(currentPhotoIndex + 3 < currentAlbum.getPhotos().size()) {
						
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
				        
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            OpenPhotoController openphotoview = loader.getController();
				            Stage stage = new Stage();
				            
				            openphotoview.start(stage, currentAlbum, currentPhotoIndex + 3);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (Exception exception) {
				            exception.printStackTrace();
				        }
					}
				}
			
			    event.consume();
			}
		});
		
		pane02.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 1) {
					
					pane00.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane10.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane01.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane11.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane02.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
					
					pane12.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				}
				else if(event.getClickCount() == 2) {
					
					if(currentPhotoIndex + 4 < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
				        
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            OpenPhotoController openphotoview = loader.getController();
				            Stage stage = new Stage();
				            
				            openphotoview.start(stage, currentAlbum, currentPhotoIndex + 4);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (Exception exception) {
				            exception.printStackTrace();
				        }
					}
				}
			
			    event.consume();
			}
		});
		
		pane12.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 1) {
					
					pane00.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				
					pane10.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				
					pane01.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane11.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane02.setBorder(new Border(new BorderStroke(Color.BLACK, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
					
					pane12.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, 
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
				}
				else if(event.getClickCount() == 2) {
					
					if(currentPhotoIndex + 5 < currentAlbum.getPhotos().size()) {
						
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));
				        
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            OpenPhotoController openphotoview = loader.getController();
				            Stage stage = new Stage();
				            
				            openphotoview.start(stage, currentAlbum, currentPhotoIndex + 5);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (Exception exception) {
				            exception.printStackTrace();
				        }
					}
				}
			
			    event.consume();
		
		}});
		
		// ON STAGE CLOSE...
		primaryStage.setOnCloseRequest( event -> {
			
			
			try {
				SerializableController.save(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		} );
	}
	
	/**
	 * Transitions between two screens
	 */
	public void screenTransition() {
		
		primaryStage.close();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AlbumsScreen.fxml"));
        
        try {
            AnchorPane root = (AnchorPane)loader.load();
            AlbumController albumview = loader.getController();
            Stage stage = new Stage();
            
            albumview.start(stage, userIndex);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
		
	}
	
	/**
	 * Moves to the next page of photos
	 */
	public void increaseCurrentPhotoIndex() {
		
		if(currentAlbum.getPhotos().size() - currentPhotoIndex > 5) {
			currentPhotoIndex += 6;
		}
		
		resetScreen();
		populateImages();
	}
	
	/**
	 * Moves to the previous page of photos
	 */
	public void decreaseCurrentPhotoIndex() {
		
		if(currentPhotoIndex - 6 >= 0) {
			
			currentPhotoIndex -= 6;
		}
		
		resetScreen();
		populateImages();
	}
	
	/**
	 * Resets the screen to a blank screen
	 */
	public void resetScreen() {
		
		imageView00.setImage(null);
    	imageView10.setImage(null);
    	imageView01.setImage(null);
    	imageView11.setImage(null);
    	imageView02.setImage(null);
    	imageView12.setImage(null);;
    	photoName00.setText("");
    	photoName10.setText("");
    	photoName01.setText("");
    	photoName11.setText("");
    	photoName02.setText("");
    	photoName12.setText("");
    	caption00.setText("");
    	caption10.setText("");
    	caption01.setText("");
    	caption11.setText("");
    	caption02.setText("");
    	caption12.setText("");
	}
	
	/**
	 * Populates the screen with images, captions, etc.
	 */
	public void populateImages() {
		
		albumNameLabel.setText(currentAlbum.getAlbumName());
		
		if(currentAlbum.getPhotos() == null || currentAlbum.getPhotos().size() == 0) {
			
		}
		else
		{
			
			if(currentAlbum.getPhotos().size() - currentPhotoIndex > 5) {
				
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getCaption());
				
				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getCaption());
				
				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getCaption());
				
				imageView11.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getLocation()));
				photoName11.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getPhotoName());
				caption11.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getCaption());
				
				imageView02.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 4).getLocation()));
				photoName02.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 4).getPhotoName());
				caption02.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 4).getCaption());
				
				imageView12.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 5).getLocation()));
				photoName12.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 5).getPhotoName());
				caption12.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 5).getCaption());
			}
			else if(currentAlbum.getPhotos().size() - currentPhotoIndex > 4) {
				
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getCaption());
				
				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getCaption());
				
				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getCaption());
				
				imageView11.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getLocation()));
				photoName11.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getPhotoName());
				caption11.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getCaption());
				
				imageView02.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 4).getLocation()));
				photoName02.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 4).getPhotoName());
				caption02.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 4).getCaption());
				
			}
			else if(currentAlbum.getPhotos().size() - currentPhotoIndex > 3) {
				
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getCaption());
				
				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getCaption());
				
				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getCaption());
				
				imageView11.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getLocation()));
				photoName11.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getPhotoName());
				caption11.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 3).getCaption());
				
			}
			else if(currentAlbum.getPhotos().size() - currentPhotoIndex > 2) {
				
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getCaption());
				
				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getCaption());
				
				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 2).getCaption());
				
			}
			else if(currentAlbum.getPhotos().size() - currentPhotoIndex > 1) {
				
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getCaption());
				
				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currentPhotoIndex + 1).getCaption());
				
			}
			else if(currentAlbum.getPhotos().size() - currentPhotoIndex > 0) {
				
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currentPhotoIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currentPhotoIndex).getCaption());
			}
		}
	}
	
	/**
	 * Adds an external photo to the album
	 * 
	 * @param e the event that invoked the method call
	 */
	public void addPhotoToAlbum(ActionEvent e) {
		 
		final FileChooser fileChooser = new FileChooser();

		File file = fileChooser.showOpenDialog(primaryStage);
	        
    	if (file != null) {
           
    		Image image1 = new Image(file.toURI().toString());
            
    		Photo newPhoto = new Photo();
    		newPhoto.setPhoto(image1);
    		newPhoto.setLocation(file.toURI().toString());
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Add Photo");
            alert.setHeaderText("Adding a photo");
            alert.setResizable(true);
            Label label1 = new Label("Photo Name: ");

            TextField text1 = new TextField();

            GridPane grid = new GridPane();
            grid.add(label1, 1, 1);
            grid.add(text1, 2, 1);
            alert.getDialogPane().setContent(grid);

            Optional<ButtonType> x = alert.showAndWait();

            if (x.get() == ButtonType.OK) {
                if (text1.getText().isEmpty()) {
                    Alert alert2 = new Alert(AlertType.ERROR);
                    alert2.setTitle("You must enter an photo name");
                    Optional<ButtonType> z = alert2.showAndWait();
                    if (z.get() == ButtonType.OK) {
                        alert2.close();
                    }
                } else {
                    newPhoto.setPhotoName(text1.getText());
                    
                    alert.close();

                }
            }
    		
    		currentAlbum.addPhoto(newPhoto);
    		
    		resetScreen();
    		populateImages();
       
        }
    	
    	try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} 
	
	/**
	 * Removes the photo from the album
	 * 
	 * @param e the event that invoked the method call
	 */
	public void removePhotoFromAlbum(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
        int indexToRemove = -1;
        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentPhotoIndex;
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentPhotoIndex + 1;
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentPhotoIndex + 2;
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentPhotoIndex + 3;
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentPhotoIndex + 4;
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentPhotoIndex + 5;
        }
        else {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        }

        alert.setTitle("Photo Deletion");
        alert.setHeaderText("Are you sure you want to delete this photo?");
        alert.setResizable(true);

        Optional<ButtonType> x = alert.showAndWait();

        if (x.get() == ButtonType.OK) {
        	
            currentAlbum.getPhotos().remove(indexToRemove);
        }
        else{
        	
            alert.close();
        }
        
        resetScreen();
        populateImages();
        
        try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Edits the photo within an album
	 * 
	 * @param e the event that invoked the method call
	 */
	public void editName(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
	        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex);
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 1);
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 2);
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 3);
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 4);
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 5);
        }
        else
        {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        
        }
	        
	    	
	    alert.setTitle("New Photo Name");
	    alert.setHeaderText("Change the photo name");
	    alert.setResizable(true);
	    Label label1 = new Label("Photo Name");

	    TextField text1 = new TextField();
	    text1.setPromptText(photo.getPhotoName());

	    GridPane grid = new GridPane();
	    grid.add(label1, 1, 1);
	    grid.add(text1, 2, 1);
	  
	    alert.getDialogPane().setContent(grid);

	    Optional<ButtonType> x = alert.showAndWait();

	    if (x.get() == ButtonType.OK) {
	    	
	    	for(int i = 0; i < currentAlbum.getPhotos().size(); i++) {
	    		
	    		if(text1.getText().equals(currentAlbum.getPhotos().get(i).getPhotoName())) {
	    			
	    			Alert alert2 = new Alert(AlertType.ERROR);
	    			alert2.setContentText("There is already a photo with that name.");
	    			
	    			Optional<ButtonType> y = alert2.showAndWait();
	    			
	    			if(y.get() == ButtonType.OK)
	    			{
	    				alert2.close();
	    				return;
	    			}
	    			
	    		}
	    	}
	    	photo.setPhotoName(text1.getText());
	    }
	    else {
	            
	    }
	       
	    resetScreen();
	    populateImages();
	    
	    try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Edits the caption of a photo
	 * 
	 * @param e the event that triggered the method call
	 */
	public void editCaption(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
	        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex);
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 1);
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 2);
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 3);
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 4);
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 5);
        }
        else
        {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        
        }
	        
	    	
	    alert.setTitle("New Photo Caption");
	    alert.setHeaderText("Change the photo caption");
	    alert.setResizable(true);
	    Label label1 = new Label("Photo Name");

	    TextField text1 = new TextField();
	    text1.setPromptText(photo.getCaption());

	    GridPane grid = new GridPane();
	    grid.add(label1, 1, 1);
	    grid.add(text1, 2, 1);
	  
	    alert.getDialogPane().setContent(grid);

	    Optional<ButtonType> x = alert.showAndWait();

	    if (x.get() == ButtonType.OK) {
	    	
	    	photo.setCaption(text1.getText());
	    }
	    else {
	            
	    }
	       
	    resetScreen();
	    populateImages();
	
	    try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Adds a tag to a photo
	 * 
	 * @param e the action that invoked the method call
	 */
	public void addTag(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
	        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex);
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 1);
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 2);
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 3);
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 4);
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 5);
        }
        else {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        
        }
	        
	    	
	    alert.setTitle("New Photo Tag");
	    alert.setHeaderText("Add a new photo tag");
	    alert.setResizable(true);
	    Label label1 = new Label("Tag Name: ");
	    Label label2 = new Label("Tag Value: ");

	    TextField text1 = new TextField();
	    TextField text2 = new TextField();

	    GridPane grid = new GridPane();
	    grid.add(label1, 1, 1);
	    grid.add(text1, 2, 1);
	    grid.add(label2, 1, 2);
	    grid.add(text2, 2, 2);
	  
	    alert.getDialogPane().setContent(grid);

	    Optional<ButtonType> x = alert.showAndWait();

	    if (x.get() == ButtonType.OK) {
	    	
	    	for(int i = 0; i < photo.getTags().size(); i++) {
	    		
	    		if(photo.getTags().get(i).getTagName().equals(text1.getText())
	    				&& photo.getTags().get(i).getTagValue().equals(text2.getText())) {
	    			
	    			Alert alert2 = new Alert(AlertType.ERROR);
	    			alert2.setContentText("There is already a tag with that name and value.");
	    			
	    			Optional<ButtonType> y = alert2.showAndWait();
	    			
	    			if(y.get() == ButtonType.OK)
	    			{
	    				alert2.close();
	    				return;
	    			}
	    		}
	    	}
	    	
	    	photo.addTag(text1.getText(), text2.getText());
	    }
	    else {
	            
	    }
	       
	    resetScreen();
	    populateImages();
	    
	    try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Removes a tag from the tags list of a photo
	 * 
	 * @param e the action that triggered the method call
	 */
	public void removeTag(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
	        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex);
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 1);
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 2);
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 3);
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 4);
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 5);
        }
        else {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        }
	        
	    	
	    alert.setTitle("Remove Photo Tag");
	    alert.setHeaderText("Remove a photo tag");
	    alert.setResizable(true);
	    Label label1 = new Label("Tag Name: ");
	    Label label2 = new Label("Tag Value: ");

	    TextField text1 = new TextField();
	    TextField text2 = new TextField();

	    GridPane grid = new GridPane();
	    grid.add(label1, 1, 1);
	    grid.add(text1, 2, 1);
	    grid.add(label2, 1, 2);
	    grid.add(text2, 2, 2);
	  
	    alert.getDialogPane().setContent(grid);

	    Optional<ButtonType> x = alert.showAndWait();

	    if (x.get() == ButtonType.OK) {
	    	
	    	for(int i = 0; i < photo.getTags().size(); i++) {
	    		
	    		if(photo.getTags().get(i).getTagName().equals(text1.getText())
	    				&& photo.getTags().get(i).getTagValue().equals(text2.getText())) {
	    			
	    			photo.removeTag(new Tag(text1.getText(), text2.getText()));
	    			return;
	    		}
	    	}
	    	
	    	Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setContentText("There is no tag with that name and value.");
			
			Optional<ButtonType> y = alert2.showAndWait();
			
			if(y.get() == ButtonType.OK)
			{
				alert2.close();
				return;
			}
	    }
	    else {
	            
	    }
	       
	    resetScreen();
	    populateImages();
	    
	    try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Copies the photo to a new album
	 */
	public void copyPhoto() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
		Album album = null;
	        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex);
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 1);
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 2);
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 3);
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 4);
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 5);
        }
        else {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        }
	        
	    	
	    alert.setTitle("Copy Photo");
	    alert.setHeaderText("Copy photo to which album?");
	    alert.setResizable(true);
	    Label label1 = new Label("Album Name: ");

	    TextField text1 = new TextField();
	    

	    GridPane grid = new GridPane();
	    grid.add(label1, 1, 1);
	    grid.add(text1, 2, 1);
	 
	    alert.getDialogPane().setContent(grid);

	    Optional<ButtonType> x = alert.showAndWait();

	    if (x.get() == ButtonType.OK) {
	    	
	    	String temp = text1.getText();
	    	
	    	for(int i = 0; i < currentUser.getAlbums().size(); i++) {
	    		
	    		if(currentUser.getAlbums().get(i).getAlbumName().equalsIgnoreCase(temp)) {
	    			
	    			album = currentUser.getAlbums().get(i);
	    			
	    		}
	    	}
	    	
	    	if(album == null) {
	    		Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("You must enter a valid Album name");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
	    	}
	    	else
	    	{
	    		
	    		Photo newPhoto = null;
	    		// check to see if there's already a photo with it's name
	    		for(int i = 0; i < album.getPhotos().size(); i++) {
	    			
	    			if(album.getPhotos().get(i).getPhotoName().equalsIgnoreCase(photo.getPhotoName())) {
	    				newPhoto = new Photo();
	    				newPhoto.setPhotoName(photo.getPhotoName() + "0");
	    				newPhoto.setCaption(photo.getCaption());
	    				newPhoto.setPhoto(photo.getPhoto());
	    				newPhoto.setTags(photo.getTags());
	    				album.addPhoto(newPhoto);
	    				
	    			}
	    		}
	    		
	    		if(newPhoto == null) {
	    			album.addPhoto(photo);
	    		}
	    		
	    	}
	    	
	    	
	    }
	       
	    resetScreen();
	    populateImages();
	    
	    try {
			SerializableController.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Moves the photo from the current album to a new album
	 */
	public void movePhoto() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
		Album album = null;
	        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex);
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 1);
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 2);
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 3);
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 4);
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	
        	photo = currentAlbum.getPhotos().get(currentPhotoIndex + 5);
        }
        else {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify a photo.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        }
	        
	    	
	    alert.setTitle("Move Photo");
	    alert.setHeaderText("Move photo to which album?");
	    alert.setResizable(true);
	    Label label1 = new Label("Album Name: ");

	    TextField text1 = new TextField();
	    

	    GridPane grid = new GridPane();
	    grid.add(label1, 1, 1);
	    grid.add(text1, 2, 1);
	 
	    alert.getDialogPane().setContent(grid);

	    Optional<ButtonType> x = alert.showAndWait();

	    if (x.get() == ButtonType.OK) {
	    	
	    	String temp = text1.getText();
	    	
	    	for(int i = 0; i < currentUser.getAlbums().size(); i++) {
	    		
	    		if(currentUser.getAlbums().get(i).getAlbumName().equalsIgnoreCase(temp) &&
	    				!(currentUser.getAlbums().get(i).getAlbumName().equals(currentAlbum.getAlbumName()))) {
	    			
	    			album = currentUser.getAlbums().get(i);
	    			
	    		}
	    	}
	    	
	    	if(album == null) {
	    		Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("You must enter a valid Album name, or a different album than the one currently selected.");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
	    	}
	    	else
	    	{
	    		
	    		Photo newPhoto = null;
	    		// check to see if there's already a photo with it's name
	    		for(int i = 0; i < album.getPhotos().size(); i++) {
	    			
	    			if(album.getPhotos().get(i).getPhotoName().equalsIgnoreCase(photo.getPhotoName())) {
	    				newPhoto = new Photo();
	    				newPhoto.setPhotoName(photo.getPhotoName() + "0");
	    				newPhoto.setCaption(photo.getCaption());
	    				newPhoto.setPhoto(photo.getPhoto());
	    				newPhoto.setTags(photo.getTags());
	    				album.addPhoto(newPhoto);
	    				currentAlbum.removePhoto(photo.getPhotoName());
	    				
	    			}
	    		}
	    		
	    		if(newPhoto == null) {
	    			album.addPhoto(photo);
	    			currentAlbum.removePhoto(photo.getPhotoName());
	    		}
	    		
	    	}
	    	
	    	
	    }
	       
	    resetScreen();
	    populateImages();
	    
	    try {
			SerializableController.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the user out
	 * 
	 * @param e the event that triggers the method
	 */
	public void logout(ActionEvent e) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Are you sure you want to logout?");

		Optional<ButtonType> x = alert.showAndWait();

		if (x.get() == ButtonType.OK) {
			
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginScreen.fxml"));
			try {
				AnchorPane root = (AnchorPane)loader.load();
				LoginViewController loginview = loader.getController();
				Stage stage = new Stage();
				SerializableController.save(users);
				loginview.start(stage);
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}
	}

}
