package nl.gerimedica.mapper;

import java.util.List;
import nl.gerimedica.entity.SymptomEntity;
import nl.gerimedica.model.SymptomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SymptomMapper {
    SymptomMapper INSTANCE = Mappers.getMapper(SymptomMapper.class);

    SymptomDto entityToDto(SymptomEntity entity);
    List<SymptomDto> entityToDto(List<SymptomEntity> entities);
}
