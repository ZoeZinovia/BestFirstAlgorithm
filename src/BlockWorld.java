import java.util.*;

public class BlockWorld implements Ilayout {

	private List<Stack<Object>> blockWorld;
	
	/**
	 * Instantiates a new block world.
	 */
	public BlockWorld() {
		blockWorld = new ArrayList<>();
	}
	
	/**
	 * Instantiates a new block world.
	 *
	 * @param str the str
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public BlockWorld(String str) throws IllegalArgumentException{
		blockWorld = new ArrayList<>();
		String[] split = str.split(" ");
		for(String s: split){
			Stack<Object> temp = new Stack<>();
			char[] charArray = s.toCharArray();
			for(char c: charArray) {
				temp.push(c);
			}
			blockWorld.add(temp);
		}
	}
	
	/**
	 * Children.
	 *
	 * @return the list
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Override
	public List<Ilayout> children() throws IllegalArgumentException {
		List<Ilayout> children = new ArrayList<>();
		if(this.blockWorld.size() == 1) //only 1 stack therefore don't need to loop through anything
		{
			children.add(this.moveBlock(0, 0, "newStack"));
		}
		for(int i = 0; i < this.blockWorld.size(); i++)
		{
			Stack<Object> actual = this.blockWorld.get(i);
			if(actual.isEmpty()) throw new IllegalArgumentException("An empty stack is being accessed.");
			for(int j = 0; j < this.blockWorld.size(); j++)
			{
				if(!actual.equals(this.blockWorld.get(j))) 
				{
					if(actual.size() == 1)
						children.add(this.moveBlock(i, j, "singleBlockStack"));
					else 
					{
						children.add(this.moveBlock(i, j, "normalStack"));
						if(j == this.blockWorld.size() - 1) //Need to add a new stack with just this block
							children.add(this.moveBlock(i, j, "newStack"));
					}
				}
			}
		}
		return children;
	}
	
	/**
	 * Move block.
	 *
	 * @param i the i
	 * @param j the j
	 * @param s the s
	 * @return the block world
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	//bool is true when stack at list(i) needs to be removed
	public BlockWorld moveBlock(int i, int j, String s) throws IllegalArgumentException{
		BlockWorld bwCopy = null;
		if(s == "normalStack") {
			bwCopy = this.copyOf();
			char block = (char) bwCopy.blockWorld.get(i).pop();
			bwCopy.blockWorld.get(j).push(block);
		} else if(s == "singleBlockStack") {
			bwCopy = this.moveBlock(i, j, "normalStack");
			bwCopy.blockWorld.remove(i);
		} else if(s == "newStack"){
			bwCopy = this.copyOf();
			char block = (char) bwCopy.blockWorld.get(i).pop();
			Stack<Object> tempStack = new Stack<>();
			tempStack.push(block);
			bwCopy.blockWorld.add(tempStack);
		} else {
			throw new IllegalArgumentException("moveBlock string did not match with any case");
		}
		return bwCopy;
	}

	/**
	 * Checks if is goal.
	 *
	 * @param l the l
	 * @return true, if is goal
	 */
	@Override
	public boolean isGoal(Ilayout l) 
	{
		return this.equals(l);
	}

	/**
	 * Gets the g.
	 *
	 * @return the g
	 */
	@Override
	public double getG() 
	{
		return 1.00;
	}
	
	/**
	 * Copy of.
	 *
	 * @return the block world
	 */
	public BlockWorld copyOf() 
	{
		String result = "";
		Iterator<Stack<Object>> listIt = this.blockWorld.iterator();
		while(listIt.hasNext()) 
		{
			Stack<Object> s = listIt.next();
			Iterator<Object> charIt = s.iterator();
			while(charIt.hasNext()) 
			{
				char c = (char) charIt.next();
				result += c; 
			}
			if(listIt.hasNext())
				result += " ";
		}
		BlockWorld copy = new BlockWorld(result);
		return copy;
	}
	
	
	/**
	 * Checks if one board is equal to another
	 * 
	 * @param other the board
	 */
	public boolean equals(Object other) 
	{
		if (other == this ) return true;
		if (other == null ) return false;
		if (getClass() != other.getClass()) return false;
		boolean result = true;
		BlockWorld that = (BlockWorld) other;
		Iterator<Stack<Object>> listItThis = this.blockWorld.iterator();
		while(listItThis.hasNext())
		{
			Stack<Object> thisStack = listItThis.next();
			if(!that.blockWorld.contains(thisStack)) 
			{
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		String result = "";
		Iterator<Stack<Object>> listIt = blockWorld.iterator();
		while(listIt.hasNext()) 
		{
			Stack<Object> s = listIt.next();
			Iterator<Object> charIt = s.iterator();
			result += "[";
			while(charIt.hasNext()) 
			{
				char c = (char) charIt.next();
				result += charIt.hasNext()? c + ", " : c; 
			}
			result += "]" + System.lineSeparator();
		}
		return result;
	}

}
