package dev.asiluxserver.launcher.utils.patch;

import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Locale;

public class PatchLoader  {

    XMLPatchParser patchFile;
    ArrayList<ArrayList<Label>> patchNoteLabel = new ArrayList<>();
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

            ArrayList<Label> labels = new ArrayList<>();

            Label categoriesLabel = new Label(patchNote.getCategories().toUpperCase(Locale.ROOT));
            categoriesLabel.setAlignment(Pos.TOP_LEFT);
            GridPane.setHalignment(categoriesLabel, HPos.LEFT);
            GridPane.setValignment(categoriesLabel, VPos.TOP);
            GridPane.setHgrow(categoriesLabel, Priority.ALWAYS);
            GridPane.setVgrow(categoriesLabel, Priority.ALWAYS);
            categoriesLabel.setStyle("-fx-text-size: 32; -fx-text-fill: rgb(0, 0, 0);");
            categoriesLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 35));
            categoriesLabel.setTranslateX(50);

            Label noteLabel = new Label();
            noteLabel.setAlignment(Pos.TOP_LEFT);
            GridPane.setHalignment(noteLabel, HPos.LEFT);
            GridPane.setValignment(noteLabel, VPos.TOP);
            GridPane.setHgrow(noteLabel, Priority.ALWAYS);
            GridPane.setVgrow(noteLabel, Priority.ALWAYS);
            noteLabel.setStyle("-fx-text-size: 25; -fx-text-fill: rgb(0, 0, 0);");
            noteLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 25));
            noteLabel.setTranslateX(50);
            noteLabel.setTranslateY(50);

            int noteSize = notes.size();
            for (int j = 0; j < noteSize; j++) {
                patchLabel.setText(patchLabel.getText() + "\n" +
                        "  - " + notes.get(j)
                );
                noteLabel.setText(noteLabel.getText() + "  - " + notes.get(j) + "\n");
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