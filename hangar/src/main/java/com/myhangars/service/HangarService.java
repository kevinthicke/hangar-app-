package com.myhangars.service;

import com.myhangars.model.Hangar;
import com.myhangars.model.HangarMin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HangarService {
    public Page< Hangar > getAll(Pageable pageable);
    public List<HangarMin> getAllMinimified();
    public List<String> getAllNames();
    public Hangar getById(long id);
    public List<Hangar> getByName(String name);
    //public Hangar getHangarThatContainsProduct(long productId);
    //public Hangar getExactlyByName(String name);
    public Hangar getFirstByName(String name);
    public Hangar insert(Hangar hangar);
    public Hangar update(long id, Hangar hangar);
    public Hangar safeDeleteById(long id);
}
