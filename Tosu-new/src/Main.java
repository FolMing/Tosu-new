import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tosu!");
        StackPane root = new StackPane();
        
        Screen screen = Screen.getPrimary();
        Rectangle2D screenBounds = screen.getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight() - 20;
        
        ImageView mainMenuBGView = ImageViewCreator.fullscreenImageCreate(screenWidth, screenHeight, "/MainMenuBG.jpg");
        
        root.getChildren().add(mainMenuBGView);

        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
        primaryStage.show();
	}
}
