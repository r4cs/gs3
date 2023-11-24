package br.com.vaidaruim.gs3.core.service;

import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.repository.FarmacoRepo;
import br.com.vaidaruim.gs3.core.service.mapper.FarmacoMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class FarmacoService {

    private final FarmacoRepo farmacoRepo;
    private final FarmacoMapper mapper;

    @Autowired
    public FarmacoService(FarmacoRepo farmacoRepo, FarmacoMapper mapper) {
        this.farmacoRepo = farmacoRepo;
        this.mapper = mapper;
    }

    @Transactional
    public FarmacoDTO cadastrarFarmaco(FarmacoDTO dto) {
        Farmaco farmaco = new Farmaco(dto);
        farmacoRepo.save(farmaco);
        return mapper.toDTO(farmaco);
    }

    public Optional<Farmaco> lerFarmacoPorId(Long id) {
        return farmacoRepo.findById(id);
    }


    public Page<Farmaco> lerTodosFarmacos(Pageable pageable) {
        return farmacoRepo.findAll(pageable);
    }


    @Transactional
    public FarmacoDTO atualizarFarmaco(Long id, FarmacoDTO farmacoAtualizadoDto) {
        Farmaco farmacoAntigo = farmacoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Farmaco não encontrado Id: " + id));

        if (mapper.toEntity(farmacoAtualizadoDto).getNomeDaSubstancia() != null) {
            farmacoAntigo.setNomeDaSubstancia(farmacoAtualizadoDto.nomeDaSubstancia());
        }
        if (mapper.toEntity(farmacoAtualizadoDto).getCruzamentos() != null) {
            farmacoAntigo.setCruzamentos(farmacoAtualizadoDto.cruzamentos());
        }
        farmacoRepo.save(farmacoAntigo);
        return mapper.toDTO(farmacoAntigo);
    }

    public String deletarFarmaco(Long id) {
        farmacoRepo.deleteById(id);
        return "Fármaco deletado";
    }

    public Optional<Farmaco>  lerFarmacoPorNomeDaSubstancia(String nomeDaSubstancia) {
        return farmacoRepo.findFarmacoByNomeDaSubstancia(nomeDaSubstancia);
    }
}
