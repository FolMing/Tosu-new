import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
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
	private int hits = 0;
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

		ImageView background = ImageViewCreator.Create("Game screen/Background.jpg", 1300, 720);

		root.getChildren().add(background);

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
		videoMediaPlayer.setVolume(MainClass.getVolume() / 100.0);
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

		ImageView fruit1 = ImageViewCreator.Create("Game screen/Fruit.png", 128, 128);
		ImageView fruit2 = ImageViewCreator.Create("Game screen/Fruit.png", 128, 128);
		ImageView fruit3 = ImageViewCreator.Create("Game screen/Fruit.png", 128, 128);
		ImageView fruit4 = ImageViewCreator.Create("Game screen/Fruit.png", 128, 128);
		ImageView fruit5 = ImageViewCreator.Create("Game screen/Fruit.png", 128, 128);

		pane.getChildren().add(fruit1);
		fruit1.setY(-15000);
		pane.getChildren().add(fruit2);
		fruit2.setY(-15000);
		pane.getChildren().add(fruit3);
		fruit3.setY(-15000);
		pane.getChildren().add(fruit4);
		fruit4.setY(-15000);
		pane.getChildren().add(fruit5);
		fruit5.setY(-15000);

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

				switch(counter)
				{
				case 30:
				{
					int lastrandom = ThreadLocalRandom.current().nextInt(0, 1100);
					fruit1.setX((double)lastrandom);
					fruit1.setY(0);
					break;
				}
				case 60:
				{
					int lastrandom = ThreadLocalRandom.current().nextInt(0, 1100);
					fruit2.setX((double)lastrandom);
					fruit2.setY(0);
					break;
				}
				case 90:
				{
					int lastrandom = ThreadLocalRandom.current().nextInt(0, 1100);
					fruit3.setX((double)lastrandom);
					fruit3.setY(0);
					break;
				}
				case 120:
				{
					int lastrandom = ThreadLocalRandom.current().nextInt(0, 1100);
					fruit4.setX((double)lastrandom);
					fruit4.setY(0);
					break;
				}
				case 150:
				{
					int lastrandom = ThreadLocalRandom.current().nextInt(0, 1100);
					fruit5.setX((double)lastrandom);
					fruit5.setY(0);
					counter = 0;
					break;
				}
				}


				Bounds CatcherBounds = catcher.getBoundsInParent();
				Bounds Fruit1Bounds = fruit1.getBoundsInParent();
				if (CatcherBounds.intersects(Fruit1Bounds))
			    {
			    	fruit1.setY(-15000);
			    	hits++;
			    	if (combo == 0) score+=300; else score+=300*combo;
			    	combo++;
			    }
				Bounds Fruit2Bounds = fruit2.getBoundsInParent();
				if (CatcherBounds.intersects(Fruit2Bounds))
			    {
					fruit2.setY(-15000);
					hits++;
					if (combo == 0) score+=300; else score+=300*combo;
					combo++;
			    }
				Bounds Fruit3Bounds = fruit3.getBoundsInParent();
				if (CatcherBounds.intersects(Fruit3Bounds))
			    {
					fruit3.setY(-15000);
					hits++;
					if (combo == 0) score+=300; else score+=300*combo;
					combo++;
			    }
				Bounds Fruit4Bounds = fruit4.getBoundsInParent();
				if (CatcherBounds.intersects(Fruit4Bounds))
			    {
					fruit4.setY(-15000);
					hits++;
					if (combo == 0) score+=300; else score+=300*combo;
					combo++;
			    }
				Bounds Fruit5Bounds = fruit5.getBoundsInParent();
				if (CatcherBounds.intersects(Fruit5Bounds))
			    {
					fruit5.setY(-15000);
					hits++;
					if (combo == 0) score+=300; else score+=300*combo;
					combo++;
			    }

				if (Fruit1Bounds.intersects(0, 720, 1280, 0))
			    {
					fruit1.setY(-15000);
					misses++;
					combo = 0;
			    }
				if (Fruit2Bounds.intersects(0, 720, 1280, 0))
			    {
					fruit2.setY(-15000);
					misses++;
					combo = 0;
			    }
				if (Fruit3Bounds.intersects(0, 720, 1280, 0))
			    {
					fruit3.setY(-15000);
					misses++;
					combo = 0;
			    }
				if (Fruit4Bounds.intersects(0, 720, 1280, 0))
			    {
					fruit4.setY(-15000);
					misses++;
					combo = 0;
			    }
				if (Fruit5Bounds.intersects(0, 720, 1280, 0))
			    {
					fruit5.setY(-15000);
					misses++;
					combo = 0;
			    }

				if (misses == 0)
				{
					accuracy = 100.0;
				}
				else
				{
					accuracy = (double)hits/(double)(hits+misses)*100;
				}

				scoreLabel.setText("Score: " + Integer.toString(score));
				accuracyLabel.setText(format.format(accuracy) + "%");
				comboLabel.setText("Combo: " + Integer.toString(combo) + "x");

				fruit1.setY(fruit1.getY()+8);
				fruit2.setY(fruit2.getY()+8);
				fruit3.setY(fruit3.getY()+8);
				fruit4.setY(fruit4.getY()+8);
				fruit5.setY(fruit5.getY()+8);
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
