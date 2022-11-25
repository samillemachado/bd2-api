package com.growdev.crud.services;

import com.growdev.crud.dto.UserInDto;
import com.growdev.crud.dto.UserOutDto;
import com.growdev.crud.entities.User;
import com.growdev.crud.repositories.UserRepository;
import com.growdev.crud.services.exception.BadRequestException;
import com.growdev.crud.services.exception.DatabaseException;
import com.growdev.crud.services.exception.EntityNotFoundIdException;
import com.growdev.crud.services.exception.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserOutDto> getAll(){
        try{
            List<User> listUsers = repository.findAll();
            return listUsers.stream().map(UserOutDto::new).collect(Collectors.toList());
        } catch (InternalServerErrorException e){
            throw new InternalServerErrorException("Erro no servidor");
        }
    }

    @Transactional(readOnly = true)
    public UserOutDto getById(Long id){
       User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundIdException("User not found"));
       return new UserOutDto(user);
    }

    public UserOutDto createOne(UserInDto userInDto){
        try{
            User newUser = new User();
            newUser.setName(userInDto.getName());
            newUser.setEmail(userInDto.getEmail());
            newUser.setPass(userInDto.getPass());

            newUser = repository.save(newUser);

            return new UserOutDto(newUser);

        } catch (BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }
    }

    public void deleteOne(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundIdException("User not found");
        }catch (DataIntegrityViolationException d){
            throw new DatabaseException("Violação de integridade");
        }
    }

    public UserOutDto update(Long id, UserInDto userInDto){
        try{
            User user = repository.findById(id).get();
            user.setName(userInDto.getName());
            user.setEmail(userInDto.getEmail());
            user.setPass(user.getPass());
            user = repository.save(user);

            return new UserOutDto(user);

        } catch (BadRequestException e){
            throw new BadRequestException("Bad request");
        } catch (EntityNotFoundIdException e) {
            throw new EntityNotFoundIdException("User not found" + id);
        }
    }

}
