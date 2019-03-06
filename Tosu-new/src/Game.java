import java.text.DecimalFormat;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class Game {
	private int combo = 0;
	private int misses = 0;
	private double accuracy = 100;
	private int score = 0;
	int counter = 0;
	DecimalFormat format = new DecimalFormat("#.##");

	public Game(String level) {
		StackPane root = new StackPane();

		Pane pane = new Pane();

		Label comboLabel = new Label(String.valueOf(combo));
		comboLabel.setFont(new javafx.scene.text.Font("Arial", 70));
		comboLabel.setTextFill(Color.WHITE);
		comboLabel.setLayoutX(0);
		comboLabel.setLayoutY(615);

		Label accuracyLabel = new Label(format.format(accuracy));
		accuracyLabel.setFont(new javafx.scene.text.Font("Arial", 70));
		accuracyLabel.setTextFill(Color.WHITE);
		accuracyLabel.setLayoutX(1050);
		accuracyLabel.setLayoutY(0);

		Label scoreLabel = new Label(String.valueOf(score));
		scoreLabel.setFont(new javafx.scene.text.Font("Arial", 70));
		scoreLabel.setTextFill(Color.WHITE);
		scoreLabel.setLayoutX(0);
		scoreLabel.setLayoutY(0);

		ImageView catcher = ImageViewCreator.Create("Game screen/Catcher.png", 270, 180);
		catcher.setLayoutX(500);
		catcher.setLayoutY(550);

		String curDir = System.getProperty("user.dir").replace("\\", "/");

		Media videoMedia = null;

		try {
			videoMedia = new Media("file:///" + curDir + "/levels/" + level + "/video.mp4");
		}
		catch (Exception e) {
			Alert TosuDirectWarning = new Alert(AlertType.WARNING);
			TosuDirectWarning.setTitle("Oops!");
			TosuDirectWarning.setContentText("Couldn't load video.mp4");
			TosuDirectWarning.showAndWait();
			return;
		}

		MediaPlayer videoMediaPlayer = new MediaPlayer(videoMedia);
		MediaView videoMediaView = new MediaView(videoMediaPlayer);
		videoMediaView.setPreserveRatio(true);
		videoMediaView.setFitHeight(720);

		if (MainClass.getVideoEnabled()) {
			videoMediaView.setOpacity(1);
		}
		else {
			videoMediaView.setOpacity(0);
		}

		Scene gameScene = new Scene(root, 1280, 720);

		HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

		gameScene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            {
                currentlyActiveKeys.put(codeString, true);
            }
		});

		gameScene.setOnKeyReleased(event ->
        	currentlyActiveKeys.remove(event.getCode().toString())
		);

		AnimationTimer gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				counter++;
				if (currentlyActiveKeys.containsKey("RIGHT"))
				{
					if (currentlyActiveKeys.containsKey("SHIFT"))
					{
						catcher.setX(catcher.getX()+30);
					}
					else
					{
						catcher.setX(catcher.getX()+15);
					}
					if (catcher.getX() > 450)
					{
						catcher.setX(450);
					}
				}
				if (currentlyActiveKeys.containsKey("LEFT"))
				{
					if (currentlyActiveKeys.containsKey("SHIFT"))
					{
						catcher.setX(catcher.getX()-30);
					}
					else
					{
						catcher.setX(catcher.getX()-15);
					}
					if (catcher.getX() < -450)
					{
						catcher.setX(-450);
					}
				}
			}
		};

		videoMediaPlayer.setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	        	gameTimer.stop();
	        	getResults();
	        }
		});

		pane.getChildren().addAll(scoreLabel, comboLabel, accuracyLabel, catcher);

		root.getChildren().addAll(videoMediaView, pane);

		MainClass.getPrimaryStage().setScene(gameScene);

		gameTimer.start();
		videoMediaPlayer.play();
	}

	private void getResults() {
		StackPane root = new StackPane();

		ImageView background = ImageViewCreator.Create("Result screen/Background.jpg", 1300, 740);

		root.getChildren().add(background);

		Pane pane = new Pane();

		Label accLabel = new Label("Accuracy: " + format.format(accuracy));

		Label missesLabel = new Label("Misses: " + misses);

		Label scoreLabel = new Label("Score: " + score);

		pane.getChildren().addAll(accLabel, missesLabel, scoreLabel);

		root.getChildren().add(pane);
		MainClass.getPrimaryStage().setScene(new Scene(root, 1280, 720));
	}
}
