package java8.streamsdemo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectDemo {
    
    public static void main(String... args) {
        
        // Create sample data
        List<Person> roster = Person.createRoster();
        
        // 1. Sum of ages of all males
        Averager averagerCollect = roster
            	.stream()
            	.filter(p -> p.getGender() == Person.Sex.MALE)
            	.mapToInt(p -> p.getAge())
            	.collect(Averager::new, Averager::accept, Averager::combine);// This form of use is not common
        
        System.out.println("Average total age of male Members using \"collect\": " + averagerCollect.average());
        
        //2. .toList()....Get list of names of all males
        List<String> listOfNamesOfMales = roster
            	.stream()
            	.filter(p -> p.getGender() == Person.Sex.MALE)
            	.map(p -> p.getName())
            	.collect(Collectors.toList());
        
        System.out.println("List of name of male members using \"Collectors.toList()\"");
        listOfNamesOfMales
        		.stream()
        		.forEach( p -> System.out.println(p));
        
        //2a. Collectors.joining....Get comma separated string of all male names
        String CommaSeparatedlistOfNamesOfMales = roster
            	.stream()
            	.filter(p -> p.getGender() == Person.Sex.MALE)
            	.map(p -> p.getName())
            	.collect(Collectors.joining(","));
        
        System.out.println("Comma separated list of name of male members using \"Collectors.joining()\" : " + CommaSeparatedlistOfNamesOfMales);
        
        //3. .groupingBy()....Get list of members by gender
        Map<Person.Sex, List<Person>> listOfMembersByGender = roster
            	.stream()
            	.collect(Collectors.groupingBy(p -> p.getGender()));
        
        System.out.println("List of name of members by gender \"Collectors.groupingBy\": " + listOfMembersByGender);
        
        //4. Collectors.mapping. retrieves the names of each member in the collection roster and groups them by gender
        Map<Person.Sex, List<String>> listOfNamesByGender = roster
        	.stream()
        	.collect(Collectors.groupingBy(
        								p -> p.getGender(),
        								Collectors.mapping(p -> p.getName(), Collectors.toList())
        								));
        System.out.println("List of names grouped by gender \"Collectors.groupingBy & Collectors.mapping\": " + listOfNamesByGender);
        
        //5. Collectors.reducing  retrieves the total age of members of each gender:
        Map<Person.Sex, Integer> listOfTotalAgesByGender = roster
        	.stream()
        	.collect(Collectors.groupingBy(
        									p -> p.getGender(),
        									Collectors.reducing(0, p -> p.getAge(), Integer::sum)));
        	
        System.out.println("List of total age, grouped by gender \"Collectors.groupingBy & Collectors.reducing\": " + listOfTotalAgesByGender);
        
        //6. Collectors.averagingInt average age of members of each gender:
        Map<Person.Sex, Double> listOfAverageAgesByGender = roster
        	.stream()
        	.collect(Collectors.groupingBy(
        									p->p.getGender(),
        									Collectors.averagingInt(p->p.getAge())
        									));
        
        System.out.println("List of average age, grouped by gender \"Collectors.groupingBy & Collectors.averagingInt\": " + listOfAverageAgesByGender);
        
    }
    
    
}