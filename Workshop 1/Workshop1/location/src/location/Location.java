package location;

//import java.util.Scanner;

public class Location {

	int row;
	int column;
	double maxValue;
	
	public static Location locateLargest(double[][] a){
		Location location = new Location();
		
		for (int row = 0; row < a.length; row++) {
			for (int column = 0; column < a[row].length; column++) {
				if(a[row][column] > location.maxValue)
				{
					location.maxValue = a[row][column];
					location.row = row;
					location.column = column;
				}
			   }
			}
		return location;
		
	}
	
	Location(){
		row = 0;
		column = 0;
		maxValue = 0;
	}
}