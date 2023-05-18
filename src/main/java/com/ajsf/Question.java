package com.ajsf;

public abstract class Question {
    // Attributes
    private String source;
    private String title;

    // Constructor
    public Question(String source, String title) {
        this.source = source;
        this.title = title;
    }

    // Misc Methods
    protected static String[] toArray(String string) {
        return string.substring(1, string.length()-1).split(";");
    }

    protected static Boolean inArray(String input, String[] array) {
        for (String s : array) {
            if (s.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public abstract String toString();

    // Mutators
    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Accessors
    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }
}
