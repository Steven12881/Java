package location;

import java.util.Scanner;

import location.Location;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Enter the number of  rows and column in the array: ");
		Scanner scn = new Scanner(System.in);
		int row = scn.nextInt();
		int column = scn.nextInt();
		
		double[][] box = new double [row][column];
		
		System.out.println("Enter the array: ");
		
		for (int i=0; i < row; i++) {
			for (int j=0; j < column; j++) {
			
				double rr= scn.nextDouble();
		
				box[i][j] = rr;  
		
			}
		}
		Location location = Location.locateLargest(box);
		System.out.println("The location of the largest element " + (int)location.maxValue + " at (" + location.row + "," + location.column + ")");
	}

}
