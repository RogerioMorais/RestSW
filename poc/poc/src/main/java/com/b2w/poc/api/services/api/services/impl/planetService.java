package com.b2w.poc.api.services.api.services.impl;

import com.b2w.poc.api.document.planet;
import com.b2w.poc.api.repository.iplanetRepository;
import com.b2w.poc.api.services.iplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class planetService implements iplanetService {
    @Autowired
    private iplanetRepository repotory;

    @Override
    public List<planet> listarTodos() {
        return this.repotory.findAll();
    }

    @Override
    public Optional<planet> listarPorId(String id) {
        return this.repotory.findById(id);
    }

    @Override
    public Optional<planet> listarPorNome(String nome){
        return this.repotory.listarPorNome(nome);
    }

    @Override
    public planet Cadastrar(planet p) {
        return this.repotory.save(p);
    }

    @Override
    public planet Atualizar(planet p) {
        return this.repotory.save(p);
    }

    @Override
    public void remover(String id) {
        this.repotory.deleteById(id);
    }
}
