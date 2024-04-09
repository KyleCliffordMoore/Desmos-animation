package svgItems;

import java.awt.Point;

public class Formula {
	private static String finalInformation = "";
	
	public static void setFinalInformation(String info) {
		finalInformation = info;
	}
	
	public static String getFinalInformation() {
		return finalInformation;
	}
	
	public static void printFormula(Point... points) {

		//Declare variables
		int numOfPoints = points.length;
		int n = numOfPoints - 1;
		int nStart = n;

		String xoutput = "", youtput = "";

		
		
		//determine x coordinates
		xoutput = xoutput + "(";
		
		for (int i = 0; i < numOfPoints; i++) 
			xoutput = xoutput + binomialCoeff(nStart, i) + "*" + "(1-t)^" + (nStart - i) + "*" + points[i].x + "*t^" + i + "+";
		
		xoutput = xoutput.substring(0, xoutput.length() - 1);
		xoutput = xoutput + ")";

		
		
		
		//determine y coordinates
		youtput = youtput + "(";
		
		for (int i = 0; i < numOfPoints; i++)
			youtput = youtput + binomialCoeff(nStart, i) + "*" + "(1-t)^" + (nStart - i) + "*" + points[i].y + "*t^" + i + "+";
		
		youtput = youtput.substring(0, youtput.length() - 1);
		youtput = youtput + ")";

		
		
		//store all information to a string
		finalInformation += "((" + xoutput + ")+" + DesmosSVG.penLocation.x + ",(" + youtput + ")+" + DesmosSVG.penLocation.y + ")\n";
	}

	private static int binomialCoeff(int n, int k) {
		int res = 1;

		if (k > n - k)
			k = n - k;

		for (int i = 0; i < k; ++i) {
			res *= (n - i);
			res /= (i + 1);
		}
		return res;
	}
}
