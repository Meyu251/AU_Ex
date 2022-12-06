package Exe.Ex2;

import java.util.Arrays;

public class mytest {

	public static final double EPS = 0.001;

	public static final double[] ZERO = {0};

	public static void main(String[] args) {
		double[] po1 = {2, 1, -0.7, -0.02, 0.02};
//		double[] po2 = {0};
		//		double[] p3 = {16, 10, 1};
		String sp = "8x^4-3x^3+x^2+6x+7";
		//		String sp = poly(p3);
		//		System.out.println(sp);
		//
		//		//		po1[0] = 0;
		//		//		po2[0] = po1[0];
		//		//		po2[1] = po1[1];
		//		//		double[] po3 = {3, 10, -2};
		//		//
		//		//		double x1 = 1;
		//		//		double x2 = 10;
		//				double eps = 0.001;
		//		boolean ans = equals(po1, po2);
		//		
		//		System.out.println(Arrays.toString(po1));
		//		System.out.println(Arrays.toString(po2));

		double[] ans = getPolynomFromString2(sp);
		//		System.out.println(sp);
		System.out.println(Arrays.toString(ans));
		//		System.out.println("16.59002278872367");

	}








	public static double[] getPolynomFromString2(String p) {

		p = p.replaceAll("-", "+-");						// replacing "-" with "+-" so we can split the String by "+"
		if (p.startsWith("+")) {						// delete the first "+" if exist
			p = p.substring(1);
		}

		String[] splited = p.split("\\+");				// splits the String by "+"

		int arrLength = 0;

		if (splited.length == 1) {						// that means the original String contains only one number
			return new double[]{Double.parseDouble(splited[0])};
		}
		else {											// assuming we didn't get an empty String so it must encluding a x
			if (splited[0].contains("^")) {
				arrLength = (Integer.parseInt(splited[0].split("\\^")[1])) + 1;
			}
			else {
				arrLength = 2;
			}
		}

		double[] ans = new double[arrLength];

		for (String split : splited) {
			if (split.isBlank()) continue;

			if (split.contains("x")) {
				if (split.contains("^")) {
					arrLength = Integer.parseInt(split.split("\\^")[1]);
				} else {
					arrLength = 1;
				}
			} else {
				arrLength = 0;
			}
			if (split.startsWith("x")) {
				ans[arrLength] = 1;
			}
			else if (split.startsWith("-x")) {
				ans[arrLength] = -1;
			}
			else {
				ans[arrLength] = Double.parseDouble(split.split("x")[0]);
			}
		}



		return ans;
		// **************************
	}




	public static double[] add(double[] p1, double[] p2) {
		// *** add your code here ***

		double[] sum = new double[Math.max(p1.length, p2.length)];

		if (p1.length==0 || p1==null) {return p2;}
		if (p2.length==0 || p2==null) {return p1;}

		if (p1.length==p2.length) {
			for(int i=0; i<p1.length; i++) {
				sum[i] = p1[i] + p2[i];
			}
		}

		else {
			int i = 0;
			while (i<p1.length && i<p2.length) {
				sum [i] = p1[i] + p2[i];
				i++;
			}

			while (i<p1.length) {									// if p1 is the longest array
				sum[i] = p1[i];
				i++;
			}

			while (i<p2.length) {									// if p2 is the longest array
				sum[i] = p2[i];
				i++;
			}
		}

		return sum;
		// **************************
	}







	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		// *** add your code here ***

		if (p1.length != p2.length) {return false;}					// if the length doesn't equal

		for (int i = 0; i<p1.length; i++) {
			if (Math.abs(p1[i]-p2[i])>EPS) {						// compares the i location in both of polynoms
				return false;
			}
		}

