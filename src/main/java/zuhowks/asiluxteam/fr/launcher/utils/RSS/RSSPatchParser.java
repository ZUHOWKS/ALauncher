package zuhowks.asiluxteam.fr.launcher.utils.RSS;

import zuhowks.asiluxteam.fr.launcher.utils.patch.PatchMessage;
import zuhowks.asiluxteam.fr.launcher.utils.patch.PatchNote;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RSSPatchParser {
    static final String TITLE = "title";
    static final String CATEGORIES = "categories";
    static final String NOTE = "note";

    final URL url;

    public RSSPatchParser(URL url) {
        this.url = url;
    }

    public PatchMessage reedPatch() {
        PatchMessage patchMessage = null;
        try {
            boolean isPatchHeader = true;

            String title = "";
            String categories = "";
            String note = "";
            ArrayList<String> notes = new ArrayList<String>();
            ArrayList<String> categoriesListe = new ArrayList<String>();
            ArrayList<PatchNote> patchNotes= new ArrayList<>();


            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case TITLE:
                            title = event.asCharacters().getSchemaType().getLocalPart();
                            break;
                        case CATEGORIES:
                            categories = getCharacterData(event, eventReader);
                            break;
                        case NOTE:
                            note = getCharacterData(event, eventReader);
                            notes.add(note);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (CATEGORIES)) {
                        PatchNote patchNote = new PatchNote(categories, notes);
                        patchNotes.add(patchNote);
                        event = eventReader.nextEvent();
                        continue;
                    }

                    if (event.asEndElement().getName().getLocalPart() == (TITLE)) {
                        patchMessage = new PatchMessage(title, patchNotes);
                        event = eventReader.nextEvent();
                        continue;
                    };
                }
            }

        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return patchMessage;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
