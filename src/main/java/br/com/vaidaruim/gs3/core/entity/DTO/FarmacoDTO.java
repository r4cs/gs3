package br.com.vaidaruim.gs3.core.entity.DTO;

import br.com.vaidaruim.gs3.core.entity.Classificacao;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

public record FarmacoDTO (
        @NotNull(message = "O nome da substância não pode ser nulo")
        @NotBlank(message = "O nome da substância não pode estar em branco")
        String nomeDaSubstancia,

//        @NotNull(message = "Os cruzamentos não podem ser nulos"2)
//        @NotEmpty(message = "Os cruzamentos não podem estar vazios")
        Map<String, Classificacao> cruzamentos
){

//    public FarmacoDTO() {
//        this(new Farmaco());
//        System.out.println("Construtor vazio acessado em FarmacoDTO");
//    }

    public FarmacoDTO() {
        this("", new HashMap<>());
        System.out.println("Construtor vazio acessado em FarmacoDTO");
    }


    public FarmacoDTO(Farmaco farmaco) {
        this(
                farmaco.getNomeDaSubstancia(),
                farmaco.getCruzamentos()
        );
        System.out.println("Construtor populado acessado em FarmacoDTO");
    }
}
