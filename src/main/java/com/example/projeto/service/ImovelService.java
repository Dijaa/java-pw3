package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.ImovelRepository;
import com.example.projeto.repository.UserRepository;
import com.example.projeto.service.exceptions.UnauthorizedException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${imgbb.api.key}")
    private String apiKey;

    public List<ImovelModel> getAll() {
        List<ImovelModel> list = imovelRepository.findAll();
        return list;
    }

    public ImovelModel find(Integer id) {
        Optional<ImovelModel> model = imovelRepository.findById(id);
        return model.orElse(null);
    }

    // public ImovelModel insert(ImovelModel model) {
    //     return imovelRepository.save(model);
    // }

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



    public String uploadImagem(MultipartFile imagem) {
		try {


			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			// Preparando os dados para o envio
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("key", this.apiKey);
			body.add("image", new ByteArrayResource(imagem.getBytes()) {
				@Override
				public String getFilename() {
					return imagem.getOriginalFilename();
				}
			});

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

			// Fazendo a requisição
			String imgBBUrl = "https://api.imgbb.com/1/upload";
			ResponseEntity<String> response = restTemplate.postForEntity(imgBBUrl, requestEntity, String.class);

			String resposta = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(resposta);
			return rootNode.path("data").path("url").asText();

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	} 

}
