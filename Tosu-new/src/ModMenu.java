import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ModMenu {
	private static Scene scene;
	private static boolean autoEnabled = false;

	public ModMenu() {
		StackPane root = new StackPane();

		ImageView background = ImageViewCreator.Create("Mod menu/Background.jpg", 1300, 740);

		root.getChildren().add(background);

		Pane pane = new Pane();

		ImageView autoImage = ImageViewCreator.Create("Mod menu/auto.png", 136, 132);

		ImageView backButton = ImageViewCreator.Create("Mod Menu/back_off.png", 420, 70);
		backButton.setLayoutX(-100);
		backButton.setLayoutY(623);

		autoImage.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (autoEnabled) {
					autoEnabled = false;
					autoImage.setScaleX(1);
					autoImage.setScaleY(1);
				}
				else {
					autoEnabled = true;
					autoImage.setScaleX(1.2);
					autoImage.setScaleY(1.2);
				}
			}
		});

		backButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setImage(new Image("Mod Menu/back_off.png"));
			}
		});

		backButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setImage(new Image("Mod Menu/back_on.png"));
			}
		});

		backButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(LevelMenu.getScene());
			}
		});

		pane.getChildren().addAll(backButton);

		root.getChildren().addAll(pane, autoImage);

		scene = new Scene(root, 1280, 720);
	}

	public static boolean getAutoEnabled() {
		return autoEnabled;
	}

	public static Scene getScene() {
		return scene;
	}
}
