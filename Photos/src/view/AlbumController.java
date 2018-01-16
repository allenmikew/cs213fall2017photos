package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.SerializableController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import app.*;

/**
 * This class is responsible for maintaining and handling the behaviors of of the Album View screen.
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class AlbumController implements Serializable {

	/**
	 * List of albums
	 */
	ArrayList<Album> albums = new ArrayList<Album>();
	
	/**
	 * Master list of all photos
	 */
	ArrayList<Photo> allPhotos = new ArrayList<Photo>();
	
	
	
	/**
	 * Current user
	 */
	private User user;
	
	ArrayList<User> users;
	
	@FXML
    ListView<Album> album;
    
    // Album screen FXML
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
	@FXML Label albumName00;
	@FXML Label albumName10;
	@FXML Label albumName01;
	@FXML Label albumName11;
	@FXML Label albumName02;
	@FXML Label albumName12;
	@FXML Label caption00;
	@FXML Label caption10;
	@FXML Label caption01;
	@FXML Label caption11;
	@FXML Label caption02;
	@FXML Label caption12;
	@FXML Label usernameLabel;
	
	/**
	 * Keeps track of the base album to display
	 */
	int	currentAlbumIndex = 0;

	Stage primaryStage;
	int userIndex;
	
	/**
	 * Starts the album controller
	 * 
	 * @param primaryStage the main stage shown to the user
	 * @param user the current user
	 * @throws Exception
	 */
	public void start(Stage primaryStage, int userIndex) throws Exception {
		
		users = SerializableController.read();
		
		user = users.get(userIndex);
		this.userIndex = userIndex;
		
		/*
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/AlbumsScreen.fxml"));
    	
    	AnchorPane root = (AnchorPane)loader.load();

    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Photos.java");
    	primaryStage.setResizable(false);
    	primaryStage.show();
		*/
		
		this.primaryStage = primaryStage;
    	resetScreen();
    	
		albums = user.getAlbums();
		
		usernameLabel.setText(user.getUsername());
		
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
					
					if(currentAlbumIndex < albums.size()) {
					
						primaryStage.close();
    					FXMLLoader loader = new FXMLLoader();
    			        loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    			        
    			        try {
    			            AnchorPane root = (AnchorPane)loader.load();
    			            PhotoViewController photoview = loader.getController();
    			            Stage stage = new Stage();
    			            SerializableController.save(users);
    			            photoview.start(stage, currentAlbumIndex, userIndex);
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
					
					if(currentAlbumIndex + 1 < albums.size()) {
    					
						primaryStage.close();
    					FXMLLoader loader = new FXMLLoader();
    			        loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    			        
    			        try {
    			            AnchorPane root = (AnchorPane)loader.load();
    			            PhotoViewController photoview = loader.getController();
    			            Stage stage = new Stage();
    			            SerializableController.save(users);
    			            photoview.start(stage, currentAlbumIndex + 1, userIndex);
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
					
					if(currentAlbumIndex + 2 < albums.size()) {
    					
						primaryStage.close();
    					FXMLLoader loader = new FXMLLoader();
    			        loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    			        
    			        try {
    			            AnchorPane root = (AnchorPane)loader.load();
    			            PhotoViewController photoview = loader.getController();
    			            Stage stage = new Stage();
    			            SerializableController.save(users);
    			            photoview.start(stage, currentAlbumIndex + 2, userIndex);
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
					
					if(currentAlbumIndex + 3 < albums.size()) {
    					
						primaryStage.close();
    					FXMLLoader loader = new FXMLLoader();
    			        loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    			        
    			        try {
    			            AnchorPane root = (AnchorPane)loader.load();
    			            PhotoViewController photoview = loader.getController();
    			            Stage stage = new Stage();
    			            SerializableController.save(users);
    			            photoview.start(stage, currentAlbumIndex + 3, userIndex);
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
					
					if(currentAlbumIndex + 4 < albums.size()) {
    					
						primaryStage.close();
    					FXMLLoader loader = new FXMLLoader();
    			        loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    			        
    			        try {
    			            AnchorPane root = (AnchorPane)loader.load();
    			            PhotoViewController photoview = loader.getController();
    			            Stage stage = new Stage();
    			            SerializableController.save(users);
    			            photoview.start(stage, currentAlbumIndex + 4, userIndex);
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
					
					if(currentAlbumIndex + 5 < albums.size()) {
    					
						primaryStage.close();
    					FXMLLoader loader = new FXMLLoader();
    			        loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));
    			        
    			        try {
    			            AnchorPane root = (AnchorPane)loader.load();
    			            PhotoViewController photoview = loader.getController();
    			            Stage stage = new Stage();
    			            SerializableController.save(users);
    			            photoview.start(stage, currentAlbumIndex + 5, userIndex);
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

		
		// ON STAGE CLOSE
		primaryStage.setOnCloseRequest( event -> {
			
			
			
			try {
				SerializableController.save(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screenTransition();
			
		
		} );
	}
	
	public void screenTransition() {
		
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

        } catch (Exception exception) {
            exception.printStackTrace();
        }
	}
	
	/**
	 * Responsible for populating the images and setting up the names and captions of the albums
	 */
	public void populateImages() {
		
		if(albums == null || albums.size() == 0) {
			
		}
		else
		{
			if(albums.size() - currentAlbumIndex > 5) {
				
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0 && albums.get(currentAlbumIndex).getPhotos().get(0).getLocation() != null) {
					
					imageView00.setImage(new Image(albums.get(currentAlbumIndex).getPhotos().get(0).getLocation()));
				}
				albumName00.setText(albums.get(currentAlbumIndex).getAlbumName());
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0) {
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex).getPhotos().get(albums.get(currentAlbumIndex).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation() != null) {
					
					imageView10.setImage(new Image(albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation()));
				}
				albumName10.setText(albums.get(currentAlbumIndex + 1).getAlbumName());
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0) {
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(albums.get(currentAlbumIndex + 1).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
			
			
				
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation() != null) {
					
					imageView01.setImage(new Image(albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation()));
				}
				albumName01.setText(albums.get(currentAlbumIndex + 2).getAlbumName());
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0) {
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(albums.get(currentAlbumIndex + 2).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 3).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 3).getPhotos().get(0).getLocation() != null) {
					
					imageView11.setImage(new Image(albums.get(currentAlbumIndex + 3).getPhotos().get(0).getLocation()));
				}
				albumName11.setText(albums.get(currentAlbumIndex + 3).getAlbumName());
				if(albums.get(currentAlbumIndex + 3).getPhotos().size() > 0) {
					caption11.setText(albums.get(currentAlbumIndex + 3).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 3).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 3).getPhotos().get(albums.get(currentAlbumIndex + 3).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption11.setText(albums.get(currentAlbumIndex + 3).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
			
				
				
				
				if(albums.get(currentAlbumIndex + 4).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 4).getPhotos().get(0).getLocation() != null) {
					
					imageView02.setImage(new Image(albums.get(currentAlbumIndex + 4).getPhotos().get(0).getLocation()));
				}
				albumName02.setText(albums.get(currentAlbumIndex + 4).getAlbumName());
				if(albums.get(currentAlbumIndex + 4).getPhotos().size() > 0) {
					caption02.setText(albums.get(currentAlbumIndex + 4).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 4).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 4).getPhotos().get(albums.get(currentAlbumIndex + 4).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption02.setText(albums.get(currentAlbumIndex + 4).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 5).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 5).getPhotos().get(0).getLocation() != null) {
					
					imageView12.setImage(new Image(albums.get(currentAlbumIndex + 5).getPhotos().get(0).getLocation()));
				}
				albumName12.setText(albums.get(currentAlbumIndex + 5).getAlbumName());
				if(albums.get(currentAlbumIndex + 5).getPhotos().size() > 0) {
					caption12.setText(albums.get(currentAlbumIndex + 5).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 5).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 5).getPhotos().get(albums.get(currentAlbumIndex + 5).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption12.setText(albums.get(currentAlbumIndex + 5).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
			}
			else if(albums.size() - currentAlbumIndex > 4) {
				
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0 && albums.get(currentAlbumIndex).getPhotos().get(0).getLocation() != null) {
					
					imageView00.setImage(new Image(albums.get(currentAlbumIndex).getPhotos().get(0).getLocation()));
				}
				albumName00.setText(albums.get(currentAlbumIndex).getAlbumName());
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0) {
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex).getPhotos().get(albums.get(currentAlbumIndex).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation() != null) {
					
					imageView10.setImage(new Image(albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation()));
				}
				albumName10.setText(albums.get(currentAlbumIndex + 1).getAlbumName());
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0) {
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(albums.get(currentAlbumIndex + 1).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
			
			
				
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation() != null) {
					
					imageView01.setImage(new Image(albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation()));
				}
				albumName01.setText(albums.get(currentAlbumIndex + 2).getAlbumName());
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0) {
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(albums.get(currentAlbumIndex + 2).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 3).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 3).getPhotos().get(0).getLocation() != null) {
					
					imageView11.setImage(new Image(albums.get(currentAlbumIndex + 3).getPhotos().get(0).getLocation()));
				}
				albumName11.setText(albums.get(currentAlbumIndex + 3).getAlbumName());
				if(albums.get(currentAlbumIndex + 3).getPhotos().size() > 0) {
					caption11.setText(albums.get(currentAlbumIndex + 3).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 3).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 3).getPhotos().get(albums.get(currentAlbumIndex + 3).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption11.setText(albums.get(currentAlbumIndex + 3).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
			
				
				
				
				if(albums.get(currentAlbumIndex + 4).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 4).getPhotos().get(0).getLocation() != null) {
					
					imageView02.setImage(new Image(albums.get(currentAlbumIndex + 4).getPhotos().get(0).getLocation()));
				}
				albumName02.setText(albums.get(currentAlbumIndex + 4).getAlbumName());
				if(albums.get(currentAlbumIndex + 4).getPhotos().size() > 0) {
					caption02.setText(albums.get(currentAlbumIndex + 4).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 4).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 4).getPhotos().get(albums.get(currentAlbumIndex + 4).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption02.setText(albums.get(currentAlbumIndex + 4).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
			}
			else if(albums.size() - currentAlbumIndex > 3) {
				
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0 && albums.get(currentAlbumIndex).getPhotos().get(0).getLocation() != null) {
					
					imageView00.setImage(new Image(albums.get(currentAlbumIndex).getPhotos().get(0).getLocation()));
				}
				albumName00.setText(albums.get(currentAlbumIndex).getAlbumName());
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0) {
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex).getPhotos().get(albums.get(currentAlbumIndex).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation() != null) {
					
					imageView10.setImage(new Image(albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation()));
				}
				albumName10.setText(albums.get(currentAlbumIndex + 1).getAlbumName());
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0) {
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(albums.get(currentAlbumIndex + 1).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
			
			
				
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation() != null) {
					
					imageView01.setImage(new Image(albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation()));
				}
				albumName01.setText(albums.get(currentAlbumIndex + 2).getAlbumName());
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0) {
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(albums.get(currentAlbumIndex + 2).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 3).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 3).getPhotos().get(0).getLocation() != null) {
					
					imageView11.setImage(new Image(albums.get(currentAlbumIndex + 3).getPhotos().get(0).getLocation()));
				}
				albumName11.setText(albums.get(currentAlbumIndex + 3).getAlbumName());
				if(albums.get(currentAlbumIndex + 3).getPhotos().size() > 0) {
					caption11.setText(albums.get(currentAlbumIndex + 3).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 3).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 3).getPhotos().get(albums.get(currentAlbumIndex + 3).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption11.setText(albums.get(currentAlbumIndex + 3).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
			}
			else if(albums.size() - currentAlbumIndex > 2) {
			
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0 && albums.get(currentAlbumIndex).getPhotos().get(0).getLocation() != null) {
					
					imageView00.setImage(new Image(albums.get(currentAlbumIndex).getPhotos().get(0).getLocation()));
				}
				albumName00.setText(albums.get(currentAlbumIndex).getAlbumName());
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0) {
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex).getPhotos().get(albums.get(currentAlbumIndex).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation() != null) {
					
					imageView10.setImage(new Image(albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation()));
				}
				albumName10.setText(albums.get(currentAlbumIndex + 1).getAlbumName());
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0) {
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(albums.get(currentAlbumIndex + 1).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
			
			
				
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation() != null) {
					
					imageView01.setImage(new Image(albums.get(currentAlbumIndex + 2).getPhotos().get(0).getLocation()));
				}
				albumName01.setText(albums.get(currentAlbumIndex + 2).getAlbumName());
				if(albums.get(currentAlbumIndex + 2).getPhotos().size() > 0) {
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 2).getPhotos().get(albums.get(currentAlbumIndex + 2).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption01.setText(albums.get(currentAlbumIndex + 2).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
			}
			else if(albums.size() - currentAlbumIndex > 1) {
				
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0 && albums.get(currentAlbumIndex).getPhotos().get(0).getLocation() != null) {
					
					imageView00.setImage(new Image(albums.get(currentAlbumIndex).getPhotos().get(0).getLocation()));
				}
				albumName00.setText(albums.get(currentAlbumIndex).getAlbumName());
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0) {
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex).getPhotos().get(albums.get(currentAlbumIndex).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
				
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0 && albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation() != null) {
					
					imageView10.setImage(new Image(albums.get(currentAlbumIndex + 1).getPhotos().get(0).getLocation()));
				}
				albumName10.setText(albums.get(currentAlbumIndex + 1).getAlbumName());
				if(albums.get(currentAlbumIndex + 1).getPhotos().size() > 0) {
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex + 1).getPhotos().get(albums.get(currentAlbumIndex + 1).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption10.setText(albums.get(currentAlbumIndex + 1).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
			}
			else if(albums.size() - currentAlbumIndex > 0) {
				
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0 && albums.get(currentAlbumIndex).getPhotos().get(0).getLocation() != null) {
					
					imageView00.setImage(new Image(albums.get(currentAlbumIndex).getPhotos().get(0).getLocation()));
				}
				albumName00.setText(albums.get(currentAlbumIndex).getAlbumName());
				if(albums.get(currentAlbumIndex).getPhotos().size() > 0) {
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos\nDates: " 
							+ albums.get(currentAlbumIndex).getPhotos().get(0).getDate() + " - "
							+ albums.get(currentAlbumIndex).getPhotos().get(albums.get(currentAlbumIndex).getPhotos().size() - 1).getDate().toString());
				} else {
					
					caption00.setText(albums.get(currentAlbumIndex).getPhotos().size() + " photos / Dates: xx-xx-xxxx - xx-xx-xxxx");
				}
				
				
			}
		}
	}
	
	/**
	 * Moves to the next page of albums
	 */
	public void increaseCurrentAlbumIndex() {
		
		if(albums.size() - currentAlbumIndex > 5) {
			currentAlbumIndex += 6;
		}
		
		resetScreen();
		populateImages();
	}
	
	/**
	 * Returns to the previous page of albums
	 */
	public void decreaseCurrentAlbumIndex() {
		
		if(currentAlbumIndex - 6 >= 0) {
			
			currentAlbumIndex -= 6;
		}
		
		resetScreen();
		populateImages();
	}
	
	/**
	 * Resets the screen to an empty screen
	 */
	public void resetScreen() {
		
		imageView00.setImage(null);
    	imageView10.setImage(null);
    	imageView01.setImage(null);
    	imageView11.setImage(null);
    	imageView02.setImage(null);
    	imageView12.setImage(null);;
    	albumName00.setText("");
    	albumName10.setText("");
    	albumName01.setText("");
    	albumName11.setText("");
    	albumName02.setText("");
    	albumName12.setText("");
    	caption00.setText("");
    	caption10.setText("");
    	caption01.setText("");
    	caption11.setText("");
    	caption02.setText("");
    	caption12.setText("");
		
	}
	
	/**
	 * Adds an album
	 * 
	 * @param evt the event that invoked the method call
	 */
    @FXML
    private void addAlbum(ActionEvent evt) {
    	
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Add Album");
        alert.setHeaderText("Creating an Album");
        alert.setResizable(true);
        Label label1 = new Label("Album Name: ");

        TextField text1 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        alert.getDialogPane().setContent(grid);

        Optional<ButtonType> x = alert.showAndWait();

        if (x.get() == ButtonType.OK) {
            if (text1.getText().isEmpty()) {
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("You must enter an Album name");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
            } else {
                Album album = new Album();
                album.setAlbumName(text1.getText());
                albums.add(album);
                //*****user.deletealum*****
                alert.close();

            }
        }
        try {
			SerializableController.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        populateImages();
    }
    
    /**
     * Deletes the album
     * 
     * @param evt the event that invoked the method call
     */
    @FXML
    private void deleteAlbum(ActionEvent evt) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        int indexToRemove = -1;
        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentAlbumIndex;
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentAlbumIndex + 1;
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentAlbumIndex + 2;
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentAlbumIndex + 3;
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentAlbumIndex + 4;
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRemove = currentAlbumIndex + 5;
        }
        else
        {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify an album.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
            return;
        }

        alert.setTitle("Album Deletion");
        alert.setHeaderText("Are you sure you want to delete the album?");
        alert.setResizable(true);

        Optional<ButtonType> x = alert.showAndWait();

        if (x.get() == ButtonType.OK) {
        	
            albums.remove(indexToRemove);
        }
        else{
        	
            alert.close();
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
     * Renames the album
     * 
     * @param evt the event that caused the method call
     */
    @FXML
    private void renameAlbum(ActionEvent evt) {
    	
    	int indexToRename = -1;
        
        if(pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRename = currentAlbumIndex;
        }
        else if(pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRename = currentAlbumIndex + 1;
        }
        else if(pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRename = currentAlbumIndex + 2;
        }
        else if(pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRename = currentAlbumIndex + 3;
        }
        else if(pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRename = currentAlbumIndex + 4;
        }
        else if(pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
        	indexToRename = currentAlbumIndex + 5;
        }
        else {
        	Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("You must specify an album.");
            Optional<ButtonType> z = alert2.showAndWait();
            if (z.get() == ButtonType.OK) {
                alert2.close();
            }
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Rename Album");
        alert.setHeaderText("New Album Name");
        alert.setResizable(true);

        Label label1 = new Label("New Album Name: ");
        TextField text1 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        alert.getDialogPane().setContent(grid);

        Optional<ButtonType> x = alert.showAndWait();

        if (x.get() == ButtonType.OK) {
            if (text1.getText().isEmpty()) {
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("You must enter an Album name");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
            } else {
            	albums.get(indexToRename).setAlbumName(text1.getText());
                alert.close();

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
     * Goes to the search function
     */
    public void search() {
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/SearchScreen.fxml"));
        try {
            AnchorPane root = (AnchorPane)loader.load();
            SearchController searchview = loader.getController();
            Stage stage = new Stage();
            SerializableController.save(users);
      
            searchview.start(stage, userIndex);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    	
    }
    
    /**
     * Logs the user out back to the login screen
     * 
     * @param e the event that invoked the method
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
    
	/**
	 * 
	 * 
	 * @param u
	 */
	public void setUser(User u) {

		this.user = u;

	}


}


