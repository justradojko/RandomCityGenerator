import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class RandomCities{
	private ArrayList<String> cityList = new ArrayList<String>();
	private ArrayList<String> randomList = new ArrayList<String>();
	private int n;
	
	
	private void loadDataFromFile(){
		try (FileReader fr = new FileReader(new File("cities.txt"))){
			String str;
			BufferedReader br = new BufferedReader(fr);
			while( (str = br.readLine()) != null  && str.length() > 0 ){
				cityList.add(str);
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
		
		//TRYING TO GRAB PART OF COMPLETE LIST SOMEWHERE FROM THE MIDDLE.
		int startingPoint;
		startingPoint = (int) (Math.random() * cityList.size());
		if (startingPoint + n > cityList.size()) {
			startingPoint = cityList.size() - n - 1;
		}
		
		randomList = new ArrayList<String>(cityList.subList(startingPoint, startingPoint + n));
		
		for (int i = 0; i < randomList.size() - (int)Math.ceil(n/5.0); i++) {
			randomList.set(i, this.getRandomTime() + " \"" +randomList.get(i) + "\"");
					
			//ASSURING THAT RANDOMLIST CONTAINS AT LEAST N/5 DULICATES
			if (i < (int)Math.ceil(n/5.0) ){
				randomList.set(randomList.size() - i-1, randomList.get(i));
			}		
		}
		Collections.shuffle(randomList);
	}
	
	private void printRandomList(){
		for (String element : randomList) {
			System.out.println(element);
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
//		cities.go(n);
	}
}
