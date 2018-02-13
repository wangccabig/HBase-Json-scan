package com.han.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HScan {
    @JsonProperty("start_rowkey")
    private String startRowKey;
    @JsonProperty("stop_rowkey")
    private String endRowKey;
    @JsonProperty("filter_list_type")
    private String filterListType;
    @JsonProperty("filter_list")
    private List<HFilter> filterList;

    public String getStartRowKey() {
        return startRowKey;
    }

    public void setStartRowKey(String startRowKey) {
        this.startRowKey = startRowKey;
    }

    public String getEndRowKey() {
        return endRowKey;
    }

    public void setEndRowKey(String endRowKey) {
        this.endRowKey = endRowKey;
    }

    public String getFilterListType() {
        return filterListType;
    }

    public void setFilterListType(String filterListType) {
        this.filterListType = filterListType;
    }

    public List<HFilter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<HFilter> filterList) {
        this.filterList = filterList;
    }

    public HScan(String startRowKey, String endRowKey, String filterListType, List<HFilter> filterList) {

        this.startRowKey = startRowKey;
        this.endRowKey = endRowKey;
        this.filterListType = filterListType;
        this.filterList = filterList;
    }

    public HScan() {

    }
}
