package br.com.vaidaruim.gs3.core.service.mapper;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import org.springframework.stereotype.Component;

@Component
public class FarmacoMapper {

    public Farmaco toEntity(FarmacoDTO farmacoDTO) {
        Farmaco farmaco = new Farmaco();
        farmaco.setNomeDaSubstancia(farmacoDTO.nomeDaSubstancia());
        farmaco.setCruzamentos(farmacoDTO.cruzamentos());
        return farmaco;
    }

    public FarmacoDTO toDTO(Farmaco farmaco) {
        return new FarmacoDTO(farmaco.getFarmacoId(),farmaco.getNomeDaSubstancia(), farmaco.getCruzamentos());
    }
}
