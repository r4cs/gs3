package br.com.vaidaruim.gs3.apiController;

import br.com.vaidaruim.gs3.core.entity.DTO.FarmacoDTO;
import br.com.vaidaruim.gs3.core.entity.Farmaco;
import br.com.vaidaruim.gs3.core.service.FarmacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/farmacos")
public class FarmacoController {


    @Autowired
    public final FarmacoService service;

    public FarmacoController(FarmacoService service) {
        this.service = service;
    }

    @GetMapping
    public String listFarmacos(Model model) {
        model.addAttribute("farmacos", service.lerTodosFarmacos());
        return "list"; //path da pasta templates
    }

    @GetMapping("/criar")
    public String showCreateForm(Model model) {
        model.addAttribute("farmacos", new Farmaco());
        return "form";
    }

    @PostMapping
    public String cadastrarFarmaco(@ModelAttribute FarmacoDTO dto) {
        service.cadastrarFarmaco(dto);
        return "redirect:/api/farmacos";
//        return "redirect:farmacos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormEdicao(@PathVariable Long id, Model model) {
        Farmaco farmaco = service.lerFarmacoPorId(id).orElseThrow(() -> new IllegalArgumentException("Farmaco não encotrado Id: " + id));
        model.addAttribute("farmacos", farmaco);
        return "form";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizarFarmaco(@PathVariable Long id, @ModelAttribute FarmacoDTO dto) {
        service.cadastrarFarmaco(dto);
        return "form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFarmaco(@PathVariable Long id) {
        service.deletarFarmaco(id);
        return "redirect:/farmacos";
    }

}
