package br.com.vaidaruim.gs3.core.entity;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Farmaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmaco_id;

    private String nomeDaSubstancia;

    @ElementCollection
    @CollectionTable(name = "farmaco_cruzamentos", joinColumns = @JoinColumn(name="farmaco_id"))
    @Enumerated(EnumType.STRING)
    private Map<String, Classificacao> cruzamentos = new HashMap<>();

    public Farmaco() {}

    public Farmaco(FarmacoDTO dto) {
        this.farmaco_id = dto.id();
        this.nomeDaSubstancia = dto.nomeDaSubstancia();
        this.cruzamentos = dto.cruzamentos();
    }

    public Long getFarmacoId() {
        return farmaco_id;
    }


    public String getNomeDaSubstancia() {
        return nomeDaSubstancia;
    }

    public void setNomeDaSubstancia(String name) {
        this.nomeDaSubstancia = name;
    }

    public Map<String, Classificacao> getCruzamentos() {
        return cruzamentos;
    }

    public void setCruzamentos(Map<String, Classificacao> cruzamentos) {
        this.cruzamentos = cruzamentos;
    }

    @Override
    public String toString() {
        return "Farmaco{" +
                "\nid=" + farmaco_id +
                "\nnome da substância='" + nomeDaSubstancia + '\'' +
                "\ncruzamentos=" + cruzamentos +
                "\n}";
    }
}
