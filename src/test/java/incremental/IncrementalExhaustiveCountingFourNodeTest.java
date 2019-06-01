package incremental;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import incremental.IncrementalExhaustiveCounting;
import incremental.IncrementalExhaustiveCountingFourNode;
import input.StreamEdge;

public class IncrementalExhaustiveCountingFourNodeTest {

	@Test
	public void singleLineAddition() {
		StreamEdge a = new StreamEdge("a", 1, "b", 2);
		StreamEdge b = new StreamEdge("b", 2, "c", 3);
		StreamEdge c = new StreamEdge("c", 3, "d", 4);
		IncrementalExhaustiveCountingFourNode topk = new IncrementalExhaustiveCountingFourNode();
		topk.addEdge(a);
		topk.addEdge(b);
		topk.addEdge(c);
		assertEquals(1, topk.getFrequentPatterns().size());

	}
	@Test
	public void circleAddition() {
		StreamEdge a = new StreamEdge("a", 1, "b", 2);
		StreamEdge b = new StreamEdge("b", 2, "c", 3);
		StreamEdge c = new StreamEdge("c", 3, "d", 4);
		StreamEdge d = new StreamEdge("d", 4, "a", 1);
		IncrementalExhaustiveCountingFourNode topk = new IncrementalExhaustiveCountingFourNode();
		topk.addEdge(a);
		topk.addEdge(b);
		topk.addEdge(c);
		topk.addEdge(d);
		assertEquals(1, topk.getFrequentPatterns().size());
	}
	
	@Test
	public void tailedTriangleAddition() {
		StreamEdge a = new StreamEdge("a", 1, "b", 2);
		StreamEdge b = new StreamEdge("a", 1, "c", 3);
		StreamEdge c = new StreamEdge("a", 1, "d", 4);
		StreamEdge d = new StreamEdge("b", 2, "c", 3);
		IncrementalExhaustiveCountingFourNode topk = new IncrementalExhaustiveCountingFourNode();
		topk.addEdge(a);
		topk.addEdge(b);
		topk.addEdge(c);
		topk.addEdge(d);
		assertEquals(1, topk.getFrequentPatterns().size());
	}
	
	@Test
	public void starAddition() {
		StreamEdge a = new StreamEdge("a", 1, "b", 2);
		StreamEdge b = new StreamEdge("a", 1, "c", 3);
		StreamEdge c = new StreamEdge("a", 1, "d", 4);
		IncrementalExhaustiveCountingFourNode topk = new IncrementalExhaustiveCountingFourNode();
		topk.addEdge(a);
		topk.addEdge(b);
		topk.addEdge(c);
		assertEquals(1, topk.getFrequentPatterns().size());
	}
	
	@Test
	public void quasiCliqueAddition() {
		StreamEdge a = new StreamEdge("a", 1, "b", 2);
		StreamEdge b = new StreamEdge("a", 1, "c", 3);
		StreamEdge c = new StreamEdge("a", 1, "d", 4);
		StreamEdge d = new StreamEdge("b", 2, "c", 3);
		StreamEdge e = new StreamEdge("b", 2, "d", 4);
		IncrementalExhaustiveCountingFourNode topk = new IncrementalExhaustiveCountingFourNode();
		topk.addEdge(a);
		topk.addEdge(b);
		topk.addEdge(c);
		topk.addEdge(d);
		topk.addEdge(e);
		assertEquals(1, topk.getFrequentPatterns().size());
	}
	
	@Test
	public void randomPatternCount() {
		List<StreamEdge> list = new ArrayList<StreamEdge>();
		list.add(new StreamEdge("b", 2, "k", 3));
		list.add(new StreamEdge("b", 2, "l", 4));
		list.add(new StreamEdge("b", 2, "m", 5));
		list.add(new StreamEdge("b", 2, "n", 6));
		list.add(new StreamEdge("k", 3, "o", 7));
		list.add(new StreamEdge("k", 3, "p", 8));
		list.add(new StreamEdge("l", 4, "q", 9));
		list.add(new StreamEdge("m", 5, "n", 6));
		list.add(new StreamEdge("m", 5, "r", 10));
		list.add(new StreamEdge("n", 6, "s", 11));
		
		IncrementalExhaustiveCountingFourNode topk = new IncrementalExhaustiveCountingFourNode();
		for(StreamEdge streamEdge: list) {
			topk.addEdge(streamEdge);
		}
		int count = topk.getFrequentPatterns().size();
		topk.addEdge(new StreamEdge("a", 1, "b", 2));
		
		
		assertEquals(11, topk.getFrequentPatterns().size()-count);
	}
	
}
