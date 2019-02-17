package com.adrian.util;

import javafx.scene.image.Image;

import java.util.HashMap;

public class Assets {

    private static HashMap<String, Image> images = new HashMap<>();

    /**
     * Initialize de load of all assets
     */
    public static void init(){
        Log.i(Assets.class.getName(), "loading Assets...");


        loadImage("APP ICON", "../icon.png");
        loadImage("FAILED ICON", "failed_icon.png");
        loadImage("TICK ICON", "tick_icon.png");
        loadImage("KEY ICON", "key_icon.png");
        loadImage("USER ICON", "user_icon.png");

        loadImage("SIGN-OUT ICON", "sign-out-option.png");
        loadImage("MAIL ICON", "mail.png");
        loadImage("SETTINGS ICON", "settings.png");

        Log.i(Assets.class.getName(), "Assets loaded");
    }

    /**
     * Load a single asset and assign it to a keyword
     * @param key the keyword
     * @param filename the full name of the file
     */
    private static void loadImage(String key, String filename){
        Log.d(Assets.class.getName(), "loading image '"+filename+"' as "+key);
        try{
            images.put(key, new Image(Assets.class.getResourceAsStream("../../../"+Constants.IMAGES_FOLDER+filename)));
        }catch(Exception e){
            Log.e(Assets.class.getName(), "loading '"+filename+"' failed!");
            Log.d(Assets.class.getName(), e.getMessage());
        }
    }

    /**
     *
     * @param key the keyword
     * @return the javafx.scene.image.Image object saved
     */
    public static Image getImage(String key){
        return images.get(key);
    }

}
