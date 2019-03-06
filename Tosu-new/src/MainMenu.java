import java.io.File;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainMenu {
	private static Scene scene;

	public MainMenu() {
		StackPane root = new StackPane();

        ImageView mainMenuBGView = ImageViewCreator.Create("Main Menu/MainMenuBG.jpg", 1300, 740);
        root.getChildren().add(mainMenuBGView);

        Pane pane = new Pane();

        ImageView mainMenuLogo = ImageViewCreator.Create("Main Menu/tosu! logo.png", 320, 280);
        mainMenuLogo.setLayoutX(380);
        mainMenuLogo.setLayoutY(200);

        ImageView mainMenuPlayButton = ImageViewCreator.Create("Main Menu/play_button_off.png", 420, 70);
        mainMenuPlayButton.setLayoutX(550);
        mainMenuPlayButton.setLayoutY(233);

        ImageView mainMenuOptionsButton = ImageViewCreator.Create("Main Menu/options_button_off.png", 420, 70);
        mainMenuOptionsButton.setLayoutX(542);
        mainMenuOptionsButton.setLayoutY(303);

        ImageView mainMenuExitButton = ImageViewCreator.Create("Main Menu/exit_button_off.png", 420, 70);
        mainMenuExitButton.setLayoutX(545);
        mainMenuExitButton.setLayoutY(373);

        ImageView mainMenuUserBoundary = ImageViewCreator.Create("Main Menu/user.png", 280, 85);
        mainMenuUserBoundary.setOpacity(0.3);
        mainMenuUserBoundary.setLayoutX(1020);
        mainMenuUserBoundary.setLayoutY(615);

        Label usernameLabel = new Label(User.getUsername());
        usernameLabel.setFont(new javafx.scene.text.Font("Arial", 50));
        usernameLabel.setTextFill(Color.BLACK);
        usernameLabel.setLayoutX(1030);
        usernameLabel.setLayoutY(625);

        mainMenuPlayButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuPlayButton.setImage(new Image("/Main Menu/play_button_off.png"));
			}
		});

        mainMenuPlayButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuPlayButton.setImage(new Image("/Main Menu/play_button_on.png"));
			}
		});

        mainMenuPlayButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(LevelMenu.getScene());
			}
		});

        mainMenuOptionsButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuOptionsButton.setImage(new Image("/Main Menu/options_button_off.png"));
			}
		});

        mainMenuOptionsButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuOptionsButton.setImage(new Image("/Main Menu/options_button_on.png"));
			}
		});

        mainMenuOptionsButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(OptionsMenu.getScene());
			}
		});

        mainMenuExitButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuExitButton.setImage(new Image("/Main Menu/exit_button_off.png"));
			}
		});

        mainMenuExitButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuExitButton.setImage(new Image("/Main Menu/exit_button_on.png"));
			}
		});

        mainMenuExitButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(ExitMenu.getScene());
			}
		});

        mainMenuUserBoundary.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuUserBoundary.setOpacity(0.3);
			}
		});

        mainMenuUserBoundary.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mainMenuUserBoundary.setOpacity(0.5);
			}
		});

        mainMenuUserBoundary.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				TextInputDialog dialog = new TextInputDialog(usernameLabel.getText());
				dialog.setTitle("Name changer");
				dialog.setHeaderText(null);
				dialog.setContentText("Please enter your name:");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent() && !result.get().isEmpty() && !result.get().contains(" ") && result.get().length() <= 8) {
					User.setUsername(result.get());
					usernameLabel.setText(result.get());
				}
			}
		});

        pane.getChildren().addAll(mainMenuPlayButton, mainMenuOptionsButton, mainMenuExitButton, mainMenuLogo, usernameLabel, mainMenuUserBoundary);

        root.getChildren().add(pane);

        scene = new Scene(root, 1280, 720);
	}

	public static Scene getScene() {
		return scene;
	}
}
