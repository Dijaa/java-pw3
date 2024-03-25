package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.dtos.ImovelDTOResposta;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.service.ImovelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/imoveis")
public class ImovelController {

	@Autowired
	private ImovelService service;

	// @RequestMapping(method = RequestMethod.GET)
	// public ResponseEntity<List<ImovelModel>> getAll() {
	// List<ImovelModel> list = service.getAll();
	// return ResponseEntity.status(HttpStatus.OK).body(list);
	// }

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ImovelDTOResposta>> getAll() {
		List<ImovelModel> list = service.getAll();

		List<ImovelDTOResposta> listaDtos = list.stream().map(imovel -> new ImovelDTOResposta(imovel))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(listaDtos);

	}

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<Void> insert(@RequestBody ImovelDTO dto) {
	// 	ImovelModel imovel = service.transformaParaObjeto(dto);
	// 	service.insert(imovel);
	// 	return new ResponseEntity(imovel, HttpStatus.CREATED);
	// }

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<ImovelDTOResposta> insert(@RequestBody ImovelDTO dto) {
	// 	ImovelModel imovel = service.transformaParaObjeto(dto);
	// 	service.insert(imovel);

	// 	return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel),
	// 			HttpStatus.CREATED);
	// }




	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ImovelDTOResposta> insert(
			@RequestParam("descricao") String descricao,
			@RequestParam("quartos") Integer quartos,
			@RequestParam("vagas") Integer vagas,
			@RequestParam("usuario_id") Integer usuarioId,
			@RequestParam("imagem") MultipartFile imagem) {

		ImovelDTO imovelDTO = new ImovelDTO();
		imovelDTO.setDescricao(descricao);
		imovelDTO.setQuartos(quartos);
		imovelDTO.setVagas(vagas);
		imovelDTO.setUsuario_id(usuarioId);

		ImovelModel imovel = service.transformaParaObjeto(imovelDTO);

		// if (!imovel.getUserModel().isAdmin()) {
		// 	throw new ResponseStatusException(HttpStatus.FORBIDDEN,
		// 			"Você precisa ser um administrador para realizar esta ação");
		// }
		String urlImagem = service.uploadImagem(imagem);

		imovel.setImagem(urlImagem);

		service.insert(imovel);

		return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel), HttpStatus.CREATED);
	}

}
