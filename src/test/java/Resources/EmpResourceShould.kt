package Resources

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import junit.framework.TestCase
import org.codehaus.jettison.json.JSONObject
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.openjdk.tools.javac.util.Assert
import resource.EmployeeResource
import services.EmployeeService
import javax.ws.rs.core.Application


class EmpResourceShould : JerseyTest(){
     val baseUrl="/api"
    private lateinit var employeeService:EmployeeService
    private lateinit var employeeResource: EmployeeResource
    val testDataSource=TestDataSource()



    override fun configure(): Application
    {
        val config=ResourceConfig()
        employeeService=Mockito.mock(EmployeeService::class.java)
        employeeResource=EmployeeResource(any(), any())
        config.register(employeeResource)
        return  config.application
    }

    @Test
    fun return_200_status()
    {

        whenever(employeeService.fretronAllEmpRecord()).thenReturn(testDataSource.getDevice())
        val responce=target("$baseUrl").request().get()

        val data=responce.readEntity(String::class.java)
        val responceJson=JSONObject(data)
        val emp=responceJson.getJSONObject("data")
        println("Data=:$emp")
        TestCase.assertTrue(responce.status==200)

    }

}