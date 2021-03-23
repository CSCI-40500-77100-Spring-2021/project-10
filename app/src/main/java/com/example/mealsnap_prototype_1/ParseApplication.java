////PARSE STUFF TO BE REMOVED
package com.example.mealsnap_prototype_1;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Register parse model
        ParseObject.registerSubclass(ParsePost.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6NlcjKyIoMUSNzBffHOR8wB2ysRSmC9GEvvjQdOW")
                .clientKey("VbLW6cxKUJrXC4Rl2fDCj1WBLFrVhgnh5YkOtNMw")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
////PARSE STUFF TO BE REMOVED