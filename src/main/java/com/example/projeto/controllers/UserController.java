package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.UserDTO;
import com.example.projeto.dtos.UserDTOResposta;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	/*
	 * Método sem DTO
	 * 
	 * @RequestMapping(method = RequestMethod.GET)
	 * public ResponseEntity<List<UserModel>> getAllUsers() {
	 * List<UserModel> list = service.getAll();
	 * return ResponseEntity.status(HttpStatus.OK).body(list);
	 * }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTOResposta>> getAllUsers() {
		List<UserModel> listaNormal = service.getAll();
		List<UserDTOResposta> listaDtos = listaNormal.stream().map(usuario -> new UserDTOResposta(usuario))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listaDtos);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> find(@PathVariable Integer id) {
		UserModel model = service.find(id);
		return ResponseEntity.status(HttpStatus.OK).body(model);
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST)
	 * public ResponseEntity<Void> insert(@RequestBody UserDTO model){
	 * model = service.insert(model);
	 * //URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (model.getId()).toUri();
	 * //return ResponseEntity.created(uri).build();
	 * return new ResponseEntity(model, HttpStatus.CREATED);
	 * }
	 */

	/*
	 * @RequestMapping(method = RequestMethod.POST)
	 * public ResponseEntity<Void> insert(@RequestBody UserDTO dto){
	 * UserModel model = service.insert(dto.transformaParaObjeto());
	 * return new ResponseEntity(model, HttpStatus.CREATED);
	 * }
	 */

	 
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTOResposta> salvar(@Valid @RequestBody UserDTO dto) {
		UserModel model = service.insert(dto.transformaParaObjeto());
		return new ResponseEntity<>(UserDTOResposta.transformaEmDTO(model), HttpStatus.CREATED);
	}


	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	 * public ResponseEntity<Void> update(@RequestBody UserModel
	 * model, @PathVariable Integer id) {
	 * model.setId(id);
	 * model = service.update(model);
	 * return ResponseEntity.noContent().build();
	 * }
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO dto, @PathVariable Integer id) {
		UserModel model = dto.transformaParaObjeto();
		model.setId(id);
		model = service.update(model);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// método para fazer a requisição por paginação
	@RequestMapping(value = "page", method = RequestMethod.GET)
	public ResponseEntity<Page<UserDTOResposta>> getAllUsersByPage(
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "lines", defaultValue = "10") Integer linhas,
			@RequestParam(value = "orderBy", defaultValue = "nome") String ordem,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcao) {
		Page<UserModel> listaNormal = service.findPage(pagina, linhas, ordem, direcao);

		Page<UserDTOResposta> listaDtos = listaNormal.map(usuario -> new UserDTOResposta(usuario));

		return ResponseEntity.status(HttpStatus.OK).body(listaDtos);
	}

}
