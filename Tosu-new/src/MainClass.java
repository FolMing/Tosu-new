import javafx.stage.Stage;

public class MainClass {
	private static Stage primaryStage;
	private static int volume = 100;
	private static boolean videoEnabled = true;

	public MainClass() {
		primaryStage = new Stage();

		primaryStage.setTitle("Tosu!");
		primaryStage.setResizable(false);
		primaryStage.setWidth(1280);
		primaryStage.setHeight(720);

		new LevelDirectories();
		new MainMenu();
		new ExitMenu();
		new OptionsMenu();
		new LevelMenu();


		primaryStage.setScene(MainMenu.getScene());
		primaryStage.show();
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static int getVolume() {
		return volume;
	}

	public static void setVolume(int newVolume) {
		if (newVolume >= 0 && newVolume <= 100) volume = newVolume;
	}

	public static boolean getVideoEnabled() {
		return videoEnabled;
	}

	public static void setVideoEnabled(boolean newVideoEnabled) {
		videoEnabled = newVideoEnabled;
	}
}
