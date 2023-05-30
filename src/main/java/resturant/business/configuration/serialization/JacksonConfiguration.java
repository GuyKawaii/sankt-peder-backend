package resturant.business.configuration.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        module.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
        objectMapper.registerModule(module);

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return objectMapper;
    }

}

