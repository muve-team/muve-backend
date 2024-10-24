package kr.muve.common.domain.user.password;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PasswordDeserializer extends JsonDeserializer<Password> {
    @Override
    public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new Password(p.getText());
    }
}