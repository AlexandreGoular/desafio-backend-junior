package desafio_backend.junior.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaDto(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull Boolean realizacao,
        @NotBlank String prioridade
        ) {
}
