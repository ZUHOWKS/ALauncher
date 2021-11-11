package dev.asiluxserver.launcher.utils.RSS;

import java.util.List;

public class PatchNote {

    String categorie;
    List<String> note;

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNote(List<String> note) {
        this.note = note;
    }

    public String getCategorie() {
        return categorie;
    }

    public List<String> getNote() {
        return note;
    }
}
