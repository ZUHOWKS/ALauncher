package dev.asiluxserver.launcher.utils.RSS;

import java.util.List;

public class PatchMessage {

    String title;
    List<PatchNote> patchNote;

    public PatchMessage(String title, List<PatchNote> patchNotes) {
        this.title = title;
        this.patchNote = patchNotes;
    }

    public List<PatchNote> getPatchNote() {
        return patchNote;
    }

    public String getTitle() {
        return title;
    }

    public void addPatchNote(PatchNote patchNote) {
        this.patchNote.add(patchNote);
    }

    public void addPatchNote(PatchNote patchNote, int index) {
        this.patchNote.add(index, patchNote);
    }

}
