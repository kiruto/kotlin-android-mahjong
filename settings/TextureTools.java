import com.badlogic.gdx.tools.texturepacker.TexturePacker;
public class TextureTools {
    public static void main (String[] args) throws Exception {
    	String inputDir = "input";
    	String outputDir = "output";
    	String packFileName = "tiles.png";
        TexturePacker.process(inputDir, outputDir, packFileName);
    }
}