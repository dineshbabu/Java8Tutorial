package nosql.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class MongoDBDocInsertDemo {

	private static final String  DATABASE_NAME_DINESH = "dinesh"; 
	private static final String  COLECTION_NAME_BOOKS = "Books";

	
	public static void main(String[] args){
		
		MongoClient mongoClient = null;
		try{

			mongoClient = MongoDBUtils.getMongoClient();
			
			//Get one database
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME_DINESH);
			
			//Create collection
			createCollection(mongoDatabase);
			
			//Get created collection
			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLECTION_NAME_BOOKS);
			
			//Create single document object
			Document doc = getDocumentToInsert();
			
			//insert one document object
			mongoCollection.insertOne(doc);
	
			
			//Create list of document object
			List<Document> docList = getDocumentListToInsert();
			
			//insert list of document objects
			mongoCollection.insertMany(docList);
			
			//Find the collection
			FindIterable<Document> findIterable = mongoCollection.find();
			MongoCursor<Document> docCursor = findIterable.iterator();
			
			Document docObj; 
			List<Document> docsList = new ArrayList<>();
			while (docCursor.hasNext()){
				docObj = docCursor.next();
				System.out.println("Document: " + docObj);
				docsList.add(docObj);
			}
			
			//Serialize or convert doc list to json
			System.out.println("Docs list serialised to Json : " + JSON.serialize(docsList));
			
		}finally{
			if( null != mongoClient){
			mongoClient.close();
			}
		}		
	}


	/**
	 * @param mongoDatabase
	 */
	private static void createCollection(MongoDatabase mongoDatabase) {
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLECTION_NAME_BOOKS);
		if( mongoCollection != null){
			mongoCollection.drop();
		}
		mongoDatabase.createCollection(COLECTION_NAME_BOOKS);
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
