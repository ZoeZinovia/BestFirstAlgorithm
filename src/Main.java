import java.util.Iterator;
import java.util.Scanner;
//import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws Exception{
		
//		long startTime = System.nanoTime();		
		Scanner sc = new Scanner(System.in);
		
		BestFirst s = new BestFirst();
		Iterator<BestFirst.State> it = s.solve(new BlockWorld(sc.nextLine()), new BlockWorld(sc.nextLine())); //we read the initial board and the goal board then call the solve function from the BestFirst class. We return an iterator that iterates through the list of states from the initial board to the goal board
		
		if(it==null) System.out.println("No solution was found");
		else {
			while(it.hasNext()) {
				BestFirst.State i = it.next();
				System.out.println(i);
				if(!it.hasNext())
					System.out.println((int) i.getG());
			}
		}
		sc.close();		
//		long endTime = System.nanoTime();
//		long time = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
//		System.out.println(time);
	}	
}
