package svgSetUpANDedgeDetection;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EdgeDetection {

	   /**
	    * This method uses openCV Canny Edge Detection to to get an image with a black background and a white trace.
	    * It then takes this file and gives it to Potrace to convert into the proper svg File and retrace the lines.
	    * 
	    * @param fileLocation Starting location of the file.
	    * @param fileExitLocation Ending location of the file. This MUST end in .BMP
	    * @param threshold1 Decrease this value for more lines and vice versa.
	    * @param threshold2 Decrease this value for more lines and vice versa.
	    * @param appentureSize Recommend keeping this on 3.
	    * @param L2gradient Recommend keeping this on false.
	    * @throws IOException Pretty spooky ngl.
	    * @throws InterruptedException InterruptedException
	    * 
	    * @apiNote Good values tend to be EdgeDetection.runEdgeDetection(loc1, loc2, 50, 150, 3, false);
	    */
	   public static void runEdgeDetectionSETUP(String fileLocation, String fileExitLocation, int threshold1, int threshold2, int appentureSize, boolean L2gradient) throws IOException, InterruptedException {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		   //Core.NATIVE_LIBRARY_NAME
		   Mat color = Imgcodecs.imread(fileLocation);
		   
		   Mat gray = new Mat();
		   Mat draw = new Mat();
		   Mat wide = new Mat();
		   
		   System.out.println(fileLocation);
		   System.out.println(fileExitLocation);
		   
		   Imgproc.cvtColor(color, gray, Imgproc.COLOR_BGR2GRAY);
		   Imgproc.Canny(gray, wide, threshold1, threshold2, appentureSize, L2gradient); //gray, wide, 50, 150, 3, false
	
		   wide.convertTo(draw, CvType.CV_8U);
		   
		   if (Imgcodecs.imwrite(fileExitLocation, draw)) System.out.println("Edge is detected....");
		   
		   Thread.sleep(1000);
		   
		   Runtime.getRuntime().exec("cmd.exe /c potrace --svg " + fileExitLocation);
		   
	   }
}
