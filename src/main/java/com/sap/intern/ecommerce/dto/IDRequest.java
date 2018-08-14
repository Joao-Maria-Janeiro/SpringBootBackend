package com.sap.intern.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

public class IDRequest {

    private List<Long> ids;

    public IDRequest() {
        this.ids = new ArrayList<>();
    }

    public List<Long> getIds() {
        return ids;
    }

    public IDRequest setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }
}
