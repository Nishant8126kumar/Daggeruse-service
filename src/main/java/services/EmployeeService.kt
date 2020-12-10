 package services

 import org.codehaus.jackson.map.ObjectMapper
 import repository.EmployeeRepository
 import repository.Employees
 import javax.inject.Inject
 import javax.inject.Named

 class EmployeeService @Inject constructor(@Named("employeerepository") val employeerepository: EmployeeRepository) {
//     var employeerepository=EmployeeRepository(objectMapper);

     fun fretronAllEmpRecord(): List<Employees>? {

         return employeerepository.getAllFretronEmpRecord()!!
     }

     fun getOneRecordEmp(name: String):List<Employees> {
         println("In Service=:" + name)
         return employeerepository?.getRecordByName(name);
     }
     fun newEmployeeRecord(record: Employees)
     {
 //        println("Data Reached=:"+record)
         employeerepository.insertEmployeeRecord(record)

     }
     fun deletRecordByuuid(uuid:String)
     {
         employeerepository.deleteRecord(uuid)

     }
 }



