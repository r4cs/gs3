package br.com.vaidaruim.gs3.core.entity.DTO;

import br.com.vaidaruim.gs3.core.entity.Classificacao;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

public record FarmacoDTO (
        Long id,
        @NotNull(message = "O nome da substância não pode ser nulo")
        @NotBlank(message = "O nome da substância não pode estar em branco")
        String nomeDaSubstancia,

        Map<String, Classificacao> cruzamentos
){

    // desconsiderar: comentário abaixo válido apenas qdo thymeleaf está rodando junto
    // o sistema me obriga a ter que criar um construtor vazio.
    // porém esse dto sempre fica como primário e o dto com atributos (abaixo),
    // que é o que deve ser implementado nunca é chamado.
    public FarmacoDTO() {
        this(null,"", new HashMap<>());
        System.out.println("Construtor vazio acessado em FarmacoDTO");
    }


    public FarmacoDTO(Farmaco farmaco) {
        this(
                farmaco.getFarmacoId(),
                farmaco.getNomeDaSubstancia(),
                farmaco.getCruzamentos()
        );
        System.out.println("Construtor populado acessado em FarmacoDTO");
    }
}
