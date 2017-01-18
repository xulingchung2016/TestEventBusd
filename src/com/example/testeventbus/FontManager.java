package com.example.testeventbus;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Just a simple singleton class to manage font loading from assets.
 * Original source: <a href="http://stackoverflow.com/a/29134056">http://stackoverflow.com/a/29134056</a>
 * <p/>
 * Created by mathias.berwig on 22/06/2016.
 */
public class FontManager {
    private static final String TAG = FontManager.class.getName();

//    private static FontManager instance;

   static AssetManager assetManager;
    private Map<String, Typeface> fonts;

    private FontManager() {
        this.fonts = new HashMap<>();
    }

    private static class singleHolder{
//    	assetManager = null;
    	public static FontManager instance = new FontManager();
    }
    public static FontManager getInstance(AssetManager assetManagers) {
    	assetManager = assetManagers;
       /* if (instance == null) {
            instance = new FontManager(assetManager);
        }*/
        return singleHolder.instance;
    }

    public Typeface getFont(String asset) {
        if (fonts.containsKey(asset))
            return fonts.get(asset);

        Typeface font = null;

        try {
            font = Typeface.createFromAsset(assetManager, asset);
            fonts.put(asset, font);
        } catch (RuntimeException e) {
            Log.e(TAG, "getFont: Can't create font from asset.", e);
        }

        return font;
    }
}
