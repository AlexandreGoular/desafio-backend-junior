package desafio_backend.junior.controller;


import desafio_backend.junior.dto.TarefaDto;
import desafio_backend.junior.model.TarefaModel;
import desafio_backend.junior.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    TarefaRepository tarefaRepository;

    @GetMapping("/tarefas")
    public Iterable<TarefaModel> tarefaModels() {
        return tarefaRepository.findAll();
    }

    @PostMapping("/tarefas")
    public TarefaModel tarefaModel(@RequestBody @Valid TarefaDto tarefaDto) {
        var tarefaModel = new TarefaModel();
        BeanUtils.copyProperties(tarefaDto, tarefaModel);
        return tarefaRepository.save(tarefaModel);
    }


    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Object> putTarefa(@PathVariable(value = "id") UUID id, @RequestBody @Valid TarefaDto tarefaDto) {
        Optional<TarefaModel> tarefaModelOptional = tarefaRepository.findById(id);

        if (tarefaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
        }

        var tarefaModel = tarefaModelOptional.get();
        BeanUtils.copyProperties(tarefaDto, tarefaModel);
        tarefaModel.setId(id);
        TarefaModel updatedTarefa = tarefaRepository.save(tarefaModel);

        return ResponseEntity.ok(updatedTarefa);
    }

    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Object> deleteTarefa(@PathVariable(value = "id") UUID id) {
        Optional<TarefaModel> tarefaModel = tarefaRepository.findById(id);

        if (tarefaModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID nao encontrado");
        }
        tarefaRepository.delete(tarefaModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluida com sucesso");
    }
}
