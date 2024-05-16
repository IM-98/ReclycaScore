package com.recycla.score.web.controller;

import com.recycla.score.domain.entity.Objet;
import com.recycla.score.domain.service.ObjetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/objet")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ObjetController {

    private final ObjetService objetService;

    @PostMapping(path = "/create")
    public Objet postObjet(@RequestBody Objet objet) {
        return objetService.save(objet);
    }

    @GetMapping(path = "/get")
    public Optional<Objet> getObjet(@RequestParam Long id) {
        return objetService.getById(id);
    }

    @GetMapping(path = "/getAll")
    public Iterable<Objet> getAllObjet() {
        return objetService.getAll();
    }

}