		// **************************
		return ans;
	}





	public static double f(double[] poly, double x) {
		double ans = 0;
		// *** add your code here ***

		if(poly!=null) {
			for (int i = 0; i<poly.length; i++) {
				ans = ans+ poly[i]*Math.pow(x, i);
			}
		}
		// **************************
		return ans;
	}





	public static String poly(double[] poly) {
		String ans = "";
		// *** add your code here ***

		if (poly==null || poly.length==0) {return "";}

		if (poly.length==1) {
			return poly[0] + "";
		}

		for (int i = poly.length-1; i>=1; i--) {
			if (poly[i]>0) {
				ans = ans + " +" + poly[i] + "x^" + i;
			}
			if (poly[i]<0) {
				ans = ans + " " + poly[i] + "x^" + i;
			}
		}

		if (poly[poly.length-1]>0) {
			ans = ans.substring(2, ans.length());							// removing the first "+" if exists
		}
		if (poly[1]!=0) {
			ans = ans.substring(0, ans.length()-2);							// removing chars "^1"
		}

		if (poly[0]>0) {
			ans = ans + " +" + poly[0];
		}
		if (poly[0]<0) {
			ans = ans + " " + poly[0];
		}

		// **************************
		return ans;
	}





	public static double root(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		// *** add your code here ***

		double sum = 0;

		if (f(p, x1) < f(p, x2)) {
			do {

				sum = f(p, x12);

				if (Math.abs(sum)<=eps) {return x12;}

				else if (sum<0) {
					x1 = x12;
					x12 = (x12+x2)/2;
				}

				else if (sum>0) {
					x2 = x12;
					x12 = (x12+x1)/2;
				}
			}  while (Math.abs(sum)>eps);

		}

		else if (f(p, x1) > f(p, x2)) {
			do {

				sum = f(p, x12);

				if (Math.abs(sum)<=eps) {return x12;}

				else if (sum<0) {
					x2 = x12;
					x12 = (x12+x1)/2;
				}

				else if (sum>0) {
					x1 = x12;
					x12 = (x12+x2)/2;
				}
			}  while (Math.abs(sum)>eps);	
		}

		// **************************
		return x12;
	}






	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		// *** add your code here ***

		if (f(p, x1) < f(p, x2)) {

			double sum = f(p, x12);

			if (Math.abs(sum)<=eps) {return x12;}

			else if (sum<0) {
				x1 = x12;
				x12 = root_rec(p, x1, x2, eps);
			}

			else if (sum>0) {
				x2 = x12;
				x12 = root_rec(p, x1, x2, eps);
			}
		}

		else if (f(p, x1) > f(p, x2)) {

			double sum = f(p, x12);

			if (Math.abs(sum)<=eps) {return x12;}

			else if (sum<0) {
				x2 = x12;
				x12 = root_rec(p, x1, x2, eps);
			}

			else if (sum>0) {
				x1 = x12;
				x12 = root_rec(p, x1, x2, eps);
			}
		}
		// **************************
		return x12;
	}



	public static double[] derivative (double[] po) {
		// *** add your code here ***

		double[] deriv = new double[po.length-1];

		for (int i = 0; i<deriv.length; i++) {
			deriv[i] = po[i+1]*(i+1);
		}

		return deriv;
		// **************************
	}



	public static double[] mul(double[] p1, double[] p2) {
		// *** add your code here ***

		double[] ans = new double [p1.length + p2.length - 1];

		for(int i = 0; i<p1.length; i++) {
			for(int j = 0; j<p2.length; j++) {
				ans[i+j] = ans[i+j] + p1[i]*p2[j];
				//				System.out.println(Arrays.toString(ans));
			}
		} 


		// **************************
		return ans;
	}



	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		// *** add your code here ***

		if (f(p1, x1) > f(p2, x1)) {

			do {

				if ( Math.abs(f(p1, x12) - f(p2, x12)) < eps ) {return x12;}

				if (f(p1, x12) < f(p2, x12)) {
					x2 = x12;
					x12 = (x1+x2)/2;
				}

				else if (f(p1, x12) > f(p2, x12)) {
					x1 = x12;
					x12 = (x1+x2)/2;
				}

			} while (f(p1, x12) != f(p2, x12));
		}

		else if (f(p1, x1) < f(p2, x1)) {

			do {

				if ( Math.abs(f(p1, x12) - f(p2, x12)) < eps ) {return x12;}

				if (f(p1, x12) < f(p2, x12)) {
					x1 = x12;
					x12 = (x1+x2)/2;
				}

				else if (f(p1, x12) > f(p2, x12)) {
					x2 = x12;
					x12 = (x1+x2)/2;
				}

			} while (f(p1, x12) != f(p2, x12));
		}

		// **************************
		return x12;
	}



	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0;
		// *** add your code here ***

		double width = x2-x1;												// length of range to compute
		double boxWidth = width/numberOfBoxes;								// the length of every box

		for (int i=0; i<numberOfBoxes; i++) {								// loop to run over all boxes
			double xi = x1 + boxWidth * i;
			double sum1 = f(p1, xi);
			double sum2 = f(p2, xi);
			double length = Math.abs(sum1-sum2);							// the length between two polynoms
			double boxArea = boxWidth * length;								// computes the area of specific box
			ans = ans + boxArea;											// sum this area to the general area
		}

		// **************************
		return ans;
	}


	public static double area2(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0;
		// *** add your code here ***

		double width = x2-x1;												// length of range to compute
		double boxWidth = width/numberOfBoxes;								// the length of every box
		double ans1 = 0;
		double ans2 = 0;

		for (int i=1; i<numberOfBoxes; i++) {								// loop to run over all boxes
			double xi = x1 + boxWidth * i - boxWidth/2;
			double high = f(p1, xi);										// the length between two polynoms
			double boxArea = boxWidth * high;								// computes the area of specific box
			ans1 = ans + boxArea;											// sum this area to the general area
		}

		for (int i=1; i<numberOfBoxes; i++) {								// loop to run over all boxes
			double xi = x1 + boxWidth * i - boxWidth/2;
			double high = f(p2, xi);										// the length between two polynoms
			double boxArea = boxWidth * high;								// computes the area of specific box
			ans2 = ans + boxArea;											// sum this area to the general area
		}

		ans = ans1 + ans2;

		// **************************
		return ans;
	}
	
}
