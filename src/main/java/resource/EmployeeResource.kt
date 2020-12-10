 package resource

 import org.codehaus.jackson.map.ObjectMapper
 import repository.Employees
 import services.EmployeeService
 import javax.inject.Inject
 import javax.inject.Named
 import javax.ws.rs.*
 import javax.ws.rs.core.MediaType
 import javax.ws.rs.core.Response
 @Path("/employee")
 class EmployeeResource @Inject constructor(@Named("service") val fretronService: EmployeeService, @Named("objectMapper") val objectMapper :ObjectMapper) {
//     val fretronService = EmployeeService()
//     val objectMapper = ObjectMapper()

     @GET
     @Produces(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
     fun fretronGetAllRecord(): Response {
         println("Welcome Fretron resource")
         val record = fretronService.fretronAllEmpRecord()
         println("record=:  $record")
         return Response.ok(record.toString()).build()
     }
     @GET
     @Path("/{name}")
     @Consumes(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
     fun fretronGetOneRecordByUserName(@PathParam("name") name: String): Response {
         fretronService.getOneRecordEmp(name)
         val record=fretronService.getOneRecordEmp(name)
         return Response.ok(record.toString()).build()
     }
     @POST
     @Consumes(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML)
     fun registerEmployeeRecord(request:String):String
     {
         val empRecord=objectMapper.readValue(request,Employees::class.java)
 //        println("Record=:$empRecord")
         fretronService.newEmployeeRecord(empRecord)
         return "ok"
     }
     @DELETE
     @Path("/{uuid}")
     @Consumes(MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN)
     fun deleteEmpRecord(@PathParam("uuid")uuid:String):String
     {
         fretronService.deletRecordByuuid(uuid)
         return "Record Deleted"
     }

 }
