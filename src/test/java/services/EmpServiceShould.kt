package services

import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import repository.EmployeeRepository
import repository.Employees

class EmpServiceShould {

    lateinit var employeeService: EmployeeService


     var objectMapper=Mockito.mock(ObjectMapper::class.java)
     var  employeeRepository=EmployeeRepository(objectMapper)
     var testDataSource=TestDataSource()


    @Before
    fun setUp()
    {
        employeeService=EmployeeService(employeeRepository)
    }

    @Test
    fun getEmpRecordTest()
    {
//        var list= mutableListOf<Employees>()
        whenever(employeeRepository.getAllFretronEmpRecord()).thenReturn(testDataSource.getDevice())
        var responcse= employeeService.fretronAllEmpRecord()
        println("Data=:$responcse")

    }

    @Test
    fun getEmpRecordNameTest()
    {
        val name="Mohit"
//        whenever(employeeRepository.getRecordByName(name)).thenReturn(testDataSource.getDevice())
        var response= employeeService.getOneRecordEmp(name)
        println("data=:"+response.toString())
    }
}