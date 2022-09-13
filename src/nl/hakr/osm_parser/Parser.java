package nl.hakr.osm_parser;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Parser {
    private XMLInputFactory xmlInputFactory;
    private XMLEventReader reader;
    private HashMap<OsmTag, TagInfo> info = new HashMap<>();
    private HashSet<String> others = new HashSet<>();

    public Parser(FileInputStream inputStream) throws XMLStreamException {
        this.xmlInputFactory = XMLInputFactory.newInstance();
        this.reader = xmlInputFactory.createXMLEventReader(inputStream);
    }

    public final void run() throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                register_tag(true, startElement.getName().toString());
            } else if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                register_tag(false, endElement.getName().toString());
            }
        }
    }

    public final void report() {
        System.out.println("... and done");
        System.out.println("\tInfo: " + this.info.toString());
        System.out.println("\tOthers: " + this.others.toString());

    }

    private final void register_tag(boolean doAdd, String tagName) {
        OsmTag osmTag = OsmTag.from(tagName);

        if (osmTag != null) {
            var infoEntry = info.getOrDefault(osmTag, new TagInfo());
            if (doAdd) {
                infoEntry.starts++;
            } else {
                infoEntry.ends++;
            }
            info.put(osmTag, infoEntry);
        } else {
            others.add(tagName);
        }
    }
}