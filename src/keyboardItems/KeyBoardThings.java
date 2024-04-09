package keyboardItems;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardThings {
	/**
	 * This method will run the neccesary key clicks to change the size of the Desmos graph.
	 * 
	 * @param robot Instance of robot - for the keyPresses
	 * @param x This is the width of the graphing window. As a String to be copy and pasted!
	 * @param y This is the height of the graphing window. As a String to be copy and pasted!
	 * @throws InterruptedException Sleeps for .3s
	 */
	public static void changeDimensions(Robot robot, String x, String y) throws InterruptedException {

		robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(300);
		
		for (int i = 0; i < 12; i++) {robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB);}
		copyAndPaste(robot, x);
		
		Thread.sleep(150);
		
		for (int i = 0; i < 5; i++) {robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB);}
		copyAndPaste(robot, y);
		
		Thread.sleep(150);
		
		robot.keyPress(KeyEvent.VK_ESCAPE); robot.keyRelease(KeyEvent.VK_ESCAPE);
		
	}
	
	/**
	 * This method will copy and paste using the robot use the keyboard.
	 * 
	 * @param robot The instance of the robot - for the keyPresses
	 * @param info Information to be pasted
	 * @throws InterruptedException 
	 */
	public static void copyAndPaste(Robot robot, String info) throws InterruptedException {
		StringSelection stringSelection = new StringSelection(info);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		//Press
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Release
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	}
}
