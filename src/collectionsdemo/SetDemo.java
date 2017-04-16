package collectionsdemo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {

	public static void main(String[] args) {
		
		   
        Set<String> hashSet = new HashSet<>();
        hashSet.add(null); //null allowed
        hashSet.add(null);//duplicate not allowed
        System.out.println(hashSet);
        
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("B");
        treeSet.add("A");
        System.out.println(treeSet);//Orders values in asc
        
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("B");
        linkedHashSet.add("A");
        System.out.println(linkedHashSet);//Orders values in asc
	}

}
