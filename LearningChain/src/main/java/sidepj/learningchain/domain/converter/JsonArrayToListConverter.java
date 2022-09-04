package sidepj.learningchain.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonArrayToListConverter implements AttributeConverter<List<String>, String> {
  private static final ObjectMapper mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    try {
      return mapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    try {
      return mapper.readValue(dbData, List.class);
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
}
