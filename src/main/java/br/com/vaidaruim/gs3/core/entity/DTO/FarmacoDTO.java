package br.com.vaidaruim.gs3.core.entity.DTO;

import br.com.vaidaruim.gs3.core.entity.Classificacao;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.HashMap;
import java.util.Map;

public record FarmacoDTO (
        Long id,
        @NotNull(message = "O nome da substância não pode ser nulo")
        @NotBlank(message = "O nome da substância não pode estar em branco")
        String nomeDaSubstancia,

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
