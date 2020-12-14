 package repository

 import com.mongodb.BasicDBObject
 import com.mongodb.MongoClient
 import com.mongodb.client.MongoDatabase
 import com.mongodb.util.JSON
 import org.bson.Document
 import org.codehaus.jackson.map.ObjectMapper
 import java.util.*
 import javax.inject.Inject
 import javax.inject.Named

 class EmployeeRepository @Inject constructor(@Named("objectMapper") val objectMapper: ObjectMapper) {


     var mongoClient: MongoClient?=MongoClient("localhost",27017)

     var mongoDatabase:MongoDatabase?=mongoClient?.getDatabase("EmployeeDetails")

     fun getAllFretronEmpRecord():List<Employees>
     {
         val empRecord= mutableListOf<Employees>()

         val mongoCollection=mongoDatabase?.getCollection("employess")
         val mongoCursor=mongoCollection?.find()?.iterator()
         while (mongoCursor!!.hasNext())
         {
             try {
                 var doc: Document = mongoCursor.next()
                 doc.remove("_id")
                 val json=JSON.serialize(doc)

                 val employees=objectMapper.readValue(json,Employees::class.java)

                 empRecord.add(employees)
             } catch (e: Exception) {
                 println("Exception=:$e")
             }
         }
         return empRecord
     }
     fun getRecordByName(name:String):List<Employees>
     {

         var record= mutableListOf<Employees>()

         val mongoCollection=mongoDatabase?.getCollection("employess")
         val basicDBObject= BasicDBObject()
         basicDBObject["name"] = name
         val mongoCursor=mongoCollection?.find(basicDBObject)?.iterator()
         while (mongoCursor!!.hasNext())
         {
             try {
                 var doc= Document()
                 doc = mongoCursor.next()
                 doc.remove("_id")

                 var json=JSON.serialize(doc)
                 var employees=objectMapper.readValue(json,Employees::class.java)
//                 employees?.let { record.add(it) }
                 record.add(employees)

             } catch (e: Exception) {
                 println("Exception=:$e")
             }
         }
         println("record Data=: $record")
         return record
     }
     fun insertEmployeeRecord(record: Employees)
     {
         try {
             println("Data Co=:$record")
             val mongoCollection=mongoDatabase?.getCollection("employess")
             val doc=Document.parse(record.toString())
             doc["uuid"] = UUID.randomUUID().toString()
             val mongoCursor=mongoCollection?.insertOne(doc)
             println("details=:$mongoCursor")
         } catch (e: Exception) {
             println("Exception occurred=:$e")
         }
     }
     fun deleteRecord(uuid:String)
     {
         try {
             println("From Repository=:$uuid")
             var basicDBObject=BasicDBObject()
             basicDBObject["uuid"] = uuid
             val mongoCollection=mongoDatabase?.getCollection("employess")
             val mongoCursor=mongoCollection?.deleteOne(basicDBObject)
         } catch (e: Exception) {
             println("Exception=:$e")
         }
     }
//    private fun getUUid(): UUID {
//         var id=UUID.randomUUID()
//         println(id);
//         return id;
//     }
 }
 //fun main()
 //{
 //    var e=EmployeeRepository();com
 //    e.getUUid();
 ////   print( e.getNewUserId());
 //   var uuid= UUID.randomUUID();
 //    println(uuid)
 //}
