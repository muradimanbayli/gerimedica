package nl.gerimedica.service;

import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import nl.gerimedica.entity.SymptomEntity;
import nl.gerimedica.exception.NotFoundException;
import nl.gerimedica.mapper.SymptomMapper;
import nl.gerimedica.model.SymptomDto;
import nl.gerimedica.repository.SymptomRepository;
import nl.gerimedica.service.util.CSVReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SymptomService {
    private final SymptomRepository symptomRepository;
    private final CSVReader csvReader;

    public List<SymptomDto> uploadData(InputStream inputStream) {
        List<SymptomEntity> entities = csvReader.tryParseCSV(inputStream);
        List<SymptomEntity> savedEntities = symptomRepository.saveAll(entities);
        return SymptomMapper.INSTANCE.entityToDto(savedEntities);
    }

    public List<SymptomDto> getAllSymptom() {
        List<SymptomEntity> entities = symptomRepository.findAll();
        return SymptomMapper.INSTANCE.entityToDto(entities);
    }

    public SymptomDto getSymptomByCode(String code) {
        SymptomEntity entity = symptomRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Not found code:"+code));
        return SymptomMapper.INSTANCE.entityToDto(entity);
    }

    public void deleteAll() {
        symptomRepository.deleteAll();
    }
}
