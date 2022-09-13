package nl.hakr.osm_parser;

public enum OsmTag {
    NODE,
    WAY,
    RELATION;

    public static OsmTag from(String tag) {
        if ("node".compareToIgnoreCase(tag) == 0) {
            return OsmTag.NODE;
        } else if ("way".compareToIgnoreCase(tag) == 0) {
            return OsmTag.WAY;
        } else if ("relation".compareToIgnoreCase(tag) == 0) {
            return OsmTag.RELATION;
        }
        return null;
    }
}
