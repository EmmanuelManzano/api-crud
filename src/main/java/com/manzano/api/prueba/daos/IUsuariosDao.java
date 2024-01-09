package com.manzano.api.prueba.daos;

import org.springframework.data.repository.CrudRepository;

import com.manzano.api.prueba.models.Usuario;

public interface IUsuariosDao extends CrudRepository<Usuario, Long> {

}
