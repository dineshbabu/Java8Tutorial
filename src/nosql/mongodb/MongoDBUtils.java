package nosql.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;

public class MongoDBUtils {
	
	private static final String USER_NAME = "admin";
	private static final String PASSWORD = "admin";
	private static final String DATABASE = "dinesh";
	
	public static MongoClient getMongoClient(){
		MongoClient mongoClient = null;
		try{
		
			//Authenticate
			MongoCredential mongoCredential = authenticate("admin", "admin", "dinesh");
	//		Get connection
			mongoClient = new MongoClient("LocalHost", 27017);
//			mongoClient = new MongoClient(new ServerAddress("LocalHost", 27017), Arrays.asList(mongoCredential));
			
	//		List databases names		
			MongoIterable<String> iterable = mongoClient.listDatabaseNames();
			MongoCursor<String> databaseCursor = iterable.iterator();
			while(databaseCursor.hasNext()){
				System.out.println("Databases name : " + databaseCursor.next());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
//		finally{
//			if( null != mongoClient){
//				mongoClient.close();
//			}
//			
//		}
	return mongoClient;
	}
	
	public static MongoCredential authenticate(String userName, String password, String database){
		return MongoCredential.createMongoCRCredential(userName, database, password.toCharArray());
	}
}
