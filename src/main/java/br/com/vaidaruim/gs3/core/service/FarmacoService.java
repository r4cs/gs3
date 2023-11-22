package br.com.vaidaruim.gs3.core.service;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.repository.FarmacoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmacoService {

    private final FarmacoRepo farmacoRepo;

    @Autowired
    public FarmacoService(FarmacoRepo farmacoRepo) {
        this.farmacoRepo = farmacoRepo;
    }

    public void cadastrarFarmaco(FarmacoDTO dto) {
        System.out.println("dto: " + dto);
        Farmaco farmaco = new Farmaco(dto);
        System.out.println("Farmaco: " + farmaco);
        farmacoRepo.save(farmaco);
    }

    public Optional<Farmaco>  lerFarmacoPorId(Long id) {
        return farmacoRepo.findById(id);
    }

    public List<Farmaco> lerTodosFarmacos() {
        return farmacoRepo.findAll();
    }

//    public void atualizarFarmaco(Long id, Farmaco farmacoAtualizado) {
//        Optional<Farmaco> farmacoAntigo = farmacoRepo.findById(id);
//
//        Farmaco farmaco = farmacoAntigo.get();
//
//        if (farmaco.getNomeDaSubstancia() != null) {
//            farmaco.setNomeDaSubstancia(farmacoAtualizado.getNomeDaSubstancia());
//        }
//        else if (farmaco.getCruzamentos() != null) {
//            farmaco.setCruzamentos(farmacoAtualizado.getCruzamentos());
//        }
//    }

    public void atualizarFarmaco(Long id, FarmacoDTO farmacoAtualizadoDto) {
        Farmaco farmacoAntigo = farmacoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Farmaco não encontrado Id: " + id));

        if (farmacoAtualizadoDto.nomeDaSubstancia() != null) {
            farmacoAntigo.setNomeDaSubstancia(farmacoAtualizadoDto.nomeDaSubstancia());
        }

        if (farmacoAtualizadoDto.cruzamentos() != null) {
            farmacoAntigo.setCruzamentos(farmacoAtualizadoDto.cruzamentos());
        }

        farmacoRepo.save(farmacoAntigo);
    }


    public void deletarFarmaco(Long id) {
        farmacoRepo.deleteById(id);
    }


}
