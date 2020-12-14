package helper

import com.mongodb.util.JSON
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import repository.Employees
import javax.ws.rs.client.Entity

class TestDataSource {


    var objectMapper=ObjectMapper();
    fun getDevice():List<Employees> {
        var list= mutableListOf<Employees>()
        var doc=Document()
        doc["name"] = "Nishant"
        doc["address"]="Aligarh"
        doc["designation"]="software Developer"
//        doc["status"]="200"

//        var json = {"\name\": \"Mohit\", \"address\": \"Adree City, Sector 52\", \"designation\": \"Senior Software Developer"}
        var jsonString= JSON.serialize(doc)
        var employees=objectMapper.readValue(jsonString,Employees::class.java)
        list.add(employees)
        return list
    }

}
