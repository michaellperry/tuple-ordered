package com.mallardsoft.tuple.ordered.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

import com.mallardsoft.tuple.End;
import com.mallardsoft.tuple.Pair;
import com.mallardsoft.tuple.Tuple;
import com.mallardsoft.tuple.Version;
import com.mallardsoft.tuple.ordered.Ordered;

public class OrderedTest
{
    @Test
    public void ordered()
    {
        Ordered<Pair<String, Integer>> pair = Ordered.order(Tuple.from("A", 1));
        assertEquals(Tuple.get1(pair.getTuple()), "A");
        assertEquals((int) Tuple.get2(pair.getTuple()), 1);
        
        assertEquals((int) pair.compareTo(Ordered.order(Tuple.from("A", 1))), 0);
        
        assertTrue((int) pair.compareTo(Ordered.order(Tuple.from("A", 0))) > 0);
        assertTrue((int) pair.compareTo(Ordered.order(Tuple.from("B", 0))) < 0);
        assertTrue((int) pair.compareTo(Ordered.order(Tuple.from("A", 2))) < 0);
        assertTrue((int) pair.compareTo(Ordered.order(Tuple.from((String) null, 2))) > 0);
        assertTrue((int) Ordered.order(Tuple.from((String) null, 2)).compareTo(pair) < 0);
        assertTrue((int) Ordered.order(Tuple.from((String) null, 2)).compareTo(Ordered.order(Tuple.from((String) null, 2))) == 0);
    }
    
    @Test
    public void tree()
    {
        TreeSet<Ordered<Pair<String, Integer>>> tree = new TreeSet<Ordered<Pair<String,Integer>>>();
        
        tree.add(Ordered.order(Tuple.from("A", 1)));
        tree.add(Ordered.order(Tuple.from("A", 2)));
        tree.add(Ordered.order(Tuple.from("B", 1)));
        tree.add(Ordered.order(Tuple.from("B", 1)));
        tree.add(Ordered.order(Tuple.from("B", 1)));
        tree.add(Ordered.order(Tuple.from("B", 0)));
        
        assertEquals(tree.size(), 4);
        Iterator<Ordered<Pair<String, Integer>>> pairs = tree.iterator();
        assertTrue(pairs.hasNext());
        assertEquals(pairs.next().getTuple(), Tuple.from("A", 1));
        assertTrue(pairs.hasNext());
        assertEquals(pairs.next().getTuple(), Tuple.from("A", 2));
        assertTrue(pairs.hasNext());
        assertEquals(pairs.next().getTuple(), Tuple.from("B", 0));
        assertTrue(pairs.hasNext());
        assertEquals(pairs.next().getTuple(), Tuple.from("B", 1));
        assertFalse(pairs.hasNext());
    }
    
    @Test
    public void single()
    {
        assertEquals(Ordered.order(Tuple.from(1)).compareTo(Ordered.order(Tuple.from(1))), 0);
    }
    
    @Test
    public void triple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3)).compareTo(Ordered.order(Tuple.from(1, 2, 3))), 0);
    }
    
    @Test
    public void quadruple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4))), 0);
    }
    
    @Test
    public void quintuple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4, 5)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4, 5))), 0);
    }
    
    @Test
    public void sextuple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6))), 0);
    }
    
    @Test
    public void septuple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7))), 0);
    }
    
    @Test
    public void octuple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7, 8)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7, 8))), 0);
    }
    
    @Test
    public void nonuple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7, 8, 9)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7, 8, 9))), 0);
    }
    
    @Test
    public void decuple()
    {
        assertEquals(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).compareTo(Ordered.order(Tuple.from(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 0);
    }
    
    @Test
    public void testVersion() {
        Version v1 = new Version(2,0,2,3425);
        Version v2 = new Version(2,1,0,241);
        
        assertTrue("Version 2.1.0.241 > 2.0.2.3425", Ordered.order(v2).compareTo(Ordered.order(v1)) > 0);
    }
    
    @Test
    public void testCompareNonNamedTuple() {
    	// Smith, Larry Lee
    	Tuple<String, Tuple<String, Tuple<String, End>>> larry = Tuple.from("Lee").prepend("Larry").prepend("Smith");
    	// Smith, Cathy Lynn
    	Tuple<String, Tuple<String, Tuple<String, End>>> cathy = Tuple.from("Lynn").prepend("Cathy").prepend("Smith");
    	
    	assertTrue("Cathy comes before Larry.", Ordered.order(cathy).compareTo(Ordered.order(larry)) < 0);
    }
}
