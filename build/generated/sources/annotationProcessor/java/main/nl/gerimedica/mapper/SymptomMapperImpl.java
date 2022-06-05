package nl.gerimedica.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import nl.gerimedica.entity.SymptomEntity;
import nl.gerimedica.model.SymptomDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-05T15:28:35+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 13.0.2 (AdoptOpenJDK)"
)
public class SymptomMapperImpl implements SymptomMapper {

    @Override
    public SymptomDto entityToDto(SymptomEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SymptomDto symptomDto = new SymptomDto();

        symptomDto.setSource( entity.getSource() );
        symptomDto.setCodeListCode( entity.getCodeListCode() );
        symptomDto.setDisplayValue( entity.getDisplayValue() );
        symptomDto.setCode( entity.getCode() );
        symptomDto.setLongDescription( entity.getLongDescription() );
        symptomDto.setFromDate( entity.getFromDate() );
        symptomDto.setToDate( entity.getToDate() );
        symptomDto.setSortingPriority( entity.getSortingPriority() );

        return symptomDto;
    }

    @Override
    public List<SymptomDto> entityToDto(List<SymptomEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SymptomDto> list = new ArrayList<SymptomDto>( entities.size() );
        for ( SymptomEntity symptomEntity : entities ) {
            list.add( entityToDto( symptomEntity ) );
        }

        return list;
    }
}
