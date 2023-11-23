package br.com.vaidaruim.gs3.core.service;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.repository.FarmacoRepo;
import br.com.vaidaruim.gs3.core.service.mapper.FarmacoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

//    public List<Farmaco> lerTodosFarmacos(Pageable pageable) {
//        Pageable pageableConfigurado = PageRequest.of(0, 10, Sort.by("farmaco_id").ascending());
//        return farmacoRepo.findAll(pageableConfigurado).getContent();
//    }

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
}
