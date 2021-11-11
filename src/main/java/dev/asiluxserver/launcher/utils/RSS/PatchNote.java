package dev.asiluxserver.launcher.utils.RSS;

import java.util.ArrayList;
import java.util.List;

public class PatchNote {

    String categories;
    ArrayList<String> note;

    public PatchNote(String categories, ArrayList<String> note) {
        this.categories = categories;
        this.note = note;
    }

    public PatchNote(String categories, String note) {
        this.categories = categories;
        addNote(note);
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setNote(String note, int index) {
        this.note.set(index, note);
    }

    public void addNote(String note) {
        this.note.add(note);
    }

    public void addNote(String note, int index) {
        this.note.add(index, note);
    }

    public void removeNote(String note) {
        this.note.remove(note);
    }

    public String getCategories() {
        return categories;
    }

    public List<String> getNote() {
        return note;
    }
}
