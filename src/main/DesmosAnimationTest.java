package main;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import keyboardItems.KeyBoardThings;
import svgItems.DesmosSVG;
import svgItems.Formula;
import svgSetUpANDedgeDetection.EdgeDetection;

public class DesmosAnimationTest {
	
	private static final String FINALFILESTARTLOCATION = "C:\\Users\\Kyle\\Desktop\\frames";
	public static final String fileMEDIANLocation_WithOutDot = "C:\\Users\\Kyle\\Desktop\\thing\\testFile";
	private static final String FINALFILEENDLOCATION = "C:\\Users\\Kyle\\Desktop\\newFrames";
	
	
	private static String fileStartLocation;
	private static String fileEndLocation;
	
	
	public static final int 
			x = 500, 
			y = Toolkit.getDefaultToolkit().getScreenSize().height/2,
			width = Toolkit.getDefaultToolkit().getScreenSize().width - 500,
			height = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 300;
	
	/*
	 * copy this into JavaScript Console:
	 * 
	 state=Calc.getState();
	 for (i=0;i<state.expressions.list.length;i++) {
     	state.expressions.list[i].color="#FF00FF"
	}
	Calc.setState(state);
	
	to change the color of the lines
	 */
	
	public static void main(String[] args) throws Exception {
		
		//Phase 2 - a
		
		//Robot robot = new Robot();
		
		//for (int i = 3503; i < 7777; i++) run(robot, i);
		
		/**/
		// 50 150 3 false
		EdgeDetection.runEdgeDetectionSETUP("C:\\Users\\Kyle\\Desktop\\tempImg.jpg", "C:\\Users\\Kyle\\Desktop\\thing\\tempImg.BMP", 250, 150, 3, false);
		Thread.sleep(1000);
		DesmosSVG.run("C:\\Users\\Kyle\\Desktop\\thing\\tempImg.svg");
		System.out.println(Formula.getFinalInformation());
		
		StringSelection stringSelection = new StringSelection(Formula.getFinalInformation());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		//Thread.sleep(3000);
		clipboard.setContents(stringSelection, null);
				
		/**/
	}
	
	
	/**
	 * 
	 * @param filePath Takes in the file path before the file path has "\frame"...
	 * @param fileType The file type should include the "." when being passed in; The required file type should be ".png" by default.
	 * @param index This will be the value added at the end once all the zeros have been placed; This should be the current frame that the computer is trying to prepare.
	 * @param numOfDigits Number should correspond to the number of digits after "\frame".
	 * @return A String of the completed file path; The method will add "\frame[number of zeros][index]" to the path.
	 */
	private static String setNamesFrameNumber(String filePath, String fileType, int index, int numOfDigits) {
		filePath += "\\frame";
		
		for (int i = 0; i < numOfDigits - ("" + index).length(); i++) filePath += "0";
		return filePath += "" + index + fileType;
	}
	
	/**
	 * 
	 * @param compImage Image to compare the current screenshot to check for a change.
	 * @param screenRectangle The size of and area that will be checked for a change.
	 * @param robot pass in current robot.
	 * @throws InterruptedException Does contain a Thread.sleep(); method.
	 */
	private static void waitForFramesChange(BufferedImage compImage, Rectangle screenRectangle, Robot robot) throws InterruptedException {
		
		BufferedImage image;
		
		while (true) {
			Thread.sleep(500);
			image = robot.createScreenCapture(screenRectangle);
			
			for (int i = 0; i < image.getWidth(); i++) 
				for (int j = 0; j < image.getHeight(); j++) 
					if (image.getRGB(i, j) != compImage.getRGB(i, j)) return;
			
		}
	}
	
	/**
	 * 
	 * Closes the chrome window; Sends a ENTER as well because Desmos will ask whether you are sure you would like to close the window.
	 * 
	 * @param robot robot
	 * @throws InterruptedException contains thread.sleep(); methods
	 */
	private static void closeChromeWindow(Robot robot) throws InterruptedException {
		robot.keyPress(KeyEvent.VK_ALT); robot.keyPress(KeyEvent.VK_F4);
		Thread.sleep(100);
		robot.keyRelease(KeyEvent.VK_ALT); robot.keyRelease(KeyEvent.VK_F4);
		Thread.sleep(250);
		robot.keyPress(KeyEvent.VK_ENTER); robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	public static void run(Robot robot, int index) throws IOException, InterruptedException, AWTException {
		
		Formula.setFinalInformation("");
		Thread.sleep(1000);
		Runtime.getRuntime().exec("cmd.exe /c start https://www.desmos.com/calculator");
		Thread.sleep(4000);
		KeyBoardThings.changeDimensions(robot, "9600", "7200");
		
		fileStartLocation = setNamesFrameNumber(FINALFILESTARTLOCATION, ".png", index, 6);
		fileEndLocation = setNamesFrameNumber(FINALFILEENDLOCATION, ".png", index, 6);
		
		EdgeDetection.runEdgeDetectionSETUP(fileStartLocation, fileMEDIANLocation_WithOutDot + ".BMP", 0, 20, 3, false);
		Thread.sleep(200);

		//Phase 3
		DesmosSVG.run(fileMEDIANLocation_WithOutDot + ".svg");
		
		
		// Phase 2 - b
		Rectangle screenRectangle = new Rectangle(x, y, width, height);
		BufferedImage compImage = robot.createScreenCapture(screenRectangle);
		
		for (int i = 0; i < 19; i++) {robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB); Thread.sleep(400);}
		KeyBoardThings.copyAndPaste(robot, Formula.getFinalInformation() + "(t,10000t)");
		Thread.sleep(200);
		
		//Phase 4
		waitForFramesChange(compImage, screenRectangle, robot);
		
		Rectangle fscreenRectangle = new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		BufferedImage fImage = robot.createScreenCapture(fscreenRectangle);
		ImageIO.write(fImage, "png", new File(fileEndLocation));
		
		Thread.sleep(1000);
		
		closeChromeWindow(robot);
		
		Thread.sleep(1000);
		
	}	
}

//Stuffies
