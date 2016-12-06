package domain.servicesRest.serialization;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import domain.servicesRest.DateTimeTransformer;

/**
 * Created by prospero on 6/2/16.
 */
public class JodaDateTimeDeserializer extends StdScalarDeserializer<ReadableInstant> {

    public JodaDateTimeDeserializer() {
        super(DateTime.class);

    }

    protected JodaDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    protected JodaDateTimeDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected JodaDateTimeDeserializer(StdScalarDeserializer<?> src) {
        super(src);
    }

    @Override
    public ReadableInstant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new DateTimeTransformer(p.getValueAsString()).getValue();
    }
}
