import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewCreator {
	public static ImageView Create(String url, double width, double height) {
		Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
	}
}
