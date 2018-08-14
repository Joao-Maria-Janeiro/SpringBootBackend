package com.sap.intern.ecommerce.dto;

import java.util.Collection;
import java.util.HashSet;

public class PageDTO {

    private Integer currentPage;
    private Integer pageSize;
    private Long totalItems;
    private Collection<ProductGetDTO> items;

    public PageDTO() {
        items = new HashSet<>();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public PageDTO setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public PageDTO setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    public Collection<ProductGetDTO> getItems() {
        return items;
    }

    public PageDTO setItems(Collection<ProductGetDTO> items) {
        this.items = items;
        return this;
    }
}
