import java.io.File;
import java.io.FilenameFilter;

public class LevelDirectories {
	private static String[] levelDirs;

	public LevelDirectories() {
		levelDirs = new File("levels").list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
	}

	public static String[] getLevelDirs() {
		return levelDirs;
	}
}
