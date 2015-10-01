import java.io.BufferedReader;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomCities{
	private ArrayList<String> cityList = new ArrayList<String>();
	private ArrayList<String> randomList = new ArrayList<String>();
	private int n;
	
	
	private void loadDataFromFile(){
		try (FileReader fr = new FileReader(new File("cities.txt")); BufferedReader br = new BufferedReader(fr)){
			String str;			
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
		
		randomList = new ArrayList<String>(cityList.subList(startingPoint, startingPoint + n - (int)Math.ceil(n/5.0)));
		randomList.addAll(randomList.subList(0, (int)Math.ceil(n/5.0)));

		Collections.shuffle(randomList);
	}
	
	private void printRandomList(){
		for (String element : randomList) {
			System.out.println(this.getRandomTime()  + " \"" + element + "\"");
		}
	}
	
	private void printRandomListToFile(){
		try(FileWriter writer = new FileWriter("cities1000.txt")){
			for (String element : randomList) {
				writer.write(this.getRandomTime()  + " \"" + element + "\" \n");
			}	
			System.out.println("File is created");
		} catch (Exception c){
			c.printStackTrace();
		}
	}
	
	public void go(int input){
		n = input;
		if ( (n >= 5) && (n <= 1000 )){
			this.loadDataFromFile();
			this.makeRandomValidList();
			this.printRandomListToFile();
		} else {
			System.out.format("Invalid value of N = %d \n", input);
		}
	}
	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		RandomCities cities = new RandomCities();
		cities.go(Integer.parseInt(args[0]));
		
		long end = System.currentTimeMillis();
		System.out.println("Time needed: " + (end - start) + "ms");
		
	}
}
