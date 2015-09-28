import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class RandomCities {
	private ArrayList<String> cityList = new ArrayList<String>();
	private ArrayList<String> randomList = new ArrayList<String>();
	private static final int NUMBER_OF_CITIES= 1500;
	private int n;
	
	
	private void loadDataFromFile(){
		try{
			FileReader fr = new FileReader(new File("cities.txt"));
			BufferedReader br = new BufferedReader(fr);
			for (int i = 0; i < NUMBER_OF_CITIES; i++) {
				cityList.add(br.readLine());
			}
			Collections.shuffle(cityList);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private String getRandomTime(){
		Random random = new Random();
		Time time = new Time(random.nextLong());
		return time.toString().substring(0, 5);
	}
	
	
	private void makeRandomValidList(){
		int startingPoint;
		startingPoint = (int) (Math.random() * NUMBER_OF_CITIES);
		if (startingPoint + n > NUMBER_OF_CITIES) {
			startingPoint -= (startingPoint + n - NUMBER_OF_CITIES);
		} 
		
		for (int i = 0; i < n; i++) {
			randomList.add(this.getRandomTime() + " \"" +cityList.get( startingPoint + i) + "\"");
					
			if (i <= n/5){
				randomList.add(this.getRandomTime() + " \"" +cityList.get(startingPoint + i) + "\"");
			}		
		}
		Collections.shuffle(randomList);
	}
	
	private void printRandomList(){
		for (int i = 0; i < n; i++) {
			System.out.println(randomList.get(i));
		}		
	}
	
	public void go(int input){
		n = input;
		if ( (n >= 5) && (n <= 1000 )){
			this.loadDataFromFile();
			this.makeRandomValidList();
			this.printRandomList();
		} else {
			System.out.format("Invalid value of N = %d \n", input);
		}
	}
	
	
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Desired number of cities: ");
//		int n = scanner.nextInt();
//		scanner.close();
		
		RandomCities cities = new RandomCities();
		cities.go(Integer.parseInt(args[0]));		
	}
}
