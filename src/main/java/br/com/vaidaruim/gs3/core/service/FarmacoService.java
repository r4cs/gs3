package br.com.vaidaruim.gs3.core.service;

import br.com.vaidaruim.gs3.core.entity.Classificacao;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.repository.FarmacoRepo;
import br.com.vaidaruim.gs3.core.service.mapper.FarmacoMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

import java.util.*;

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
        Farmaco farmaco = new Farmaco();
        farmaco.setNomeDaSubstancia(dto.nomeDaSubstancia().toUpperCase());

        Map<String, Classificacao> cruzamentosEmCaixaAlta = new HashMap<>();
        dto.cruzamentos().forEach((key, value) -> {
            cruzamentosEmCaixaAlta.put(key.toUpperCase(), value);
        });

        farmaco.setCruzamentos(cruzamentosEmCaixaAlta);

        farmacoRepo.save(farmaco);
//        atualizarCruzamentos(farmaco.getFarmacoId(), farmaco.getCruzamentos());
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

//    @Transactional
//    public FarmacoDTO atualizarCruzamentos(Long farmacoId, Map<String, Classificacao> novosCruzamentos) {
//        System.out.println("Novos cruzamentos: " + novosCruzamentos.keySet());
//
//        Farmaco farmacoPrincipal = farmacoRepo.findById(farmacoId)
//                .orElseThrow(() -> new IllegalArgumentException("Farmaco não encontrado Id: " + farmacoId));
//
//        System.out.println("Farmaco principal: " + farmacoPrincipal);
//
//        if (novosCruzamentos != null && !novosCruzamentos.isEmpty()) {
//            for (Map.Entry<String, Classificacao> entry : novosCruzamentos.entrySet()) {
//                String farmacoCruzadoNome = entry.getKey();
//                Classificacao classificacao = entry.getValue();
//                System.out.println("\nNovo cruzamento: " + entry.getKey() + " value: " + entry.getValue());
//
//                // gera um erro bem aqui, quando faz a segunda entrada e encontra o fármaco com ele mesmo:
//                // "status": 500,
//                //    "error": "Erro interno no servidor",
//                //    "message": "query did not return a unique result: 2"
//                Optional<Farmaco> optionalFarmacoCruzado = farmacoRepo.findFarmacoByNomeDaSubstancia(farmacoCruzadoNome);
//
//                if (optionalFarmacoCruzado.isPresent()) {
//                    Farmaco farmacoCruzado = optionalFarmacoCruzado.get();
//
//                    if (!farmacoCruzado.getCruzamentos().containsKey(farmacoPrincipal.getNomeDaSubstancia())) {
//                        farmacoCruzado.getCruzamentos().put(farmacoPrincipal.getNomeDaSubstancia(), classificacao);
//                        atualizarFarmaco(farmacoCruzado.getFarmacoId(), mapper.toDTO(farmacoCruzado));
//                    } else {
//                        System.out.println("Cruzamento já existe para " + farmacoCruzadoNome + " com " + farmacoPrincipal.getNomeDaSubstancia() + ". Pulando para próxima chave.");
//                    }
//                } else {
//                    Farmaco novoFarmacoCruzado = new Farmaco();
//                    novoFarmacoCruzado.setNomeDaSubstancia(farmacoCruzadoNome);
//                    novoFarmacoCruzado.getCruzamentos().put(farmacoPrincipal.getNomeDaSubstancia(), classificacao);
//                    farmacoRepo.save(novoFarmacoCruzado);
//                    atualizarFarmaco(novoFarmacoCruzado.getFarmacoId(), mapper.toDTO(novoFarmacoCruzado));
//                }
//            }
//        }
//        // Atualiza o fármaco principal
//        farmacoRepo.save(farmacoPrincipal);
//        return mapper.toDTO(farmacoPrincipal);
//    }

    public String deletarFarmaco(Long id) {
        farmacoRepo.deleteById(id);
        return "Fármaco deletado";
    }

    public Optional<Farmaco>  lerFarmacoPorNomeDaSubstancia(String nomeDaSubstancia) {
        return farmacoRepo.findFarmacoByNomeDaSubstancia(nomeDaSubstancia);
    }
}
