package com.manzano.api.prueba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manzano.api.prueba.daos.IUsuariosDao;
import com.manzano.api.prueba.models.Usuario;

@Service
public class UsuariosService implements IService<Usuario, Usuario>{
	
	//Inyectar la dependencia 
	
	@Autowired
	private IUsuariosDao usuariosDao;

	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = (List<Usuario>) usuariosDao.findAll();
		return usuarios;
	}

	@Override
	public Optional<Usuario> getById(Long id) {
		Optional<Usuario> usuario = usuariosDao.findById(id);
		return usuario;
	}

	@Override
	public void guardar(Usuario t) {
		
		this.usuariosDao.save(t);
		
	}

	@Override
	public void eliminar(Long id) {
		
		this.usuariosDao.deleteById(id);
		
	}

}
