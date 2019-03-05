import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ExitMenu {
	private static Scene scene;

	public ExitMenu() {
		StackPane root = new StackPane();

		ImageView background = ImageViewCreator.Create("Exit Menu/Background.jpg", 1300, 740);

		root.getChildren().add(background);

		Pane pane = new Pane();

		ImageView yesButton = ImageViewCreator.Create("Exit Menu/ExitYes.jpg", 700, 70);
		yesButton.setLayoutX(290);
		yesButton.setLayoutY(233);

		ImageView noButton = ImageViewCreator.Create("Exit Menu/ExitNo.jpg", 700, 70);
		noButton.setLayoutX(290);
		noButton.setLayoutY(330);

		yesButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Platform.exit();
			}
		});

		noButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(MainMenu.getScene());
			}
		});

		pane.getChildren().addAll(yesButton, noButton);

		root.getChildren().add(pane);

		scene = new Scene(root, 1280, 720);
	}

	public static Scene getScene() {
		return scene;
	}
}
