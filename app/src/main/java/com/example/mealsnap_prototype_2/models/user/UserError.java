package com.example.mealsnap_prototype_2.models.user;

import androidx.annotation.Nullable;

public class UserError extends Exception{
    private UserErrorType type;

    public UserError(UserErrorType type) {
        super();
        this.type = type;
    }

    public UserError(UserErrorType type, String message) {
        super(message);
        this.type = type;
    }

    public UserErrorType getType() {
        return type;
    }
}
