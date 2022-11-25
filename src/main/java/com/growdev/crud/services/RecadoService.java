package com.growdev.crud.services;

import com.growdev.crud.dto.*;
import com.growdev.crud.entities.Recado;
import com.growdev.crud.entities.User;
import com.growdev.crud.repositories.RecadoRepository;
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
public class RecadoService {

    @Autowired
    private RecadoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RecadoOutDto> getAll(){
        try{
            List<Recado> recadoList = repository.findAll();
            return recadoList.stream().map(RecadoOutDto::new).collect(Collectors.toList());
        } catch (InternalServerErrorException e){
            throw new InternalServerErrorException("Erro no servidor");
        }
    }

    @Transactional(readOnly = true)
    public RecadoOutDto getById(Long id){
        Optional<Recado> recado = repository.findById(id);
        Recado recado1 = recado.orElseThrow(() -> new EntityNotFoundIdException("Recado not found"));
        return new RecadoOutDto(recado1);
    }

    public RecadoOutDto createOne(RecadoInDto recadoInDto){
        try{
            Recado recado = new Recado();
            recado.setTitle(recadoInDto.getTitle());
            recado.setDescription(recadoInDto.getDescription());
            recado.setStatusRec(recadoInDto.getStatusRec());
            recado.setIsArquivado(recadoInDto.getIsArquivado());

            //busca o user na tabela, procura pelo id do dto
            User user = userRepository.findById(recadoInDto.getUserId()).get();
            recado.setUser(user);

            recado = repository.save(recado);

            return new RecadoOutDto(recado);

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

    public RecadoOutDto update(Long id, RecadoInDto recadoInDto) {
        //busquei o recado no banco
        Recado recado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundIdException("Recado não encontrado: " + id));

        recado.setTitle(recadoInDto.getTitle());
        recado.setDescription(recadoInDto.getDescription());
        recado.setStatusRec(recadoInDto.getStatusRec());
        recado.setIsArquivado(recadoInDto.getIsArquivado());

        //salvar o recado novamente no usuário
        repository.save(recado);

        return new RecadoOutDto(recado);
    }
}

