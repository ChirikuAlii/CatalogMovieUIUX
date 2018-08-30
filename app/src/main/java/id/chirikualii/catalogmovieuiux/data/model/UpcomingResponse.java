package id.chirikualii.catalogmovieuiux.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingResponse {

    @SerializedName("results")
    private List<UpcomingModel> upcomingModels;

    @SerializedName("page")
    private Integer page;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("dates")
    private Dates dates;

    @SerializedName("total_pages")
    private Integer totalPages;

    public List<UpcomingModel> getUpcomingModels() {
        return upcomingModels;
    }

    public void setUpcomingModels(List<UpcomingModel> upcomingModels) {
        this.upcomingModels = upcomingModels;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }




}
