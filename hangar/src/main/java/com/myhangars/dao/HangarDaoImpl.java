package com.myhangars.dao;

import com.myhangars.model.Hangar;
import com.myhangars.model.HangarMin;
import com.myhangars.repository.HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HangarDaoImpl implements HangarDao {

    @Autowired
    private HangarRepository hangarRepository;

    @Override
    public Page< Hangar > findAll(Pageable pageable) {
        return this.hangarRepository.findAllWithTrueState(pageable);
    }

    @Override
    public List<Hangar> findByName(String name) {
        return this.hangarRepository.findByNameWithTrueState("%" + name + "%");
    }

    /*
    @Override
    public Optional<Hangar> findExactlyByName(String name) {
        return this.hangarRepository.findExactlyByNameWithTrueState(name);
    }
    */

    @Override
    public List<HangarMin> findAllMinimified() {
        return this.hangarRepository.findAllWithTrueStateNoPaginable()
                .stream()
                .map(hangar -> new HangarMin(hangar.getId(), hangar.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Hangar> findById(long id) {
        return this.hangarRepository.findByIdWithTrueState(id);
    }

    @Override
    public Hangar save(Hangar hangar) {
        return this.hangarRepository.save(hangar);
    }

    @Override
    public Hangar update(Hangar hangar) {
        return this.hangarRepository.save(hangar);
    }
}
