package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.dtos.ImovelDTOResposta;
import com.example.projeto.dtos.UserDTOResposta;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.ImovelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/imoveis")
public class ImovelController {

	@Autowired
	private ImovelService service;

	// @RequestMapping(method = RequestMethod.GET)
	// public ResponseEntity<List<ImovelModel>> getAll() {
	// 	List<ImovelModel> list = service.getAll();
	// 	return ResponseEntity.status(HttpStatus.OK).body(list);
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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ImovelDTOResposta> insert(@RequestBody ImovelDTO dto) {
		ImovelModel imovel = service.transformaParaObjeto(dto);
		service.insert(imovel);
		
		return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel), HttpStatus.CREATED);
	}

	

}
