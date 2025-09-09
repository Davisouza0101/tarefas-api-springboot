package com.example.tarefas;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin
public class TarefaController {

    private final TarefaRepository repository;

    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa) {
        Tarefa salva = repository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public List<Tarefa> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa atualizada) {
        return repository.findById(id).map(tarefa -> {
            tarefa.setNome(atualizada.getNome());
            tarefa.setDataEntrega(atualizada.getDataEntrega());
            tarefa.setResponsavel(atualizada.getResponsavel());
            return repository.save(tarefa);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
