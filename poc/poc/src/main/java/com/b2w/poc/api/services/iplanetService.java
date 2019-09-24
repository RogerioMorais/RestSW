package com.b2w.poc.api.services;

import com.b2w.poc.api.document.planet;

import java.util.List;
import java.util.Optional;

public interface iplanetService {
    List<planet>listarTodos();
    Optional<planet> listarPorId(String id);
    Optional<planet> listarPorNome(String nome);
    planet Cadastrar(planet p);
    planet Atualizar (planet p);
    void remover(String id);

}
