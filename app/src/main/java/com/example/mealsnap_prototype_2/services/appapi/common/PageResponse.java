package com.example.mealsnap_prototype_2.services.appapi.common;

import androidx.annotation.Nullable;

// Reusable response data class for requests that returns page response
// Example Response: https://www.notion.so/API-Documentation-c622f4f78c7941c987a4362acb73b6cc#f1ebaa442a3d4f0a81291cba55cb6a10
public class PageResponse {
    String next;

    @Nullable
    public String getNext() {
        return next;
    }
}
