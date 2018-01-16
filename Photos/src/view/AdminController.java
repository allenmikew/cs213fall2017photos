package view;

import app.Album;
import app.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SerializableController;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

/**
 * This class is the controller for the Admin view screen, and controls all the behaviors
 * of the screen and buttons.
 * 
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 */
public class AdminController implements Serializable{

	/**
	 * The listview of all the users
	 */
    @FXML ListView<User> usersListView;
    
    /**
     * An ArrayList of all of the users
     */
    ArrayList<User> users = new ArrayList<User>();
    
    /**
     * An observable list of all of the users
     */
    ObservableList<User> obsList;
    
    
    /**
     * The primary stage that is shown to the user
     */
    Stage primaryStage;
    
    /**
     * Starts the controller
     * 
     * @param primaryStage the main stage that is shown to the user
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {


        // create an ObservableList
        // from an ArrayList
       // s = SerializableObjects.read();
        //users = s.getUserList();
        //obsList = FXCollections.observableArrayList(s.getUserList());
    	
    	this.primaryStage = primaryStage;

    	users = SerializableController.read();
    	
    	obsList = FXCollections.observableArrayList();
    	
    	// add all users to obslist
    	
    	for(int i = 0; i < users.size(); i++) {
    		
    		obsList.add(users.get(i));
    	}
    	
        usersListView.setItems(obsList);

        // select the first item
        usersListView.getSelectionModel().select(0);

        usersListView.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUsername() == null) {
                    setText(null);
                } else {
                    setText(item.getUsername());
                   
                }
            }
        });
        
        // ON STAGE CLOSE...
        primaryStage.setOnCloseRequest( event -> {
        	
        	
        	
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
        	
        });
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
                obsList.add(user);
                users.add(user);
                alert.close();

            }
            else {
                User user = new User(text1.getText(), text2.getText());
                obsList.add(user);
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
    
    public void editUserName(ActionEvent evt) {
    	
        User user = usersListView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edit Username");
        alert.setHeaderText("New Username");
        alert.setResizable(true);
        Label label1 = new Label("New Username: ");

        TextField text1 = new TextField();
       

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        text1.setPromptText(user.getUsername());

        alert.getDialogPane().setContent(grid);

        Optional<ButtonType> x = alert.showAndWait();

        if (x.get() == ButtonType.OK) {
            if (text1.getText().isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("You must enter a new username.");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
            }
            else {
            	
            	for(int i = 0; i < users.size(); i++) {
            		
            		if(text1.getText().equalsIgnoreCase(users.get(i).getUsername())) {
            			  Alert alert2 = new Alert(Alert.AlertType.ERROR);
                          alert2.setTitle("There is already a user with that username.");
                          Optional<ButtonType> z = alert2.showAndWait();
                          if (z.get() == ButtonType.OK) {
                              alert2.close();
                          }
                          
                          return;
            		}
            	}
            	
            	user.setUsername(text1.getText());
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
    
    public void editPassword(ActionEvent e) {
    	
    	User user = usersListView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edit Password");
        alert.setHeaderText("New Password");
        alert.setResizable(true);
        Label label1 = new Label("New Password: ");
        Label label2 = new Label("Confirm Password: ");

        PasswordField text1 = new PasswordField();
        PasswordField text2 = new PasswordField();
      
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
                alert2.setTitle("You must enter a new password.");
                Optional<ButtonType> z = alert2.showAndWait();
                if (z.get() == ButtonType.OK) {
                    alert2.close();
                }
            }
            else {
            	
            	if(text1.getText().equals(text2.getText())) {
            		
            		user.setPassword(text1.getText());
            	}
            	else
            	{
            		 Alert alert2 = new Alert(Alert.AlertType.ERROR);
                     alert2.setTitle("Passwords must match.");
                     Optional<ButtonType> z = alert2.showAndWait();
                     if (z.get() == ButtonType.OK) {
                         alert2.close();
                     }
                     return;
            	}
            	
            }
        }
        else {
            alert.close();
        }
        
        try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    /**
     * Deletes the user from the users list
     * 
     * @param e the event that caused this method to run
     */
    public void deleteUser(ActionEvent e) {
    	
        User user = usersListView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //int index = listView.getSelectionModel().getSelectedIndex();

        alert.setTitle("Album Deletion");
        alert.setHeaderText("Are you sure you want to delete the album?");
        alert.setResizable(true);

        Optional<ButtonType> x = alert.showAndWait();

        if (x.get() == ButtonType.OK) {
            obsList.remove(user);
            users.remove(user);
            insertionSort(obsList);
            alert.close();
        }
        else{
            alert.close();
        }
        
        try {
			SerializableController.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    /**
     * Uses insertion sort to sort the users list
     * 
     * @param input the list of the users
     * @return a sorted users list
     */
    private ObservableList<User> insertionSort(ObservableList<User> input) {

        Collections.sort(obsList, new Comparator() {

            public int compare(Object o1, Object o2) {

                String x1 = ((User) o1).getUsername();
                String x2 = ((User) o2).getUsername();
                int sComp = x1.toLowerCase().compareTo(x2.toLowerCase());
                return sComp;
            }
        });
        return input;
    }
    
    /**
     * Logs out the current user and opens the login screen
     * 
     * @param e the event that invoked the method call
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