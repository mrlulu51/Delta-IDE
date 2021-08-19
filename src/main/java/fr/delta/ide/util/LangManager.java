package fr.delta.ide.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public interface LangManager {

    ResourceBundle en_US = ResourceBundle.getBundle("assets.lang.enUS", new Locale("enUS"));
    ResourceBundle fr_FR = ResourceBundle.getBundle("assets.lang.frFR", new Locale("frFR"));

    ResourceBundle getCurrent = en_US;

    static String getString(String key, ResourceBundle bundle) {
        try {
            return bundle.getString(key);
        }catch(MissingResourceException e) {
            System.err.printf("Key %s was not found.%n", key);
            return key;
        }
    }
}
