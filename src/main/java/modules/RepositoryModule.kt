package modules

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repository.EmployeeRepository
import javax.inject.Named

@Module
class RepositoryModule {
    @Provides
    @Named("employeerepository")
    fun getRepository(  @Named("objectMapper") objectMapper: ObjectMapper):EmployeeRepository
    {
        return EmployeeRepository(objectMapper)
    }
}