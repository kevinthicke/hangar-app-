package com.myhangars.builder;

import com.myhangars.model.HangarMin;
import com.myhangars.model.Hangar;

public class HangarMinBuilder {

    private HangarMin hangarMinDto;

    HangarMinBuilder(Hangar hangar) {

        hangarMinDto = new HangarMin();

        hangarMinDto.setId(hangar.getId());
        hangarMinDto.setName(hangar.getName());
    }

    public HangarMin getHangarMinDto() {
        return hangarMinDto;
    }
}
