import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class OptionsMenu {
	private static Scene scene;

	public OptionsMenu() {
		StackPane root = new StackPane();

		ImageView background = ImageViewCreator.Create("Options Menu/Background.jpg", 1300, 740);

		root.getChildren().add(background);

		Pane pane = new Pane();

		ImageView tick = ImageViewCreator.Create("Options Menu/ticked.png", 93, 93);
		tick.setLayoutX(100);
		tick.setLayoutY(100);

		Label videoLabel = new Label("Video");
		videoLabel.setFont(new javafx.scene.text.Font("Arial", 93));
		videoLabel.setTextFill(Color.WHITE);
		videoLabel.setLayoutX(200);
		videoLabel.setLayoutY(95);

		ImageView backButton = ImageViewCreator.Create("Options Menu/back_off.png", 420, 70);
		backButton.setLayoutX(-100);
		backButton.setLayoutY(623);

		Slider volumeSlider = new Slider(0, 100, MainClass.getVolume());
		volumeSlider.setLayoutX(70);
		volumeSlider.setLayoutY(280);

		Label volumeSliderLabel = new Label(String.valueOf(MainClass.getVolume()));
		volumeSliderLabel.setFont(new javafx.scene.text.Font("Arial", 70));
		volumeSliderLabel.setTextFill(Color.WHITE);
		volumeSliderLabel.setLayoutX(80);
		volumeSliderLabel.setLayoutY(300);

		Label volumeLabel = new Label("Volume");
		volumeLabel.setFont(new javafx.scene.text.Font("Arial", 93));
		volumeLabel.setTextFill(Color.WHITE);
		volumeLabel.setLayoutX(220);
		volumeLabel.setLayoutY(270);

		tick.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (MainClass.getVideoEnabled()) {
					tick.setImage(new Image("Options Menu/unticked.png"));
					MainClass.setVideoEnabled(false);
				}
				else {
					tick.setImage(new Image("Options Menu/ticked.png"));
					MainClass.setVideoEnabled(true);
				}
			}
		});

		backButton.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setImage(new Image("Options Menu/back_off.png"));
			}
		});

		backButton.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setImage(new Image("Options Menu/back_on.png"));
			}
		});

		backButton.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MainClass.getPrimaryStage().setScene(MainMenu.getScene());
			}
		});

		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
	         @Override
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	        	 MainClass.setVolume((int) Math.round((double) newValue));
	        	 volumeSliderLabel.setText(String.valueOf(MainClass.getVolume()));
	        }
	    });

		pane.getChildren().addAll(tick, videoLabel, backButton, volumeLabel, volumeSliderLabel, volumeSlider);

		root.getChildren().add(pane);

		scene = new Scene(root, 1280, 720);
	}

	public static Scene getScene() {
		return scene;
	}
}
