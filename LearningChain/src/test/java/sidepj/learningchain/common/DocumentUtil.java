package sidepj.learningchain.common;

import java.util.List;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.restdocs.snippet.Attributes.Attribute;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class DocumentUtil {

  public static FieldDescriptor createRequestField(String path, Object type, Object description, Class<?> clazz) {
    Attribute att = Attributes.key("constraint").value(getNullable(clazz, path));
    return fieldWithPath(path).type(type).description(description).attributes(att);
  }

  public static FieldDescriptor createResponseField(String path, Object description) {
    return fieldWithPath(path).description(description);
  }

  public static String getNullable(Class<?> clazz, String field) {
    ConstraintDescriptions contentsRequestConstraint = new ConstraintDescriptions(clazz);
    List<String> nameDescription = contentsRequestConstraint.descriptionsForProperty(field);

    return nameDescription.size() > 0 ? nameDescription.get(0) : "";
  }

}
