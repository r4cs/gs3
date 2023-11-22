package br.com.vaidaruim.gs3.core.service.mapper;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.service.FarmacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmacoMapper {

    private final FarmacoService service;

    @Autowired
    public FarmacoMapper(FarmacoService service) {
        this.service = service;
    }

    public Farmaco toEntity(FarmacoDTO farmacoDTO) {
        Farmaco farmaco = new Farmaco();
        farmaco.setNomeDaSubstancia(farmacoDTO.nomeDaSubstancia());
        farmaco.setCruzamentos(farmacoDTO.cruzamentos());
        return farmaco;
    }

    public FarmacoDTO toDTO(Farmaco farmaco) {
        return new FarmacoDTO(farmaco.getNomeDaSubstancia(), farmaco.getCruzamentos());
    }
}
