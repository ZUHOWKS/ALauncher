package dev.asiluxserver.launcher.utils.patch;

import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class PatchLoader  {

    XMLPatchParser patchFile;
    Label titleLabel = new Label("");
    Label patchLabel = new Label("");

    public PatchLoader(XMLPatchParser xmlPatchParser) {
        this.patchFile = xmlPatchParser;
    }

    public void load() {

        PatchMessage patchMessage = patchFile.getPatch();
        ArrayList<PatchNote> patchNotes = patchMessage.getPatchNotes();
        this.titleLabel.setText(patchMessage.getTitle());

        for (int i = 0; i < patchNotes.size(); i++) {
            PatchNote patchNote = patchNotes.get(i);
            ArrayList<String> notes = patchNote.getNote();
            patchLabel.setText(patchLabel.getText() + (!patchLabel.getText().equals("") ? "\n\n" : "") +
                    patchNote.getCategories() + "\n");
            for (int j = 0; j < patchNote.getNote().size(); j++) {
                patchLabel.setText(patchLabel.getText() + "\n" +
                        "  - " + notes.get(j)
                );
            }
        }
    }

    public PatchMessage getPatch() {
        return this.patchFile.getPatch();
    }

    public Label getPatchLabel() {
        return this.patchLabel;
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    public XMLPatchParser getPatchFile() {
        return this.patchFile;
    }

}
