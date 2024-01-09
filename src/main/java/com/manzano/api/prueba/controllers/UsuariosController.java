package com.manzano.api.prueba.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manzano.api.prueba.models.Usuario;
import com.manzano.api.prueba.services.IService;

@RestController
@RequestMapping("api/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {
	
	//inyectar la dependencia del servicio
	@Autowired
	IService<Usuario, Usuario> usuariosService;
	
	@PostMapping
	public Map<String, String> guardar (@RequestBody Usuario c){
		usuariosService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Usuario guardado correctamente");
		return respuesta;
	}
	
	@GetMapping
	public List<Usuario> listar(){
		return usuariosService.listar();
	}
	
	@GetMapping("/obtener/{id}")
	public Usuario obtenerPorId(@PathVariable(name = "id")Long id) {
		Optional<Usuario> usuario = usuariosService.getById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/eliminar")
	public Map<String, String> eliminar(@RequestParam(name = "id") Long id){
		usuariosService.eliminar(id);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Usuario eliminado correctamente");
		return respuesta;
	}
	
	@PutMapping("/actualizar/{id}")
	public Map<String, String> actualizar(@RequestBody Usuario c, @PathVariable(name = "id") Long id){
		Optional<Usuario> usuario = usuariosService.getById(id);
		if(usuario.isPresent()) {
			c.setId(id);
			usuariosService.guardar(c);
		}else {
			throw new RuntimeException("El usuario no existe en la base de datos");
		}
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Usuario actualizado correctamente");
		return respuesta;
	}

}
