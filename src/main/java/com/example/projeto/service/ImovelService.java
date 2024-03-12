package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.ImovelRepository;
import com.example.projeto.repository.UserRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ImovelModel> getAll() {
        List<ImovelModel> list = imovelRepository.findAll();
        return list;
    }

    public ImovelModel find(Integer id) {
        Optional<ImovelModel> model = imovelRepository.findById(id);
        return model.orElse(null);
    }

    public ImovelModel insert(ImovelModel model) {
        return imovelRepository.save(model);
    }

    public ImovelModel update(ImovelModel model) {
        find(model.getId());
        return imovelRepository.save(model);
    }

    public void delete(Integer id) {
        ImovelModel model = find(id);
        try {
            imovelRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível exlcluir");
        }
    }

    public Page<ImovelModel> findPage(Integer pagina, Integer linhas, String ordem, String direcao) {
        PageRequest request = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordem);
        return imovelRepository.findAll(request);
    }

    public ImovelModel fromDto(ImovelDTO dto) {
        UserModel userModel = userRepository.findById(dto.getUsuario_id()).orElseThrow();

        ImovelModel imovel = new ImovelModel(dto.getDescricao(), dto.getQuartos(), dto.getVagas(), userModel);

        return imovelRepository.save(imovel);

    }

    public ImovelModel transformaParaObjeto(ImovelDTO imovelDTO) {
        UserModel userModel = userRepository.findById(imovelDTO.getUsuario_id()).orElseThrow();
        
        return new ImovelModel(
                imovelDTO.getId(),
                imovelDTO.getDescricao(),
                imovelDTO.getQuartos(),
                imovelDTO.getVagas(),
                userModel);
    }

}
