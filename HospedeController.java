package br.edu.senai.sc.granHotel.Controller;

import br.edu.senai.sc.granHotel.Entity.Hospede;
import br.edu.senai.sc.granHotel.Repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {
    @GetMapping("/cad")
    public ResponseEntity<Hospede> helloWorld(){
        Hospede hospede = new Hospede();
        hospede.setId(hospede.getId());
        hospede.setNome("Daniel");
        hospede.setSobrenome("Claudino");

        return new ResponseEntity<>(hospede, HttpStatus.OK);
    }
    @Autowired
    private HospedeRepository hospedeRepository;

    @PostMapping
    public Hospede criarHospede(@RequestBody Hospede hospede) {

        return hospedeRepository.save(hospede);
    }

    //Atualização de registro
    @PutMapping("/{id}")
    public Hospede atualizarHospede(@PathVariable Long id, @RequestBody Hospede hospedeAtualizado) {
        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóspede não encontrado"));

        hospede.setNome(hospedeAtualizado.getNome());
        hospede.setSobrenome(hospedeAtualizado.getSobrenome());
        hospede.setDataCheckIn(hospedeAtualizado.getDataCheckIn());
        hospede.setDataCheckOut(hospedeAtualizado.getDataCheckOut());

        return hospedeRepository.save(hospede);
    }

    //Exclusão de registro
    @DeleteMapping("/{id}")
    public void excluirHospede(@PathVariable Long id) {
        hospedeRepository.deleteById(id);
    }

    //Busca de registro
    @GetMapping
    public List<Hospede> listarHospedes() {
        return hospedeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hospede buscarHospedePorId(@PathVariable Long id) {
        return hospedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóspede não encontrado"));
    }

}
