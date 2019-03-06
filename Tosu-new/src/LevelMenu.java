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

		ImageView modsButton = ImageViewCreator.Create("Level Menu/mods_off.png", 57, 70);
		modsButton.setLayoutX(305);
		modsButton.setLayoutY(623);

		ImageView randomButton = ImageViewCreator.Create("Level Menu/random_off.png", 57, 70);
		randomButton.setLayoutX(363);
		randomButton.setLayoutY(623);

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

		modsButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				modsButton.setImage(new Image("Level Menu/mods_off.png"));
			}
		});

		modsButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				modsButton.setImage(new Image("Level Menu/mods_on.png"));
			}
		});

		modsButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(ModMenu.getScene());
			}
		});

		randomButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				randomButton.setImage(new Image("Level Menu/random_off.png"));
			}
		});

		randomButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				randomButton.setImage(new Image("Level Menu/random_on.png"));
			}
		});
/*
		backButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(MainMenu.getScene());
			}
		});
*/
		scrollPane.setContent(vbox);

		pane.getChildren().addAll(modsButton, randomButton, backButton, scrollPane);

		root.getChildren().add(pane);

		scene = new Scene(root, 1280, 720);
	}

	public static Scene getScene() {
		return scene;
	}
}
