package com.recycla.score.domain.service;

import com.recycla.score.domain.entity.Objet;
import com.recycla.score.domain.repository.ObjetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjetService {

    private final ObjetRepository objetRepository;

    public Objet save(Objet objet) {
        return objetRepository.save(objet);
    }

    public List<Objet> getAll() {
        return objetRepository.findAll();
    }

    public Optional<Objet> getById(Long id) {
        return objetRepository.findById(id);
    }
}
