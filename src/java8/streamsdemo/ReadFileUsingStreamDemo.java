package java8.streamsdemo;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReadFileUsingStreamDemo {

	public static void main(String args[]) {

		//****Create stream example		
		Stream<Integer> numbersFromValues = Stream.of(1, 2);
		Stream<String> stringsFromValues = Stream.of("1", "2");
		
		int[] numbers = {1, 2, 3, 4};
		IntStream numbersFromArray = Arrays.stream(numbers);
	
		String fileName = "c://lines.txt";
		List<String> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.defaultCharset())) {

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			list = stream
					.filter(line -> !line.startsWith("line3"))
					.map(String::toUpperCase)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		list.forEach(System.out::println);

	}

}