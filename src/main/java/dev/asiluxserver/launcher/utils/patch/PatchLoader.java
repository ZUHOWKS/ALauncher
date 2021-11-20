package dev.asiluxserver.launcher.utils.patch;

import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.Locale;

public class PatchLoader  {

    private XMLPatchParser patchFile;
    private ArrayList<ArrayList<Label>> patchNoteLabel = new ArrayList<>();
    private Label titleLabel = new Label("");
    private Label patchLabel = new Label("");

    public PatchLoader(XMLPatchParser xmlPatchParser) {
        this.patchFile = xmlPatchParser;
    }

    public void load() {

        PatchMessage patchMessage = patchFile.getPatch();
        ArrayList<PatchNote> patchNotes = patchMessage.getPatchNotes();
        this.titleLabel.setText(patchMessage.getTitle());

        for (PatchNote patchNote : patchNotes) {

            ArrayList<String> notes = patchNote.getNote();
            patchLabel.setText(patchLabel.getText() + (!patchLabel.getText().equals("") ? "\n\n" : "") +
                    patchNote.getCategories() + "\n");

            ArrayList<Label> labels = new ArrayList<>();

            Label categoriesLabel = new Label(patchNote.getCategories().toUpperCase(Locale.ROOT));
            categoriesLabel.setAlignment(Pos.TOP_LEFT);
            GridPane.setHalignment(categoriesLabel, HPos.LEFT);
            GridPane.setValignment(categoriesLabel, VPos.TOP);
            GridPane.setHgrow(categoriesLabel, Priority.ALWAYS);
            GridPane.setVgrow(categoriesLabel, Priority.ALWAYS);
            categoriesLabel.setStyle("-fx-text-size: 40; -fx-text-fill: rgb(240, 240, 240);");
            categoriesLabel.setFont(Fonts.SELAWK_SEMI_BOLD.getFont(40));
            categoriesLabel.setTranslateX(50);
            categoriesLabel.setTranslateY(25);

            Label noteLabel = new Label();
            noteLabel.setAlignment(Pos.TOP_LEFT);
            GridPane.setHalignment(noteLabel, HPos.LEFT);
            GridPane.setValignment(noteLabel, VPos.TOP);
            GridPane.setHgrow(noteLabel, Priority.ALWAYS);
            GridPane.setVgrow(noteLabel, Priority.ALWAYS);
            noteLabel.setStyle("-fx-text-size: 28; -fx-text-fill: rgb(230, 230, 230);");
            noteLabel.setFont(Fonts.SELAWK.getFont(28));
            noteLabel.setTranslateX(50);
            noteLabel.setTranslateY(140);

            int noteSize = notes.size();
            for (String note : notes) {
                patchLabel.setText(patchLabel.getText() + "\n" +
                        "  • " + note
                );
                noteLabel.setText(noteLabel.getText() + "  • " + note + "\n");
            }

            labels.add(categoriesLabel);
            labels.add(noteLabel);

            patchNoteLabel.add(labels);

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

    public ArrayList<ArrayList<Label>> getPatchNoteLabel() {
        return patchNoteLabel;
    }
}
