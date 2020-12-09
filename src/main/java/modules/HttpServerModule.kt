package modules

import dagger.Module
import dagger.Provides
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import resource.EmployeeResource
import javax.inject.Named
import javax.ws.rs.core.UriBuilder

@Module
class HttpServerModule {

    @Provides
    fun getResourceConfig(employeeResource: EmployeeResource):ResourceConfig
    {
        return ResourceConfig().register(employeeResource)

    }

    @Provides
    fun server(@Named("hostName") host:String,@Named("port") port:Int,resourceConfig: ResourceConfig):HttpServer
    {
        val uri=UriBuilder.fromUri(host).port(port).build()
        return GrizzlyHttpServerFactory.createHttpServer(uri,resourceConfig)

    }
}