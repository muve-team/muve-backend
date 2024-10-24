package kr.muve.common.domain.user.password;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PasswordSerializer extends JsonSerializer<Password> {
    @Override
    public void serialize(Password password, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(password.getValue());
    }
}