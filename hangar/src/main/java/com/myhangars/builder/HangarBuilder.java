package com.myhangars.builder;

import com.myhangars.dto.HangarDto;
import com.myhangars.model.Hangar;

public class HangarBuilder {

    private Hangar hangar;

    public HangarBuilder(HangarDto hangarDto) {
        hangar = new Hangar();

        this.hangar.setName(hangarDto.getName());
        this.hangar.setLocation(hangarDto.getLocation());
        this.hangar.setOwner(hangarDto.getOwner());
        this.hangar.setEmail(hangarDto.getEmail());
        this.hangar.setPhone(hangarDto.getPhone());
    }

    public Hangar getHangar() {
        return hangar;
    }

}
