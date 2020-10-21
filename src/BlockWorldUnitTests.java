import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import org.junit.jupiter.api.Test;

class BlockWorldUnitTests {

	@Test
	void testConstructor0() {
		BlockWorld bw1 = new BlockWorld("AC B");
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		pw.println("[A, C]");
		pw.println("[B]");
		assertEquals(bw1.toString(), writer.toString());
		pw.close();
		
		BlockWorld bw2 = new BlockWorld("A C B");
		StringWriter writer2 = new StringWriter();
		PrintWriter pw2 = new PrintWriter(writer2);
		pw2.println("[A]");
		pw2.println("[C]");
		pw2.println("[B]");
		assertEquals(bw2.toString(), writer2.toString());
		pw2.close();
	
		BlockWorld bw3 = new BlockWorld("ABC");
		StringWriter writer3 = new StringWriter();
		PrintWriter pw3 = new PrintWriter(writer3);
		pw3.println("[A, B, C]");
		assertEquals(bw3.toString(), writer3.toString());
		pw3.close();		
	}
	
	@Test
	public void testEquals() {
		BlockWorld bw1 = new BlockWorld("AC B");
		BlockWorld bw2 = new BlockWorld("AC B");
		BlockWorld bw3 = new BlockWorld("A C B");
		BlockWorld bw4 = new BlockWorld("A B C");
		
		assertTrue(bw1.equals(bw2));
		assertFalse(bw1.equals(bw3));
		assertFalse(bw3.equals(bw2));
		assertTrue(bw3.equals(bw4));
	}
	
	@Test
	public void testEqualsStack() {
		Stack<Character> stack1 = new Stack<>();
		stack1.push('A');
		stack1.push('B');
		stack1.push('C');

		Stack<Character> stack2= new Stack<>();
		stack2.push('A');
		stack2.push('B');
		stack2.push('C');
		stack2.push('D');
		stack2.pop();
		
		assertTrue(stack1.equals(stack2));

	}
	
	@Test
	public void testcopyOf() {
		BlockWorld bw1 = new BlockWorld("AC B");
		BlockWorld bw2 = new BlockWorld("A C B");
		BlockWorld bw3 = new BlockWorld("A B C");
		
		assertTrue(bw1.equals(bw1.copyOf()));
		assertTrue(bw2.equals(bw2.copyOf()));
		assertTrue(bw3.equals(bw3.copyOf()));
	}
	
	@Test
	void testChildren() {
		Ilayout bw1 = new BlockWorld("AC B");
		Ilayout bw1child2= new BlockWorld("A C B");
		Ilayout bw1child1 = new BlockWorld("A BC");
		Ilayout bw1child3 = new BlockWorld("ACB");
		List<Ilayout> list1 = new ArrayList<>();
		list1.add(bw1child1);
		list1.add(bw1child2);
		list1.add(bw1child3);
		assertEquals(bw1.children(), list1);
		
		
		BlockWorld bw2 = new BlockWorld("A C B");
		Ilayout bw2child1 = new BlockWorld("CA B");
		Ilayout bw2child2 = new BlockWorld("C BA");
		Ilayout bw2child3 = new BlockWorld("AC B");
		Ilayout bw2child4 = new BlockWorld("A BC");
		Ilayout bw2child5 = new BlockWorld("AB C");
		Ilayout bw2child6 = new BlockWorld("A CB");
		List<Ilayout> list2 = new ArrayList<>();
		list2.add(bw2child1);
		list2.add(bw2child2);
		list2.add(bw2child3);
		list2.add(bw2child4);
		list2.add(bw2child5);
		list2.add(bw2child6);
		assertEquals(bw2.children().toString(), list2.toString());
		
		
		BlockWorld bw3 = new BlockWorld("ABC");
		Ilayout bw3child1 = new BlockWorld("AB C");
		List<Ilayout> list3 = new ArrayList<>();
		list3.add(bw3child1);
		assertEquals(bw3.children(), list3);
	}

	@Test
	public void testGetG() {
		Ilayout ex1 = new BlockWorld("A C B");
		Ilayout ex2 = new BlockWorld("CA B");
		Ilayout ex3 = new BlockWorld("A BC");

		assertEquals(ex1.getG(), 1.00, 0.001);
		assertEquals(ex2.getG(), 1.00, 0.001);
		assertEquals(ex3.getG(), 1.00, 0.001);
	}
	
	@Test
	public void testIsGoal() {
		Ilayout ex1 = new BlockWorld("A C B");
		Ilayout ex2 = new BlockWorld("CA B");
		Ilayout ex3 = new BlockWorld("A B C");
		Ilayout ex4 = new BlockWorld("A B C");
		
		assertTrue(ex1.isGoal(ex3));
		assertFalse(ex1.isGoal(ex2));
		assertTrue(ex3.isGoal(ex4));		
	}
	
}
