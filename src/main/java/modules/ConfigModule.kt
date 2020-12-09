package modules

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    private var hostName = "http://localhost"
    private var serverport = 8070

    @Provides
    @Named("hostName")
    fun getHost(): String {
        return hostName
    }

    @Provides
    @Named("port")
    fun getPort():Int
    {
        return serverport
    }
}