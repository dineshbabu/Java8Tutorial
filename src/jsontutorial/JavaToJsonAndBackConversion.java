package jsontutorial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.plaf.BorderUIResource.EtchedBorderUIResource;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JavaToJsonAndBackConversion {
	
	private static final String JSON_ARRAY_FILE_PATH = "C:\\installations\\eclipse\\workspace\\Java8Tutorial\\src\\jsontutorial\\Authors_json.json";
	private static final String JSON_SINGLE_RECORD_FILE_PATH = "C:\\installations\\eclipse\\workspace\\Java8Tutorial\\src\\jsontutorial\\Authors_single_record_json.json";


	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		//Read JSON from file
		String jsonString = getJsonString(JSON_SINGLE_RECORD_FILE_PATH);
		
		
		ObjectMapper objMapper = new ObjectMapper();
		
		//Read json with single record
		Author author = objMapper.readValue(jsonString, Author.class);
		System.out.println(author);
		System.out.println("Converting an object to Json :" +objMapper.writeValueAsString(author));


		//Read json with array of objects
		jsonString = getJsonString(JSON_ARRAY_FILE_PATH);
		Author[] authorList = objMapper.readValue(jsonString, Author[].class);
		System.out.println(authorList);
		System.out.println("Converting array object to Json :" +objMapper.writeValueAsString(authorList));

		
	}


	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String getJsonString(String filePath) throws FileNotFoundException, IOException {
		BufferedReader jsonFileReader = new BufferedReader(new FileReader(filePath));
		String jsonString = "";
		String currentLine = "";
		while(( currentLine = jsonFileReader.readLine() ) != null )
			jsonString += currentLine;
		return jsonString;
	}

}
