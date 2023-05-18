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
