import dagger.Component
import modules.*
import org.glassfish.grizzly.http.server.HttpServer


@Component(modules = [ConfigModule::class,HttpServerModule::class,ServiceModule::class,SchemaModule::class,RepositoryModule::class])
interface Controller {
    fun getServer():HttpServer
}