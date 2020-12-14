package modules

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repository.EmployeeRepository
import services.EmployeeService
import javax.inject.Named

@Module
class ServiceModule {

    @Provides
    @Named("service")
    fun getService(@Named("employeerepository") emp: EmployeeRepository):EmployeeService
    {
        return EmployeeService(emp)
    }
}