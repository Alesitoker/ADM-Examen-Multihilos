package com.iessaladillo.alejandro.adm_examenmultihilos.ui.main;

import com.iessaladillo.alejandro.adm_examenmultihilos.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public MainFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainFragmentViewModel(repository);
    }
}
