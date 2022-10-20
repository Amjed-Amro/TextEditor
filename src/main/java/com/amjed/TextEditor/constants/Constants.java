package com.amjed.TextEditor.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {


    public static final String DICTIONARY_PATH = "data/dict.txt";
    public static final String ERROR_LOADING_FILE = "Problem loading dictionary file: ";
    public static final String INITIAL_DICTIONARY_ID = "634e4cc4527e700789231825";

    public static class RESPONSE_CODE {
        public static final String SUCCESS = "000";
        public static final String FAILED = "999";
    }

    public static class RESPONSE_MESSAGE {
        public static final String SUCCESS = "SUCCESS ";
        public static final String FAILED = "ERROR ";
    }

    public static class ALPHABET {
        public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
        public static final ArrayList<Character> VOWELS = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y'));
    }
}
