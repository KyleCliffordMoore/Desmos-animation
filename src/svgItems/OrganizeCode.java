package svgItems;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class OrganizeCode {
	public static void organizePath(String path) {

		ArrayList<String> commandOrder = new ArrayList<String>();

		// BREAK INTO DIFFERENT COMMANDS
		ArrayList<String> commands = new ArrayList<String>(
				Arrays.asList(path.split("(?=[qwertyuiopasdfghjkklzxcvbnmQWERTYUIOOPASDFGHJKLZXCVBNM])")));

		// PreSetup
		for (int i = commands.size() - 1; i >= 0; i--)
			if (commands.get(i).contains("z"))
				commands.remove(i);
		DesmosSVG.penLocation.setLocation(Integer.parseInt(commands.get(0).split(" ")[0].replaceAll("M", "")),
				Integer.parseInt(commands.get(0).split(" ")[1]));
		commands.remove(0);

		// Determine the command
		for (int i = 0; i < commands.size(); i++) {

			if (commands.get(i).contains("c"))
				commandOrder.add("c");
			else if (commands.get(i).contains("l"))
				commandOrder.add("l");
			else if (commands.get(i).contains("m"))
				commandOrder.add("m");
			else
				System.err.println("Some other letter is here!");

			// remove first character
			commands.set(i, commands.get(i).strip());
			commands.set(i, commands.get(i).substring(1));
		}

		// **********************************************************************************************************************

		for (int i = 0; i < commandOrder.size(); i++) {

			// determine what it is
			if (commandOrder.get(i).equals("c")) formatC(commands.get(i));
			if (commandOrder.get(i).equals("l")) formatL(commands.get(i));
			if (commandOrder.get(i).equals("m")) formatM(commands.get(i));
		}

	} // End of Method

	private static void formatM(String information) {
		DesmosSVG.penLocation.setLocation(
				DesmosSVG.penLocation.x + Integer.parseInt(information.split(" ")[0]),
				DesmosSVG.penLocation.y + Integer.parseInt(information.split(" ")[1]));
	}
	
	private static void formatL(String information) {
		String[] dividedup = information.split(" ");

		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();

		for (int i = -1; i < dividedup.length; i += 2) {
			if (i > 0) {
				x.add(Integer.parseInt(dividedup[i - 1]));
				y.add(Integer.parseInt(dividedup[i]));

			}
		}
		
		// X
		ArrayList<ArrayList<Integer>> setsx = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < x.size(); i++) {
//			System.out.println(x.get(i) + " " + y.get(i));
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(0);
			temp.add(x.get(i));

			setsx.add(temp);
		}
		
		for (int i = 0; i < setsx.size(); i++) {
			for (int j = 0; j < setsx.get(i).size(); j++) {
				if (i > 0) {
					int num = setsx.get(i - 1).get(1); // last one in index
					setsx.get(i).set(j, setsx.get(i).get(j) + num);
				}
//						System.out.println(setsx.get(i).get(j));
			}
//					System.out.println("======");
		}
		
		
//		System.out.println("=========================================================================");

		// Y
		ArrayList<ArrayList<Integer>> setsy = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < x.size(); i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(0);
			temp.add(y.get(i));

			setsy.add(temp);
		}

		for (int i = 0; i < setsy.size(); i++) {
			for (int j = 0; j < setsy.get(i).size(); j++) {
				if (i > 0) {
					int num = setsy.get(i - 1).get(1); // last one in index
					setsy.get(i).set(j, setsy.get(i).get(j) + num);
				}
//						System.out.println(setsy.get(i).get(j));
			}
//					System.out.println("======");
		}
		
		
		
		for (int i = 0; i < setsx.size(); i++) {
//			System.out.println(setsx.get(i).get(0) + " " + setsy.get(i).get(0));
//			System.out.println(setsx.get(i).get(1) + " " + setsy.get(i).get(1));
//			System.out.println(setsx.get(i).get(2) + " " + setsy.get(i).get(2));
//			System.out.println(setsx.get(i).get(3) + " " + setsy.get(i).get(3));
			
			Point p0 = new Point(setsx.get(i).get(0), setsy.get(i).get(0));
			Point p1= new Point(setsx.get(i).get(1), setsy.get(i).get(1));
			
			Formula.printFormula(p0, p1);
			
			if (i == setsx.size()-1) {
				DesmosSVG.penLocation.setLocation(p1.x + DesmosSVG.penLocation.x, p1.y + DesmosSVG.penLocation.y);
//				System.out.println(DesmosSVG.penLocation.x + ", " + DesmosSVG.penLocation.y);
			}
			
		}
		
	}
	
	private static void formatC(String information) {
		String[] dividedup = information.split(" ");

		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();

		for (int i = -1; i < dividedup.length; i += 2) {
			if (i > 0) {
				x.add(Integer.parseInt(dividedup[i - 1]));
				y.add(Integer.parseInt(dividedup[i]));

			}
		}

		
		
		// X
		ArrayList<ArrayList<Integer>> setsx = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < x.size() - 2; i += 3) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(0);
			temp.add(x.get(i));
			temp.add(x.get(i + 1));
			temp.add(x.get(i + 2));

			setsx.add(temp);
		}

		for (int i = 0; i < setsx.size(); i++) {
			for (int j = 0; j < setsx.get(i).size(); j++) {
				if (i > 0) {
					int num = setsx.get(i - 1).get(3); // last one in index
					setsx.get(i).set(j, setsx.get(i).get(j) + num);
				}
//						System.out.println(setsx.get(i).get(j));
			}
//					System.out.println("======");
		}

//		System.out.println("=========================================================================");

		// Y
		ArrayList<ArrayList<Integer>> setsy = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < x.size() - 2; i += 3) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(0);
			temp.add(y.get(i));
			temp.add(y.get(i + 1));
			temp.add(y.get(i + 2));

			setsy.add(temp);
		}

		for (int i = 0; i < setsy.size(); i++) {
			for (int j = 0; j < setsy.get(i).size(); j++) {
				if (i > 0) {
					int num = setsy.get(i - 1).get(3); // last one in index
					setsy.get(i).set(j, setsy.get(i).get(j) + num);
				}
//						System.out.println(setsy.get(i).get(j));
			}
//					System.out.println("======");
		}
		
		
		for (int i = 0; i < setsx.size(); i++) {
//			System.out.println(setsx.get(i).get(0) + " " + setsy.get(i).get(0));
//			System.out.println(setsx.get(i).get(1) + " " + setsy.get(i).get(1));
//			System.out.println(setsx.get(i).get(2) + " " + setsy.get(i).get(2));
//			System.out.println(setsx.get(i).get(3) + " " + setsy.get(i).get(3));
			
			Point p0 = new Point(setsx.get(i).get(0), setsy.get(i).get(0));
			Point p1= new Point(setsx.get(i).get(1), setsy.get(i).get(1));
			Point p2 = new Point(setsx.get(i).get(2), setsy.get(i).get(2));
			Point p3 = new Point(setsx.get(i).get(3), setsy.get(i).get(3));
			
			Formula.printFormula(p0, p1, p2, p3);
			
			if (i == setsx.size()-1) {
				DesmosSVG.penLocation.setLocation(p3.x + DesmosSVG.penLocation.x, p3.y + DesmosSVG.penLocation.y);
//				System.out.println(DesmosSVG.penLocation.x + ", " + DesmosSVG.penLocation.y);
			}
			
		}
		

	} // End of method
}
