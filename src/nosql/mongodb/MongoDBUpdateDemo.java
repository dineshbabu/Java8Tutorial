package nosql.mongodb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBUpdateDemo {

	private static final String  DATABASE_NAME_DINESH = "dinesh"; 
	private static final String  COLECTION_NAME_AUTHORS = "Authors";
	private static final String JSON_ARRAY_FILE_PATH = "C:\\installations\\eclipse\\workspace\\Java8Tutorial\\src\\nosql\\mongodb\\Authors_json.json";
	private static final String JSON_SINGLE_RECORD_FILE_PATH = "C:\\installations\\eclipse\\workspace\\Java8Tutorial\\src\\nosql\\mongodb\\Authors_single_record_json.json";

	
	public static void main(String[] args) throws IOException{
		
		MongoClient mongoClient = null;
		try{

			mongoClient = MongoDBUtils.getMongoClient();
			
			//Get one database
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME_DINESH);
			
			//Create collection
			createCollection(mongoDatabase);
			
			//Get created collection
			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLECTION_NAME_AUTHORS);
			
//			//Read JSON with single object in it
//			String jsonString = getJsonString(JSON_SINGLE_RECORD_FILE_PATH);
//
//			//Create single document object
//			Document doc  = Document.parse(jsonString);
//			
//			//insert one document object
//			mongoCollection.insertOne(doc);
	
			//Read JSON with array of object in it
			String jsonString = getJsonString(JSON_ARRAY_FILE_PATH);
			Gson gson = new Gson();
			Author[] authorArray = gson.fromJson(jsonString, Author[].class);
			List<Author> authorList =  Arrays.asList(authorArray);
			List<Document> docList = authorList
				.stream()
				.map(convertToJson())
				.map(json -> Document.parse(json))
				.collect(Collectors.toList());
			
//			insert list of document objects
			mongoCollection.insertMany(docList);
			
			//Get all. Iterate and print the whole collection
			FindIterable<Document> findIterable = mongoCollection.find();
			MongoCursor<Document> docCursor = findIterable.iterator();
//			printDocs(docCursor);
			
			//Update One record. Update Dinesh's book named "Dinesh book1 Name" to "Dinesh book1 updated Name"
			mongoCollection.updateOne(new Document("books.name" , "Dinesh book1 Name"), 
									  new Document("$set", new Document("books.$.name", "Dinesh book1 updated Name")));
			
			findIterable = mongoCollection.find(new Document("author" , "Dinesh"));
			//findIterable = mongoCollection.find(Filters.eq("author" , "Dinesh"));//*** Can use this as well
			docCursor = findIterable.iterator();
//			printDocs(docCursor);

			//Update One record, but multiple fields. Update Harry's book named "Harry book1 Name" 
			mongoCollection.updateOne(new Document("books.name" , "Harry book1 Name"), 
									  new Document("$set", new Document("books.$.name", "Harry book1 updated Name").append("books.$.cost", "11.50")));
			
			findIterable = mongoCollection.find(new Document("author" , "Harry"));
			//findIterable = mongoCollection.find(Filters.eq("author" , "Dinesh"));//*** Can use this as well
			docCursor = findIterable.iterator();
			printDocs(docCursor);

			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if( null != mongoClient){
			mongoClient.close();
			}
		}		
	}


	private static void printDocs(MongoCursor<Document> docCursor) {
	Document docObj;
	Gson gson = new Gson();
	while (docCursor.hasNext()){
		docObj = docCursor.next();
		System.out.println("Document: " + docObj);
		System.out.println("Document injson form: " + gson.toJson(docObj.toString(), Author.class));
		
	}
}

//	private static Function<JsonValue, Author> convertToAuthor() {
//		return jsonValue -> (JsonObjectImpl)jsonValue;
//	}


	private static Function<Author, String> convertToJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		return jsonValue -> {
			try {
				return objectMapper.writeValueAsString(jsonValue);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		};
	}


	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String getJsonString(String jsonFilePath) throws FileNotFoundException, IOException {
		BufferedReader jsonFileReader = new BufferedReader(new FileReader(jsonFilePath));
		String jsonString = "";
		String currentLine = "";
		while(( currentLine = jsonFileReader.readLine() ) != null )
			jsonString += currentLine;
		return jsonString;
	}


	/**
	 * @param mongoDatabase
	 */
	private static void createCollection(MongoDatabase mongoDatabase) {
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLECTION_NAME_AUTHORS);
		if( mongoCollection != null){
			mongoCollection.drop();
		}
		mongoDatabase.createCollection(COLECTION_NAME_AUTHORS);
	}


	/**
	 * @return
	 */
	private static Document getDocumentToInsert() {
		Document doc = new Document();
		doc = doc.append("name1", "Book1");
		doc = doc.append("name2", "Book2");
		doc = doc.append("name3", "Book3");
		doc = doc.append("name4", "Book4");
		doc = doc.append("name5", "Book5");
		doc = doc.append("name6", "Book6");
		return doc;
	}


	/**
	 * @return
	 */
	private static List<Document> getDocumentListToInsert() {
		List<Document> docList = new ArrayList<Document>();
		
		Document doc1 = new Document("name7", "Book7");
		docList.add(doc1);
		
		Document doc2 = new Document("name8", "Book8");
		docList.add(doc2);
		
		Document doc3 = new Document("name9", "Book9");
		docList.add(doc3);
		return docList;
	}

}
