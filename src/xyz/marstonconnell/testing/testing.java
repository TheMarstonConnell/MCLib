package xyz.marstonconnell.testing;
import xyz.marstonconnell.graphics.GraphicsFrame;
import xyz.marstonconnell.util.WeightedChooser;

public class testing {

	public static void main(String[] args) {
//		addonframe g = new addonframe();
		
		WeightedChooser<String> wc = new WeightedChooser<>();
		
		wc.addChoice("wow", 56);
		wc.addChoice("epic", 25);
		wc.addChoice("amazing", 34);
		wc.addChoice("mister krabs", 53);
		wc.addChoice("holy cow", 10);
		
		
		System.out.println(wc.getRandomObject());
		
	}

}
