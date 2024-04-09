package svgItems;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class DesmosSVG {
	
	//This will be set to M
	public static Point penLocation = new Point(0, 0);
	/**
	 * 
	 * @param fileLocation This is the location of the file. This MUST end with .svg
	 * @throws IOException
	 */
	public static void run(String fileLocation) throws IOException {
		
		resetPenLocation();
		
		String svgCode = getSvgCode(new File (fileLocation));
		
		String[] paths = svgCode.replaceAll("\\r\\n", " ").split("<path d=\"");
		for (int i = 0; i < paths.length; i++) paths[i] = paths[i].trim().substring(0, paths[i].length()-4);
		
		for (int i = 0; i < paths.length; i++) {
			if (!paths[i].contains(">")) OrganizeCode.organizePath(paths[i]);
			if (paths[i].contains(">") && (!paths[i].contains("<?"))) {
				OrganizeCode.organizePath(paths[i].split(">")[0]);
				System.out.println(paths[i].split(">")[0]);
			}
		}
		
	}
	
	private static String getSvgCode(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	    br.close();
	    
		return sb.toString();
	}
	
	/**
	 * Resets pen location.
	 * 
	 * I don't think I actually need this b/c the first 2 points are always set as the pen location. But oh well...
	 * 
	 * You know the saying, get the barely working one first, then fix it later :P
	 */
	private static void resetPenLocation() {
		penLocation.setLocation(0, 0);
	}
}

//Formula.printFormula(new Point(0, 0), new Point(0, -36), new Point(29, -126), new Point(61, -190));