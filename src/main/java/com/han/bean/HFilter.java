package com.han.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HFilter {
    @JsonProperty("filter_class_name")
    private String filterName;
    @JsonProperty("params")
    private String[] params;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public HFilter(String filterName, String[] params) {

        this.filterName = filterName;
        this.params = params;
    }

    public HFilter() {

    }
}
