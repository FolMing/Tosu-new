import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LevelMenu {
	private static Scene scene;

	public LevelMenu() {
		StackPane root = new StackPane();

		ImageView background = ImageViewCreator.Create("Level Menu/Background.jpg", 1300, 740);

		root.getChildren().add(background);

		Pane pane = new Pane();

		ImageView backButton = ImageViewCreator.Create("Level Menu/back_off.png", 420, 70);
		backButton.setLayoutX(-100);
		backButton.setLayoutY(623);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefViewportHeight(610);
		scrollPane.setPrefViewportWidth(1260);

		VBox vbox = new VBox();

		for (int i = 0; i < LevelDirectories.getLevelDirs().length; i++)
		{
			Button button = new Button(LevelDirectories.getLevelDirs()[i]);
			vbox.getChildren().add(button);
		}

		backButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setImage(new Image("Level Menu/back_off.png"));
			}
		});

		backButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setImage(new Image("Level Menu/back_on.png"));
			}
		});

		backButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(MainMenu.getScene());
			}
		});

		scrollPane.setContent(vbox);

		pane.getChildren().addAll(backButton, scrollPane);

		root.getChildren().add(pane);

		scene = new Scene(root, 1280, 720);
	}

	public static Scene getScene() {
		return scene;
	}
}
