
package com.jatinsinghroha.volleywithjson.userinput.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListOfQuotes implements Serializable
{

    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("totalCount")
    @Expose
    private Long totalCount;
    @SerializedName("page")
    @Expose
    private Long page;
    @SerializedName("totalPages")
    @Expose
    private Long totalPages;
    @SerializedName("lastItemIndex")
    @Expose
    private Long lastItemIndex;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    private final static long serialVersionUID = 6144626179982942424L;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getLastItemIndex() {
        return lastItemIndex;
    }

    public void setLastItemIndex(Long lastItemIndex) {
        this.lastItemIndex = lastItemIndex;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
