package java8.streamsdemo;
import java.util.List;

public class StreamReduceDemo {
    
    public static void main(String... args) {
        
        // Create sample data
        List<Person> roster = Person.createRoster();
        
        // 1. Average age of memebrs
        double avgAge = roster
        	.stream()
        	.filter(p -> p.getGender() == Person.Sex.MALE)
        	.mapToInt(p -> p.getAge())
        	.average()
        	.getAsDouble();
        System.out.println("Average age of male Members: " + avgAge);	
        
        // 2. Sum of ages
        int totalAge = roster
            	.stream()
            	.filter(p -> p.getGender() == Person.Sex.MALE)
            	.mapToInt(p -> p.getAge())
            	.sum();
        System.out.println("Average total age of male Members using \"sum\": " + totalAge);
        //another way to find sum using reduce
        totalAge = roster
            	.stream()
            	.filter(p -> p.getGender() == Person.Sex.MALE)
            	.mapToInt(p -> p.getAge())
            	.reduce(0, (a,b) -> a+ b);
        System.out.println("Average total age of male Members using \"redcue\": " + totalAge);
        	
            
        
    }
}