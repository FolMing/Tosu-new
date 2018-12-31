import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewCreator {
	public static ImageView mainMenuBGCreate(double width, double height)
	{
		Image mainMenuBG = new Image("/MainMenuBG.jpg");
        ImageView mainMenuBGView = new ImageView(mainMenuBG);
        mainMenuBGView.setSmooth(true);
        mainMenuBGView.setCache(true);
        mainMenuBGView.setPreserveRatio(false);
        mainMenuBGView.setFitWidth(width);
        mainMenuBGView.setFitHeight(height);
        return mainMenuBGView;
	}
}
