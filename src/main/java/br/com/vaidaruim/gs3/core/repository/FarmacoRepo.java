package br.com.vaidaruim.gs3.core.repository;

import br.com.vaidaruim.gs3.core.entity.Classificacao;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmacoRepo extends JpaRepository<Farmaco, Long> {
    Page<Farmaco> findAll(Pageable pageable);
    Optional<Farmaco> findFarmacoByNomeDaSubstancia(String nomeDaSubstancia);

    @Query("SELECT COUNT(f) > 0 FROM Farmaco f JOIN f.cruzamentos c WHERE f.nomeDaSubstancia = :nomeSubstancia AND c.classificacao = :classificacao")
    Boolean existsByNomeDaSubstanciaAndCruzamentos(@Param("nomeSubstancia") String nomeSubstancia,
                                                   @Param("classificacao") Classificacao classificacao);

}
