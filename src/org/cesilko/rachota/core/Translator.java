/*
 * Translator.java
 *
 * Created on July 30, 2004, 9:19 PM
 */

package org.cesilko.rachota.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.jar.JarFile;

/** Translator class providing localization.
 *
 * @author  Jiri Kovalsky
 */
public class Translator {
    
    /**
     * Resource bundle representing appropriate dictionary of translations.
     */
    private static PropertyResourceBundle dictionary;
    
    /**
     * Finds appropriate dictionary based on set country and language locales.
     */
    private static void findDictionary() {
        java.net.URL url = Translator.class.getResource("Translator.class");
        String location = url.getFile();
        String dictionaryName = "Dictionary_" + Locale.getDefault().getLanguage();
        dictionaryName = dictionaryName + "_";
        dictionaryName = dictionaryName + Locale.getDefault().getCountry();
        dictionaryName = dictionaryName + ".properties";
        
        InputStream inputStream;
        try {
            if (location.indexOf(".jar!") == -1)
                inputStream = new FileInputStream(location.substring(0, location.indexOf("Translator.class")) + dictionaryName);
            else {
                String fileName = System.getProperty("os.name").indexOf("Windows") == -1 ? "/" : "";
                fileName = fileName + location.substring(6, location.indexOf(".jar") + 4);
                JarFile jarFile = new JarFile(fileName);
                inputStream = jarFile.getInputStream(jarFile.getEntry("org/cesilko/rachota/core/" + dictionaryName));
            }
            dictionary = new PropertyResourceBundle(inputStream);
        } catch (IOException e) {
            System.out.println("Error: Reading from " + dictionaryName + " dictionary failed.");
            e.printStackTrace();
        }
        
    }
    
    /** Returns translation of given word in default language. If required
     * removes all & chars in addition.
     * @param word Word to be translated.
     * @param removeAnds Whether & chars should be removed.
     * @return Translation of given word in default language.
     */
    private static String getTranslation(String word, boolean removeAnds) {
        if (dictionary == null) findDictionary();
        try {
            String translation = dictionary.getString(word);
            if (removeAnds) translation = translation.replace("&", "");
            return translation;
        } catch (Exception e) {
            System.out.println("Error: Reading translation of " + word + " word failed.");
            e.printStackTrace();
            return null;
        }
    }
    
    /** Returns translation of given word in default language.
     * @param word Word to be translated.
     * @return Translation of given word in default language.
     */
    public static String getTranslation(String word) {
        return getTranslation(word, true);
    }
    
    /** Returns translation of given word in default language and replaces arguments.
     * @param word Word to be translated containing ${0}, ${1} etc. elements.
     * @param substitutions Words that will be used to replace ${0}, ${1} etc. elements.
     * @return Translation of given word in default language with substitutions.
     */
    public static String getTranslation(String word, String[] substitutions) {
        String translation = getTranslation(word, true);
        int count = substitutions.length;
        for (int i=0; i<count; i++)
            translation = translation.replace("${" + i + "}", substitutions[i]);
        return translation;
    }
    
    /** Returns mnemonic char found in translated word. As the mnemonic is considered
     * the character after & char. If & is not found in the translation, space
     * char is returned.
     * @param word Word to be translated and where mnemonic char will be searched for.
     * @return Mnemonic i.e. character right after first occurence of & character in
     * the translated word.
     */
    public static char getMnemonic(String word) {
       String translation = getTranslation(word, false);
       int index = translation.indexOf("&");
       return (index == -1) ? 32 : translation.charAt(index + 1);
    }
    
}
