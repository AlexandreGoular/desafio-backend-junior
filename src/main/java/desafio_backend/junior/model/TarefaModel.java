package desafio_backend.junior.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "TB_tarefas")
public class TarefaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(name = "nome_tarefa" ,length = 50)
    private String nome;

    @Column(name = "descricao_tarefa", length = 200)
    private String descricao;

    @Column(name = "tarefa_realizada")
    private Boolean realizacao;

    @Column(name = "prioridade_tarefa")
    private String prioridade;
}
