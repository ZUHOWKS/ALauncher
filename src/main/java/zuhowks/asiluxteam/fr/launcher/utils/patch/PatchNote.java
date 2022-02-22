package zuhowks.asiluxteam.fr.launcher.utils.patch;

import java.util.ArrayList;

public class PatchNote {

    private String categories;
    private ArrayList<String> note;

    public PatchNote(String categories) {
        this.categories = categories;
        this.note = null;
    }

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

    public void setNote(ArrayList<String> note) {
        this.note = note;
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

    public ArrayList<String> getNote() {
        return this.note;
    }
}
