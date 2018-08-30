package id.chirikualii.catalogmovieuiux.data.repo;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.data.ApiService;

public class MainRepo {

    ApiService apiService;

    @Inject
    public MainRepo(ApiService apiService) {
        this.apiService = apiService;
    }


}
