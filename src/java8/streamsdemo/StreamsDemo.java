package java8.streamsdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamsDemo {
	
	private enum Gender{MALE, FEMALE};

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new StreamsDemo.Person("dinesh", 42,Gender.MALE));
		persons.add(new StreamsDemo.Person("soumya", 31,Gender.FEMALE));
		persons.add(new StreamsDemo.Person("harry", 6,Gender.MALE));
		persons.add(new StreamsDemo.Person("alvin", 2,Gender.MALE));
		
		Predicate<Person> adultMale = p -> (p.getAge() > 18 && p.getGender() == Gender.MALE);
		Predicate<Person> adultFemale = p -> (p.getAge() > 18 && p.getGender() == Gender.FEMALE);
		Predicate<Person> kids = p -> p.getAge() < 18 ;
		printNames(persons, getAdult());
		printNames(persons, adultMale);
		printNames(persons, adultFemale);
		printNames(persons, kids);
	}
	
	public static Predicate<Person> getAdult(){
		return p -> (p.getAge() > 18);
	}
	
	private static void printNames(List<Person> persons, Predicate<Person> predicate){
		persons.stream()
				.filter(predicate)
				//scenario1
				.map( p -> p.getName())
				.forEach(name -> System.out.println(name));
				//scenario2
//				.mapToInt( p -> p.getAge())
//				.average()
//				.getAsDouble();
				
	}
	

	private static class Person{
		String name;
		int age;
		Gender gender;
		
		Person(String name, int age, Gender gender){
			this.name = name;
			this.age = age;
			this.gender = gender;			
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public Gender getGender() {
			return gender;
		}

		
	}

}
