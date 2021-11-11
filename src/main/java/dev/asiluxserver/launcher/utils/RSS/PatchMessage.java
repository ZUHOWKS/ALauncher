package dev.asiluxserver.launcher.utils.RSS;

import java.util.List;

public class PatchMessage {

    String title;
    List<PatchNote> patchNote;

    public PatchMessage(String title, List<PatchNote> patchNotes) {
        this.title = title;
        this.patchNote = patchNotes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPatchNote(PatchNote patchNote, int index) {
        this.patchNote.set(index, patchNote);
    }

    public void addPatchNote(PatchNote patchNote) {
        this.patchNote.add(patchNote);
    }

    public void addPatchNote(PatchNote patchNote, int index) {
        this.patchNote.add(index, patchNote);
    }

    public void removePatchNote(PatchNote patchNote) {
        this.patchNote.remove(patchNote);
    }

    public List<PatchNote> getPatchNote() {
        return patchNote;
    }

    public String getTitle() {
        return title;
    }
}
