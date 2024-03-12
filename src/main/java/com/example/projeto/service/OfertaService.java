package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.models.OfertaModel;
import com.example.projeto.repository.OfertaRepository;


@Service
public class OfertaService {

    @Autowired
    private OfertaRepository repository;

    public List<OfertaModel> getAll() {
        try{
        List<OfertaModel> list = repository.findAll();
        return list;
    }
    catch(Exception e){
        System.out.println(e.toString());
        return null;
    }
    }

    public OfertaModel find(Integer id) {
        Optional<OfertaModel> model = repository.findById(id);
        return model.orElse(null);
    }

    public OfertaModel insert(OfertaModel model) {
        return repository.save(model);
    }

    public OfertaModel update(OfertaModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id){
       OfertaModel model = find(id);
       try{
            repository.deleteById(id);
       }
       catch(Exception e){
            throw new DataIntegrityViolationException("Não foi possível exlcluir");
       }
    }

    public Page<OfertaModel>  findPage(Integer pagina, Integer linhas, String ordem, String direcao){
        PageRequest request = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
        return repository.findAll(request);
    }

}
