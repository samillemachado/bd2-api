package com.growdev.crud.resouces;

import com.growdev.crud.dto.UserInDto;
import com.growdev.crud.dto.UserOutDto;
import com.growdev.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserOutDto>> getAll(){
    List<UserOutDto> usersOutDto = service.getAll();
    return ResponseEntity.ok().body(usersOutDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutDto> getById(@PathVariable Long id){
        UserOutDto userOutDto = service.getById(id);
        return ResponseEntity.ok().body(userOutDto);
    }

    @PostMapping
    public ResponseEntity<UserOutDto> createOne(@RequestBody UserInDto userInDto){
        UserOutDto userOutDto1 = service.createOne(userInDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userOutDto1.getUserId()).toUri();
        return ResponseEntity.created(uri).body(userOutDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutDto> updateOne(@PathVariable Long id, @RequestBody UserInDto userInDto){
        UserOutDto userOutDto1 = service.update(id, userInDto);
        return ResponseEntity.ok().body(userOutDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id){
        service.deleteOne(id);
        return ResponseEntity.ok().build();
    }

}
