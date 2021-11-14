package dev.asiluxserver.launcher.utils.patch;

import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class PatchLoader  {

    XMLPatchParser patchFile;
    ArrayList<Label> labels;

    public PatchLoader(XMLPatchParser xmlPatchParser) {
        this.patchFile = xmlPatchParser;
    }

    public void load() {

        PatchMessage patchMessage = patchFile.getPatch();
        ArrayList<PatchNote> patchNotes = patchMessage.getPatchNotes();
        this.labels.add(0, new Label(patchMessage.getTitle()));

        for (int i = 0; i < patchNotes.size(); i++) {
            PatchNote patchNote = patchNotes.get(i);
            ArrayList<String> notes = patchNote.getNote();
            Label note = new Label(patchNote.getCategories());
            for (int j = 0; j < patchNote.getNote().size(); j++) {
                note.setText(note.getText() + "\n" +
                        notes.get(j)
                );
            this.labels.add(note);
            }
        }
    }

    public PatchMessage getPatch() {
        return this.patchFile.getPatch();
    }

    public Label getLabel(int index) {
        return this.labels.get(index);
    }

    public ArrayList<Label> getLabel() {
        return this.labels;
    }

    public XMLPatchParser getPatchFile() {
        return this.patchFile;
    }

}
