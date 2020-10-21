import java.util.*;

class BestFirst { 
	static class State {
		private Ilayout layout; //Board layout
		private State father;
		private double g;
		
		public State(Ilayout l, State n) { //method used to add a new state
			layout = l; 
			father = n;
			if (father!=null)
				g = father.g + l.getG(); 
			else g = 0.0; //in this case we are at the "root" father
		}
		
		public String toString() { 
			return layout.toString(); 
		} 
		
		public double getG() {
			return g;
		}
	}
	
	protected Queue<State> abertos; 
	private List<State> fechados; 
	private State actual;
	private Ilayout objective;
	
	/**
	 * @param n is the input state for which children should be generated
	 * @return a List of all of the children states of the input state n 
	 */
	final private List<State> sucessores(State n) 
	{ 
		List<State> sucs = new ArrayList<>(); 
		List<Ilayout> children = n.layout.children(); 
		for(Ilayout e: children) 
		{
			if (n.father == null || !e.equals(n.father.layout))
			{ 
				State nn = new State(e, n);
				sucs.add(nn);
			} 
		}
		return sucs; 
	}
	
	/**
	 * Solve.
	 *
	 * @param s the s
	 * @param goal the goal
	 * @return the iterator
	 */
	final public Iterator<State> solve(Ilayout s, Ilayout goal) 
	{ 
		objective = goal;
		Queue<State> abertos = new PriorityQueue<>(10,
						(s1, s2) -> (int) Math.signum(s1.getG()-s2.getG())); 
		List<State> fechados = new ArrayList<>();
		abertos.add(new State(s, null)); 
		List<State> sucs;
		while(true) //This loop will continue until one of the following occurs: a solution is found, the abertos queue is empty or no more memory
		{
			if(abertos.isEmpty())
				return null;
			actual = abertos.poll();
			if(actual.layout.isGoal(objective)) //objective found
			{
				List<State> childList = new ArrayList<>();
				childList.add(actual); // the solution state will be the last state to come out of the stack when printing
				while(actual.father != null)
				{
					actual = actual.father;
					childList.add(actual);
				}
				Collections.reverse(childList);
				return childList.iterator();
			} else //objective not found yet so we will expand 
			{
				sucs = sucessores(actual);
				fechados.add(actual);
				Iterator<State> suc = sucs.iterator();
				while(suc.hasNext())
				{
					State i = suc.next();
					if(!fechados.contains(i))
						abertos.add(i);
				}
			}
		}
	} 
}