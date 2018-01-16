package view;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.stage.Stage;
import model.SerializableController;

/**
 * This class controls the search view.
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class SearchController implements Serializable{

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
	@FXML Label usernameLabel;
	
	Stage primaryStage;
	
	/**
	 * Results of the photo search
	 */
	ArrayList<Photo> searchResults = new ArrayList<Photo>();
	
	/**
	 * All of the albums in the user's library
	 */
	ArrayList<Album> albums = new ArrayList<Album>();
	
	/**
	 * Master list of all the photos in the users albums
	 */
	ArrayList<Photo> allPhotos = new ArrayList<Photo>();
	
	ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * Index of the base photo in the photo results list
	 */
	int searchResultsIndex = 0;
		
	/**
	 * Starts the controller
	 * @param primaryStage
	 */
	public void start(Stage primaryStage, int userIndex) {
		
		this.primaryStage = primaryStage;
		try {
			users = SerializableController.read();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resetScreen();
		this.albums = users.get(userIndex).getAlbums();
		
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
					
				} );
		
	}
	
	/**
	 * Searches all the users albums for photos with matching tags
	 */
	public void searchByTag() {
    	
		String tagName = "";
		String tagValue = "";
		
		createAllPhotosList();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Search By Tag");
        alert.setHeaderText("Search By Tag");
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
            if (text1.getText().isEmpty()) {
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("You must enter an photo name");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
            } else {
                
            	tagName = text1.getText();
            	tagValue = text2.getText();
                
                alert.close();

            }
        }
		
		
		searchResults = new ArrayList<Photo>();
		
    	if(albums.size() == 0) {
    		
    		
    	}
    	else {
    		
    		for(int i = 0; i < allPhotos.size(); i++) {
    			
    			for(int j = 0; j < allPhotos.get(i).getTags().size(); j++) {
    				
    				if(allPhotos.get(i).getTags().get(j).getTagName().equalsIgnoreCase(tagName)
    						&& allPhotos.get(i).getTags().get(j).getTagValue().equalsIgnoreCase(tagValue)) {
    					
    					searchResults.add(allPhotos.get(i));
    				}
    			}
    		}
    	}
    	
    	populateImages();
	}
	
	/**
	 * Creates a master list of photos from all albums
	 */
	public void createAllPhotosList() {
		
		for(int i = 0; i < albums.size(); i++) {
			
			if(albums.get(i).getPhotos().size() > 0) {
				
				for(int j = 0; j < albums.get(i).getPhotos().size(); j++) {
					
					allPhotos.add(albums.get(i).getPhotos().get(j));
				}
			}
		}
	}
	
	/**
	 * Searches all the users albums for photos with matching date ranges
	 */
	public void searchByDateRange() {
		
		int startDateDay = 0, endDateDay = 0;
		int startDateMonth = 0, endDateMonth = 0;
		int startDateYear = 0, endDateYear = 0;
		
		createAllPhotosList();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().setMinHeight(AnchorPane.USE_PREF_SIZE);
        alert.setTitle("Search By Date Range");
        alert.setHeaderText("Please enter dates in number form (ex. 12 2 1994");
        alert.setResizable(true);
        Label label1 = new Label("Start Date - Day: ");
        Label label2 = new Label("Start Date - Month: ");
        Label label3 = new Label("Start Date - Year: ");
        Label label4 = new Label("End Date - Day: ");
        Label label5 = new Label("End Date - Month: ");
        Label label6 = new Label("End Date - Year: ");

        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        TextField text4 = new TextField();
        TextField text5 = new TextField();
        TextField text6 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        grid.add(label3, 1, 3);
        grid.add(text3, 2, 3);
        grid.add(label4, 1, 4);
        grid.add(text4, 2, 4);
        grid.add(label5, 1, 5);
        grid.add(text5, 2, 5);
        grid.add(label6, 1, 6);
        grid.add(text6, 2, 6);
        
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
                
            	startDateDay = Integer.parseInt(text1.getText());
            	endDateDay = Integer.parseInt(text4.getText());
            	
            	startDateMonth = Integer.parseInt(text2.getText());
            	endDateMonth = Integer.parseInt(text5.getText());
            	
            	startDateYear = Integer.parseInt(text3.getText());
            	endDateYear = Integer.parseInt(text6.getText());
                
                alert.close();

            }
        }
		
        LocalDate startDate = LocalDate.of(startDateYear, startDateMonth, startDateDay);
        LocalDate endDate = LocalDate.of(endDateYear, endDateMonth, endDateDay);
		
		searchResults = new ArrayList<Photo>();
		
    	if(albums.size() == 0) {
    		
    		
    	}
    	else {
    		
    		for(int i = 0; i < allPhotos.size(); i++) {
    			
    			Date testDate = allPhotos.get(i).getDate();
    			LocalDate date = testDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    			
    			if(date.isAfter(startDate) && date.isBefore(endDate)) {
    				
    				searchResults.add(allPhotos.get(i));
    			}
    		}
    	}
    	
    	populateImages();
		
	}
	
	public void createAlbumFromSearchResults() {
		
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
                
                for(int i = 0; i < searchResults.size(); i++) {
                	
                	album.addPhoto(searchResults.get(i));
                }
                
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
	}
	
	/**
	 * Moves to the next page of albums
	 */
	public void increaseSearchResultsIndex() {
		
		if(searchResults.size() - searchResultsIndex > 5) {
			searchResultsIndex += 6;
		}
		
		resetScreen();
		populateImages();
	}
	
	/**
	 * Returns to the previous page of albums
	 */
	public void decreaseSearchResultsIndex() {
		
		if(searchResultsIndex - 6 >= 0) {
			
			searchResultsIndex -= 6;
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
	
	public void populateImages() {
		
		if(searchResults == null || searchResults.size() == 0) {
			
		}
		else
		{
			
			if(searchResults.size() - searchResultsIndex > 5) {
				
				imageView00.setImage(searchResults.get(searchResultsIndex).getPhoto());;
				photoName00.setText(searchResults.get(searchResultsIndex).getPhotoName());
				caption00.setText(searchResults.get(searchResultsIndex).getCaption());
				
				imageView10.setImage(searchResults.get(searchResultsIndex + 1).getPhoto());
				photoName10.setText(searchResults.get(searchResultsIndex + 1).getPhotoName());
				caption10.setText(searchResults.get(searchResultsIndex + 1).getCaption());
				
				imageView01.setImage(searchResults.get(searchResultsIndex + 2).getPhoto());
				photoName01.setText(searchResults.get(searchResultsIndex + 2).getPhotoName());
				caption01.setText(searchResults.get(searchResultsIndex + 2).getCaption());
				
				imageView11.setImage(searchResults.get(searchResultsIndex + 3).getPhoto());
				photoName11.setText(searchResults.get(searchResultsIndex + 3).getPhotoName());
				caption11.setText(searchResults.get(searchResultsIndex + 3).getCaption());
				
				imageView02.setImage(searchResults.get(searchResultsIndex + 4).getPhoto());
				photoName02.setText(searchResults.get(searchResultsIndex + 4).getPhotoName());
				caption02.setText(searchResults.get(searchResultsIndex + 4).getCaption());
				
				imageView12.setImage(searchResults.get(searchResultsIndex + 5).getPhoto());
				photoName12.setText(searchResults.get(searchResultsIndex + 5).getPhotoName());
				caption12.setText(searchResults.get(searchResultsIndex + 5).getCaption());
			}
			else if(searchResults.size() - searchResultsIndex > 4) {
				
				imageView00.setImage(searchResults.get(searchResultsIndex).getPhoto());;
				photoName00.setText(searchResults.get(searchResultsIndex).getPhotoName());
				caption00.setText(searchResults.get(searchResultsIndex).getCaption());
				
				imageView10.setImage(searchResults.get(searchResultsIndex + 1).getPhoto());
				photoName10.setText(searchResults.get(searchResultsIndex + 1).getPhotoName());
				caption10.setText(searchResults.get(searchResultsIndex + 1).getCaption());
				
				imageView01.setImage(searchResults.get(searchResultsIndex + 2).getPhoto());
				photoName01.setText(searchResults.get(searchResultsIndex + 2).getPhotoName());
				caption01.setText(searchResults.get(searchResultsIndex + 2).getCaption());
				
				imageView11.setImage(searchResults.get(searchResultsIndex + 3).getPhoto());
				photoName11.setText(searchResults.get(searchResultsIndex + 3).getPhotoName());
				caption11.setText(searchResults.get(searchResultsIndex + 3).getCaption());
				
				imageView02.setImage(searchResults.get(searchResultsIndex + 4).getPhoto());
				photoName02.setText(searchResults.get(searchResultsIndex + 4).getPhotoName());
				caption02.setText(searchResults.get(searchResultsIndex + 4).getCaption());
			}
			else if(searchResults.size() - searchResultsIndex > 3) {
				
				imageView00.setImage(searchResults.get(searchResultsIndex).getPhoto());;
				photoName00.setText(searchResults.get(searchResultsIndex).getPhotoName());
				caption00.setText(searchResults.get(searchResultsIndex).getCaption());
				
				imageView10.setImage(searchResults.get(searchResultsIndex + 1).getPhoto());
				photoName10.setText(searchResults.get(searchResultsIndex + 1).getPhotoName());
				caption10.setText(searchResults.get(searchResultsIndex + 1).getCaption());
				
				imageView01.setImage(searchResults.get(searchResultsIndex + 2).getPhoto());
				photoName01.setText(searchResults.get(searchResultsIndex + 2).getPhotoName());
				caption01.setText(searchResults.get(searchResultsIndex + 2).getCaption());
				
				imageView11.setImage(searchResults.get(searchResultsIndex + 3).getPhoto());
				photoName11.setText(searchResults.get(searchResultsIndex + 3).getPhotoName());
				caption11.setText(searchResults.get(searchResultsIndex + 3).getCaption());
			}
			else if(searchResults.size() - searchResultsIndex > 2) {
				
				imageView00.setImage(searchResults.get(searchResultsIndex).getPhoto());;
				photoName00.setText(searchResults.get(searchResultsIndex).getPhotoName());
				caption00.setText(searchResults.get(searchResultsIndex).getCaption());
				
				imageView10.setImage(searchResults.get(searchResultsIndex + 1).getPhoto());
				photoName10.setText(searchResults.get(searchResultsIndex + 1).getPhotoName());
				caption10.setText(searchResults.get(searchResultsIndex + 1).getCaption());
				
				imageView01.setImage(searchResults.get(searchResultsIndex + 2).getPhoto());
				photoName01.setText(searchResults.get(searchResultsIndex + 2).getPhotoName());
				caption01.setText(searchResults.get(searchResultsIndex + 2).getCaption());
			}
			else if(searchResults.size() - searchResultsIndex > 1) {
				
				imageView00.setImage(searchResults.get(searchResultsIndex).getPhoto());;
				photoName00.setText(searchResults.get(searchResultsIndex).getPhotoName());
				caption00.setText(searchResults.get(searchResultsIndex).getCaption());
				
				imageView10.setImage(searchResults.get(searchResultsIndex + 1).getPhoto());
				photoName10.setText(searchResults.get(searchResultsIndex + 1).getPhotoName());
				caption10.setText(searchResults.get(searchResultsIndex + 1).getCaption());
			}
			else if(searchResults.size() - searchResultsIndex > 0) {
				
				
				imageView00.setImage(searchResults.get(searchResultsIndex).getPhoto());;
				photoName00.setText(searchResults.get(searchResultsIndex).getPhotoName());
				caption00.setText(searchResults.get(searchResultsIndex).getCaption());
			}
		}
	}
	
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
