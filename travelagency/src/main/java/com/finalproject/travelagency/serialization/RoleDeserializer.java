package com.finalproject.travelagency.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.finalproject.travelagency.model.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class RoleDeserializer extends StdDeserializer<Role> {

    public RoleDeserializer() {
        this(null);
    }

    public RoleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Role deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String authority = jp.readValueAs(String.class);

        // Implement logic to map authority to Role enum values
        if ("ROLE_USER".equals(authority)) {
            return Role.USER;
        } else if ("ROLE_ADMIN".equals(authority)) {
            return Role.ADMIN;
        } else {
            throw new IllegalArgumentException("Unknown role: " + authority);
        }
    }
}