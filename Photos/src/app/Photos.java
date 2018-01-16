package app;
	
import java.io.IOException;
import java.io.Serializable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.PhotoViewController;
import view.AlbumController;
import view.LoginViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;

/**
 * @author Mike Allen
 * @author Ziad Bekhiet
 *
 *	This is the main class of the photos application, and is reponsible for launching the
 *  program initially.
 */
public class Photos extends Application {
	
	/**
	 * Starts the program, and sets the stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/LoginScreen.fxml"));
    	
    	AnchorPane root = (AnchorPane)loader.load();
    	
    	LoginViewController loginview = loader.getController();
        loginview.start(primaryStage);

    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Photos.java");
    	primaryStage.setResizable(false);
    	primaryStage.show();
	}
	
	/**
	 * The main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
