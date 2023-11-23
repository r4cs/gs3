package br.com.vaidaruim.gs3.apiController;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.service.FarmacoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/farmacos")
public class FarmacoController {

    private final FarmacoService service;

    @Autowired
    public FarmacoController(FarmacoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FarmacoDTO> cadastrarFarmaco(@Valid @RequestBody FarmacoDTO dto) {
        return ResponseEntity.ok(service.cadastrarFarmaco(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Farmaco>> lerFarmacoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.lerFarmacoPorId(id));
    }

//    @GetMapping
//    public ResponseEntity<List<Farmaco>> listarFarmacos(Pageable pageRequest) {
//        return ResponseEntity.ok(service.lerTodosFarmacos(pageRequest));
//    }

    @GetMapping
    public ResponseEntity<Page<Farmaco>> listarFarmacos(Pageable pageable) {
        Page<Farmaco> farmacos = service.lerTodosFarmacos(pageable);
        return ResponseEntity.ok(farmacos);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<FarmacoDTO> atualizarFarmaco(@Valid @PathVariable Long id,
                                                       @RequestBody FarmacoDTO dto) {
        return ResponseEntity.ok(service.atualizarFarmaco(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFarmaco(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletarFarmaco(id));
    }
}
