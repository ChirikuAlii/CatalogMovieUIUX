package id.chirikualii.catalogmovieuiux.presentasion.ui.adapter;

import java.util.List;

import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;

public interface OnItemClick<T> {
    void onItemClick(T item);
}
