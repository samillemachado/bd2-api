package com.growdev.crud.resouces;

import com.growdev.crud.dto.RecadoInDto;
import com.growdev.crud.dto.RecadoOutDto;
import com.growdev.crud.services.RecadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recados")
@CrossOrigin("*")
public class RecadoController {
    @Autowired
    private RecadoService service;

    @GetMapping
    public ResponseEntity<List<RecadoOutDto>> getAll(){
        List<RecadoOutDto> recadosOutDto = service.getAll();
        return ResponseEntity.ok().body(recadosOutDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecadoOutDto> getById(@PathVariable Long id){
        RecadoOutDto recadoOutDto = service.getById(id);
        return ResponseEntity.ok().body(recadoOutDto);
    }

    @PostMapping
    public ResponseEntity<RecadoOutDto> createOne(@RequestBody RecadoInDto recadoInDto){
        RecadoOutDto recadoOutDto1 = service.createOne(recadoInDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recadoOutDto1.getRecadoId()).toUri();
        return ResponseEntity.created(uri).body(recadoOutDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecadoOutDto> updateOne(@PathVariable Long id, @RequestBody RecadoInDto recadoInDto){
        RecadoOutDto recadoOutDto1 = service.update(id, recadoInDto);
        return ResponseEntity.ok().body(recadoOutDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id){
        service.deleteOne(id);
        return ResponseEntity.ok().build();
    }

}
