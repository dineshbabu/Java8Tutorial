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

package nosql.mongodb.restaurantExample.aggregation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import nosql.mongodb.MongoDBUtils;

public class RestaurantDocAggregationDemo {

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
			
			//Create index on "name" field
			mongoCollection.createIndex(new Document("name", 1));
			
			//Create compound index on "name" and "zipcode" field
			mongoCollection.createIndex(new Document("name", 1).append("address.zipcode", -1));
			
			//$project aggregation, db.restaurants.aggregate( [ { $project : { "name" : 1 , "cuisine" : 1 } } ] )
			List<Document> aggregateFilter ;
			/*aggregateFilter = Arrays.asList(
																new Document("$project", new Document("name",1).append("cuisine", 1).append("_id", 0))	
																);
			queryRestaurants(mongoCollection, aggregateFilter);*/
			
			//$match aggregation, db.restaurants.aggregate( [ { $match : { "name" : "St Albans cuisine"} } ] )
			/*aggregateFilter = Arrays.asList(
											new Document("$match", new Document("borough", "St Albans District Council")),
											new Document("$match", new Document("name", "St Albans cuisine")),
											new Document("$project", new Document("name",1).append("cuisine", 1).append("_id", 0))
											
											);
			queryRestaurants(mongoCollection, aggregateFilter);*/
			
			//$limit aggregation, db.restaurants.aggregate( [ { $limit : 2} } ] )
			/*aggregateFilter = Arrays.asList(
											new Document("$match", new Document("borough", "St Albans District Council")),
											new Document("$project", new Document("name",1).append("cuisine", 1).append("_id", 0)),
											new Document("$limit", 2)
											
											);
			queryRestaurants(mongoCollection, aggregateFilter);*/
			
			//$skip aggregation, db.restaurants.aggregate( [ { $skip : 1} } ] )
			/*aggregateFilter = Arrays.asList(
											new Document("$match", new Document("borough", "St Albans District Council")),
											new Document("$project", new Document("name",1).append("cuisine", 1).append("_id", 0)),
											new Document("$limit", 2),
											new Document("$skip", 1)
											);
			queryRestaurants(mongoCollection, aggregateFilter);*/
			
			//$group aggregation, Groupby borough and sum of all restaurant_id in the borough
			/*([
				{
					$group:{"_id" :"borough",
							"SumOfrestaurantIds":{"$sum":"$restaurant_id"}
							}
				}
			])	*/
			aggregateFilter = Arrays.asList(
											new Document("$group", new Document("_id", "borough")
											.append("SumOfrestaurantIds", new Document("$sum", "$restaurant_id"))
											));
			queryRestaurants(mongoCollection, aggregateFilter);
			
			
			
		}finally{
			if( null != mongoClient){
			mongoClient.close();
			}
		}		
	}

	
	public static void queryRestaurants(MongoCollection<Document> mongoCollection, List<Document> filterCondition ){
		AggregateIterable<Document> aggregateIterable = mongoCollection.aggregate(filterCondition);
		MongoCursor<Document> docCursor = aggregateIterable.iterator();
		
		Document docObj; 
		List<Document> docsList = new ArrayList<>();
		while (docCursor.hasNext()){
			docObj = docCursor.next();
			System.out.println("Document: " + docObj);
			docsList.add(docObj);
		}
		
		//Serialize or convert doc list to json
		System.out.println("Docs list serialised to Json : " + JSON.serialize(docsList));
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
								.append("zipcode", "1")
								
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
		Document doc = getDocumentToInsert();
		doc.append("name", "St Albans cuisine1");
		doc.append("address", new Document("zipcode", "2"));
		docList.add(doc);
		doc = getDocumentToInsert();
		doc.append("name", "St Albans cuisine2");
		doc.append("borough", "St Albans District Council1");
		doc.append("address", new Document("zipcode", "3"));
		docList.add(doc);
		doc = getDocumentToInsert();
		doc.append("name", "St Albans cuisine3");
		doc.append("borough", "St Albans District Council1");
		doc.append("address", new Document("zipcode", "4"));
		docList.add(doc);
		return docList;
	}

}
