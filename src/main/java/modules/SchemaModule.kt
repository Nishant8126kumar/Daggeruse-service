package modules

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Named

@Module
class SchemaModule {
    @Provides
    @Named("objectMapper")
    fun getMapper():ObjectMapper
    {
        return  ObjectMapper()
    }
}