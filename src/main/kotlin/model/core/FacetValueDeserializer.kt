package model.core

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.IOException


class FacetValueDeserializer : StdDeserializer<FacetValue?>(FacetValue::class.java) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): FacetValue {
        val mapper = jsonParser.codec as ObjectMapper
        val root = mapper.readTree<ObjectNode>(jsonParser)
        // Determine the type based on existence of certain fields
        val type: Class<*>
        type = if (root.has("start")) {
            FacetRange::class.java
        } else if (root.has("cat_id")) {
            FacetFieldsCategory::class.java
        } else {
            FacetFields::class.java
        }
        return mapper.treeToValue(root, type) as FacetValue
    }
}