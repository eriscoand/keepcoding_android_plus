package com.erisco.madridshops.domain.interactors.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.erisco.madridshops.domain.interactors.InteractorErrorCompletion;
import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;
import com.erisco.madridshops.domain.managers.network.managers.ListManagerCompletion;
import com.erisco.madridshops.domain.managers.network.managers.ManagerErrorCompletion;
import com.erisco.madridshops.domain.managers.network.managers.NetworkManager;
import com.erisco.madridshops.domain.managers.network.mappers.ActivitiesMapper;
import com.erisco.madridshops.domain.model.Activity.Activities;

import java.util.List;

public class ActivitiesListInteractorImpl implements ListInteractor {
    
    private NetworkManager networkManager;
    private String url;
    
    public ActivitiesListInteractorImpl(@NonNull final String url, @NonNull final NetworkManager networkManager) {
        this.networkManager = networkManager;
        this.url = url;
    }

    @Override
    public void execute(@NonNull final ListInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        if (this.networkManager == null) {
            if (onError == null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.onError("");
            }
        }

        this.networkManager.getListFromServer(
                this.url,
                new ListManagerCompletion() {
                        @Override
                        public void completion(@NonNull List<JsonEntity> activitiesEntities) {
                            if (completion != null) {
                                Activities activities = new ActivitiesMapper().map(activitiesEntities);
                                completion.completion(activities);
                            }
                        }
                    }, 
                new ManagerErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {
                        if (onError != null) {
                            onError.onError(errorDescription);
                        }
                    }
        });
    }
}
