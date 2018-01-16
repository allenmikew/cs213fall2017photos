package view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import app.Album;
import app.Photo;
import app.Tag;
import app.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.SerializableController;

/**
 * This class handles the login view
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class LoginViewController implements Serializable {

	// Login screen FXML
	
	/**
	 * The login fxml button
	 */
	@FXML Button loginButton;
	
	/**
	 * The new user fxml button
	 */
	@FXML Button newUserButton;
	
	/**
	 * the username field
	 */
	@FXML TextField usernameField;
	
	/**
	 * The password field
	 */
	@FXML PasswordField passwordField;
	
	//SerializableObjects userlist = new SerializableObjects();
	
	Stage primaryStage;
	
	/**
	 * ArrayList of all of the users
	 */
	ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * Starts the controller
	 * 
	 * @param primaryStage the main stage that is shown to the user
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		
		try {
			users = SerializableController.read();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// ON STAGE CLOSE
		primaryStage.setOnCloseRequest( event -> {
			
			
			try {
				SerializableController.save(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		boolean stockExists = false;
		
		for(int i = 0; i < users.size(); i++) {
			
			if(users.get(i).getUsername().equals("stock"))
			{
				stockExists = true;
			}
		}
		
		if(!stockExists) {
			
			createStockUser();
		}
	}
	
	/**
	 * Attempts to log the user in.
	 * 
	 */
	public void login(ActionEvent e) throws Exception {
		
		// Put the username and password into a String to compare
		String username = usernameField.getText();
		String password = passwordField.getText();

		Parent parent;
		//userlist = SerializableObjects.read();
	
		// If the length of the input is 0 (i.e. there is no text in the username field...
		if(username.length() == 0) {
		
			// Throw an alert, prompting the user to input a username
			Alert usernameAlert = new Alert(AlertType.ERROR);
	        usernameAlert.setTitle("No username");
	        usernameAlert.setContentText("Please enter a username and password.");
	        
	        // Show the optional OK button to push to close the alert
	        Optional<ButtonType> z = usernameAlert.showAndWait();
	        
	        if (z.get() == ButtonType.OK) {
	        	
	            usernameAlert.close();
	        }
		}
	
		// Authenticate username and password
		
		if(users == null || users.size() == 0) {
			
			// Throw an alert, prompting the user to input a username
			Alert usernameAlert = new Alert(AlertType.ERROR);
	        usernameAlert.setTitle("Failed to authenticate.");
	        usernameAlert.setContentText("Please enter a valid username and password.");
	        
	        // Show the optional OK button to push to close the alert
	        Optional<ButtonType> z = usernameAlert.showAndWait();
	        
	        if (z.get() == ButtonType.OK) {
	        	
	            usernameAlert.close();
	        }
		}
		
		
		if(username.equals("admin") && password.equals("admin")){
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/AdminScreen.fxml"));
	        try {
	        	primaryStage.close();
	            AnchorPane root = (AnchorPane)loader.load();
	            AdminController adminview = loader.getController();
	            Stage stage = new Stage();
	            
	            SerializableController.save(users);
	            adminview.start(stage);
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	
	        } catch (IOException exception) {
	            exception.printStackTrace();
	        }
	
		}
		// Scan through the users list and attempt to match the username and password
		for(int i = 0; i < users.size(); i++) {
			
			if(users.get(i).authenticate(username, password)) {
	
				
				
				
					if(username.equals("admin") && password.equals("admin")){
						
						primaryStage.close();
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/AdminScreen.fxml"));
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            AdminController adminview = loader.getController();
				            Stage stage = new Stage();
				            SerializableController.save(users);
				      
				            adminview.start(stage);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (IOException exception) {
				            exception.printStackTrace();
				        }
	
					}
					else{
						
						primaryStage.close();
						FXMLLoader loader = new FXMLLoader();
				        loader.setLocation(getClass().getResource("/view/AlbumsScreen.fxml"));
				        try {
				            AnchorPane root = (AnchorPane)loader.load();
				            AlbumController albumview = loader.getController();
				            Stage stage = new Stage();
				            SerializableController.save(users);
				      
				            albumview.start(stage, i);
				            Scene scene = new Scene(root,800,600);
				            stage.setScene(scene);
				            stage.show();
	
				        } catch (IOException exception) {
				            exception.printStackTrace();
				        }
						
					}
	
			}
			else {
				
				
			}
			
		}
	}
	
	/**
	 * Creates an initializes the stock user, adds to users list
	 */
	public void createStockUser() {
		
		User user = new User("stock", "stock");
		
		Album stockAlbum = new Album();
		stockAlbum.setAlbumName("Stock Photos");
		
		Photo beach = new Photo();
		beach.setPhoto(new Image("beach.jpg"));
		beach.setLocation("beach.jpg");
		beach.setPhotoName("Beach");
		beach.setCaption("If you're lucky enough to be at the beach, you're lucky enough.");
		ArrayList<Tag> beachTags = new ArrayList<Tag>();
		beachTags.add(new Tag("beach", "view"));
		beachTags.add(new Tag("beach", "beautiful"));
		beachTags.add(new Tag("season", "summer"));
		beach.setTags(beachTags);
		stockAlbum.addPhoto(beach);
		
		Photo dock = new Photo();
		dock.setPhoto(new Image("dock.jpg"));
		dock.setLocation("dock.jpg");
		dock.setPhotoName("Dock");
		dock.setCaption("Set sail.");
		ArrayList<Tag> dockTags = new ArrayList<Tag>();
		dockTags.add(new Tag("nature", "dock"));
		dockTags.add(new Tag("sunset", "awesome"));
		dockTags.add(new Tag("craft", "wooden"));
		dock.setTags(dockTags);
		stockAlbum.addPhoto(dock);
		
		Photo family = new Photo();
		family.setPhoto(new Image("family.jpg"));
		family.setLocation("family.jpg");
		family.setPhotoName("Family");
		family.setCaption("Family means nobody gets left behind.");
		ArrayList<Tag> familyTags = new ArrayList<Tag>();
		familyTags.add(new Tag("family", "quote"));
		familyTags.add(new Tag("bears", "friendly"));
		family.setTags(familyTags);
		stockAlbum.addPhoto(family);
		
		Photo forestcreek = new Photo();
		forestcreek.setPhoto(new Image("forest creek.jpg"));
		forestcreek.setLocation("forest creek.jpg");
		forestcreek.setPhotoName("Forest Creek");
		forestcreek.setCaption("The forest speaks.");
		stockAlbum.addPhoto(forestcreek);
		
		Photo london = new Photo();
		london.setPhoto(new Image("london.jpg"));
		london.setLocation("london.jpg");
		london.setPhotoName("London");
		london.setCaption("Eye see you.");
		stockAlbum.addPhoto(london);
		
		Photo panda = new Photo();
		panda.setPhoto(new Image("panda.jpg"));
		panda.setLocation("panda.jpg");
		panda.setPhotoName("Panda");
		panda.setCaption("Pure happiness.");
		stockAlbum.addPhoto(panda);
		
		Photo planelanding = new Photo();
		planelanding.setPhoto(new Image("plane landing.jpg"));
		planelanding.setLocation("plane landing.jpg");
		planelanding.setPhotoName("Plane Landing");
		planelanding.setCaption("Touchdown.");
		stockAlbum.addPhoto(planelanding);
		
		Photo success = new Photo();
		success.setPhoto(new Image("success.jpg"));
		success.setLocation("success.jpg");
		success.setPhotoName("Success");
		success.setCaption("Just do it.");
		stockAlbum.addPhoto(success);
		
		Photo sunset = new Photo();
		sunset.setPhoto(new Image("sunset.jpg"));
		sunset.setLocation("sunset.jpg");
		sunset.setPhotoName("Sunset");
		sunset.setCaption("Red sky at night, sailor's delight.");
		stockAlbum.addPhoto(sunset);
		
		Photo volcano = new Photo();
		volcano.setPhoto(new Image("volcano.jpg"));
		volcano.setLocation("volcano.jpg");
		volcano.setPhotoName("Volcano");
		volcano.setCaption("Hot, hot, hot!");
		stockAlbum.addPhoto(volcano);
		
		user.addAlbum(stockAlbum);
		
		users.add(user);
	}
	
	/**
     * Adds a user to the list of users
     * 
     * @param evt the event that took place to trigger the method
     */
    public void addUser(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add User");
        alert.setHeaderText("Adding User");
        alert.setResizable(true);
        Label label1 = new Label("Username: ");
        Label label2 = new Label("Password: ");

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
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("You must enter a User name");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
            } else if (text2.getText().isEmpty()) {
                User user = new User(text1.getText());
                users.add(user);
                alert.close();

            }
            else {
                User user = new User(text1.getText(), text2.getText());
                users.add(user);
                alert.close();

            }
        }
        else {
            alert.close();
        }
        
        try {
			SerializableController.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
