package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entidades.Opinion;
import com.servicio.OpinionService;

import java.util.List;

@RestController
@RequestMapping("/api/opiniones")
public class OpinionController {

    private final OpinionService opinionService;

    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @GetMapping
    public List<Opinion> getAllOpiniones() {
        return opinionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opinion> getOpinionById(@PathVariable Long id) {
        return opinionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Opinion createOpinion(@RequestBody Opinion opinion) {
        return opinionService.save(opinion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Opinion> updateOpinion(@PathVariable Long id, @RequestBody Opinion opinionDetails) {
        return opinionService.findById(id)
                .map(opinion -> {
                    opinion.setPuntuacion(opinionDetails.getPuntuacion());
                    opinion.setComentario(opinionDetails.getComentario());
                    Opinion updatedOpinion = opinionService.save(opinion);
                    return ResponseEntity.ok(updatedOpinion);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpinion(@PathVariable Long id) {
        return opinionService.findById(id)
                .map(opinion -> {
                    opinionService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
