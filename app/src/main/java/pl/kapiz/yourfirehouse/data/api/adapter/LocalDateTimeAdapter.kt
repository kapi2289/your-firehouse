package pl.kapiz.yourfirehouse.data.api.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDateTime

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    override fun write(writer: JsonWriter?, value: LocalDateTime?) {}

    override fun read(reader: JsonReader?): LocalDateTime? =
        LocalDateTime.parse(reader?.nextString())
}

