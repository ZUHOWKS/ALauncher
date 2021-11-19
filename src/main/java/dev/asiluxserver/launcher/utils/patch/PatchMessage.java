package dev.asiluxserver.launcher.utils.patch;

import java.util.ArrayList;

public class PatchMessage {

    private String title;
    private ArrayList<PatchNote> patchNotes;

    public PatchMessage(String title, ArrayList<PatchNote> patchNotes) {
        this.title = title;
        this.patchNotes = patchNotes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPatchNote(PatchNote patchNote, int index) {
        this.patchNotes.set(index, patchNote);
    }

    public void addPatchNote(PatchNote patchNote) {
        this.patchNotes.add(patchNote);
    }

    public void addPatchNote(PatchNote patchNote, int index) {
        this.patchNotes.add(index, patchNote);
    }

    public void removePatchNote(PatchNote patchNote) {
        this.patchNotes.remove(patchNote);
    }

    public ArrayList<PatchNote> getPatchNotes() {
        return this.patchNotes;
    }

    public PatchNote getPatchNote(int index) {
        return this.patchNotes.get(index);
    }

    public String getTitle() {
        return this.title;
    }
}
