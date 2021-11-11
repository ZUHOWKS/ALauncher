package dev.asiluxserver.launcher.utils.RSS;

import java.util.List;

public class PatchMessage {

    String title;
    List<PatchNote> patchNote;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPatchNote(List<PatchNote> patchNote) {
        this.patchNote = patchNote;
    }

    public List<PatchNote> getPatchNote() {
        return patchNote;
    }

    public String getTitle() {
        return title;
    }
}
