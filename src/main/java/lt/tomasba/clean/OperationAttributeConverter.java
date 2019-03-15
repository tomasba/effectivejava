package lt.tomasba.clean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * The ENUM conversion automatically applied in JPA with custom converter.
 */
@Converter(autoApply = true)
public class OperationAttributeConverter implements AttributeConverter<Operation, String> {

    @Override
    public String convertToDatabaseColumn(Operation objData) {
        return objData.toDatabaseValue();
    }

    @Override
    public Operation convertToEntityAttribute(String dbData) {
        return Operation.fromString(dbData).orElse(Operation.ADD);
    }
}
