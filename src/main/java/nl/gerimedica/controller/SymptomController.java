package nl.gerimedica.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import nl.gerimedica.exception.UnsupportedFormatException;
import nl.gerimedica.model.SymptomDto;
import nl.gerimedica.service.SymptomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class SymptomController {
    private final SymptomService symptomService;

    @PostMapping
    public ResponseEntity<List<SymptomDto>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(!"text/csv".equals(file.getContentType())) {
            throw new UnsupportedFormatException(file.getContentType());
        }
        return ResponseEntity.ok(symptomService.uploadData(file.getInputStream()));
    }

    @GetMapping
    public ResponseEntity<List<SymptomDto>> getAll() {
        return ResponseEntity.ok(symptomService.getAllSymptom());
    }

    @GetMapping("/{code}")
    public ResponseEntity<SymptomDto> getSingle(@PathVariable("code") String code){
       return ResponseEntity.ok(symptomService.getSymptomByCode(code));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAll() {
        symptomService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
