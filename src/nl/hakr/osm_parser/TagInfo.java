package nl.hakr.osm_parser;

public class TagInfo {
    public long starts = 0;
    public long ends = 0;

    public String toString() {
        return "{ starts: " + starts + ", ends: " + ends + " }";
    }
}
