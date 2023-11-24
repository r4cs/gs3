package br.com.vaidaruim.gs3.core.entity.DTO;

import br.com.vaidaruim.gs3.core.entity.Classificacao;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

import java.util.HashMap;
import java.util.Map;

public record FarmacoDTO (
        @Schema(description = "farmacoId", example = "null")
        Long id,
        @NotNull(message = "O nome da substância não pode ser nulo")
        @NotBlank(message = "O nome da substância não pode estar em branco")
        @Schema(description = "nomeDaSubstancia", example = "paracetamol")
        String nomeDaSubstancia,

        @Schema(description = "cruzamentos", example = "{\"morfina\": \"AMARELO\"}")
        Map<String, Classificacao> cruzamentos
){

    public FarmacoDTO(Farmaco farmaco) {
        this(
                farmaco.getFarmacoId(),
                farmaco.getNomeDaSubstancia(),
                farmaco.getCruzamentos()
        );
    }
}
