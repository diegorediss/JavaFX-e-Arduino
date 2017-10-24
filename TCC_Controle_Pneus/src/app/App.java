package app;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    
    @Override
    public void start(Stage primaryStage) throws IOException {
              
        Parent root = FXMLLoader.load(getClass().getResource("viewPrincipal.fxml"));        
        primaryStage.setTitle("VULCANIZADORA");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("css/estilo.css"); 
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
  public static void main(String[] args) {
        launch(args);
    }      

   
    
}
