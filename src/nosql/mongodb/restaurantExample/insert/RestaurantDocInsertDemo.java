//{
//	"address": {
//		"building": "1007",
//		"coord": [-73.856077, 40.848447],
//		"street": "Morris Park Ave",
//		"zipcode": "10462"
//	},
//	"borough": "Bronx",
//	"cuisine": "Bakery",
//	"grades": [{
//		"date": {
//			"$date": 1393804800000
//		},
//		"grade": "A",
//		"score": 2
//	}, {
//		"date": {
//			"$date": 1378857600000
//		},
//		"grade": "A",
//		"score": 6
//	}, {
//		"date": {
//			"$date": 1358985600000
//		},
//		"grade": "A",
//		"score": 10
//	}, {
//		"date": {
//			"$date": 1322006400000
//		},
//		"grade": "A",
//		"score": 9
//	}, {
//		"date": {
//			"$date": 1299715200000
//		},
//		"grade": "B",
//		"score": 14
//	}],
//	"name": "Morris Park Bake Shop",
//	"restaurant_id": "30075445"
//}

package nosql.mongodb.restaurantExample.insert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import nosql.mongodb.MongoDBUtils;

public class RestaurantDocInsertDemo {

	private static final String  DATABASE_NAME_DINESH = "dinesh"; 
	private static final String  COLECTION_NAME_RESTAURANTS = "restaurants";

	
	public static void main(String[] args) throws ParseException{
		
		MongoClient mongoClient = null;
		try{

			mongoClient = MongoDBUtils.getMongoClient();
			
			//Get one database
			MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME_DINESH);
			
			//Create collection
			createCollection(mongoDatabase);
			
			//Get created collection
			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLECTION_NAME_RESTAURANTS);
			
			//Create single document object
			Document doc = getDocumentToInsert();
			
			//insert one document object
			mongoCollection.insertOne(doc);
	
			
			//Create list of document object
			List<Document> docList = getDocumentListToInsert();
			
			//insert list of document objects
			mongoCollection.insertMany(docList);
			
			//Find the collection
			FindIterable<Document> findIterable = mongoCollection.find(getDocumentToInsert());
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
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLECTION_NAME_RESTAURANTS);
		if( mongoCollection != null){
			mongoCollection.drop();
		}
		mongoDatabase.createCollection(COLECTION_NAME_RESTAURANTS);
	}


	/**
	 * @return
	 * @throws ParseException 
	 */
	private static Document getDocumentToInsert() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		Document doc = new Document();
		doc.append("address",
					new Document().append("building", "9999")
								.append("coord", Arrays.asList(99.9999,-99.9999))
								.append("street", "St Albans")
								.append("zipcode", "AL12PA")
								
					).append("borough", "St Albans District Council")
					.append("cuisine", "japanese")
					.append("grades", Arrays.asList( new Document().append("date", format.parse("2016-08-03T10:10:10Z"))
																	.append("grade", "AAA")
																	.append("score", 999),
													new Document().append("date", format.parse("2016-08-02T10:10:10Z"))
													.append("grade", "BBB")
													.append("score", 998),
													new Document().append("date", format.parse("2016-08-01T10:10:10Z"))
													.append("grade", "CCC")
													.append("score", 997),
													new Document().append("date", format.parse("2016-07-01T10:10:10Z"))
													.append("grade", "DDD")
													.append("score", 996),
													new Document().append("date", format.parse("2016-07-02T10:10:10Z"))
													.append("grade", "EEE")
													.append("score", 995)
													)				
							).append("name", "St Albans cuisine")
							.append("restaurant_id", 9999999);
					
		return doc;
	}


	/**
	 * @return
	 * @throws ParseException 
	 */
	private static List<Document> getDocumentListToInsert() throws ParseException {
		List<Document> docList = new ArrayList<Document>();
		docList.add(getDocumentToInsert());
		docList.add(getDocumentToInsert());
		docList.add(getDocumentToInsert());
		return docList;
	}

}
