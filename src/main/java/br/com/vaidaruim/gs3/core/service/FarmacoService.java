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

import java.util.Map;
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
        atualizarCruzamentos(farmaco.getFarmacoId(), farmaco.getCruzamentos());
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

    @Transactional
    public FarmacoDTO atualizarCruzamentos(Long farmacoId, Map<String, Classificacao> novosCruzamentos) {
        Farmaco farmacoPrincipal = farmacoRepo.findById(farmacoId)
                .orElseThrow(() -> new IllegalArgumentException("Farmaco não encontrado Id: " + farmacoId));

        if (novosCruzamentos != null && !novosCruzamentos.isEmpty()) {
            for (Map.Entry<String, Classificacao> entry : novosCruzamentos.entrySet()) {
                System.out.println("\nnovo cruzamento: " + novosCruzamentos.entrySet() + "\nentry:" + entry);
                String nomeCruzamento = entry.getKey();
                System.out.println("nomeCruzamento: " + nomeCruzamento);
                Classificacao classificacao = entry.getValue();
                System.out.println("classificaçao: " + classificacao);

                // Valida se o cruzamento já existe no fármaco principal
                if (!farmacoPrincipal.getCruzamentos().containsKey(nomeCruzamento)) {
                    // Se não existe, adiciona o cruzamento ao fármaco principal
                    farmacoPrincipal.getCruzamentos().put(nomeCruzamento, classificacao);
                }

                // Valida se o fármaco cruzado existe
                Farmaco farmacoCruzado = farmacoRepo.findFarmacoByNomeDaSubstancia(nomeCruzamento)
                        .orElseGet(() -> {
                            // Se não existe, cria um novo fármaco cruzado
                            Farmaco novoFarmacoCruzado = new Farmaco();
                            novoFarmacoCruzado.setNomeDaSubstancia(nomeCruzamento);
                            novoFarmacoCruzado.getCruzamentos().put(farmacoPrincipal.getNomeDaSubstancia(), classificacao);
                            farmacoRepo.save(novoFarmacoCruzado);
                            return novoFarmacoCruzado;
                        });
                System.out.println("farmacoCruzado: " + farmacoCruzado);

                // Verifica se o cruzamento no fármaco cruzado está alinhado
                if (!farmacoCruzado.getCruzamentos().containsKey(farmacoPrincipal.getNomeDaSubstancia())
                        || farmacoCruzado.getCruzamentos().get(farmacoPrincipal.getNomeDaSubstancia()) != classificacao) {
                    // Se não está alinhado, atualiza o cruzamento no fármaco cruzado
                    farmacoCruzado.getCruzamentos().put(farmacoPrincipal.getNomeDaSubstancia(), classificacao);
                    farmacoRepo.save(farmacoCruzado);
                }
            }
        }

        farmacoRepo.save(farmacoPrincipal);
        return mapper.toDTO(farmacoPrincipal);
    }


    public String deletarFarmaco(Long id) {
        farmacoRepo.deleteById(id);
        return "Fármaco deletado";
    }

    public Optional<Farmaco>  lerFarmacoPorNomeDaSubstancia(String nomeDaSubstancia) {
        return farmacoRepo.findFarmacoByNomeDaSubstancia(nomeDaSubstancia);
    }
}
