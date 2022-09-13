package main;

import java.io.FileInputStream;

import nl.hakr.osm_parser.Parser;

public final class cli {

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                var fileName = args[0];
                var inputStream = new FileInputStream(fileName);
                var parser = new Parser(inputStream);

                parser.run();
                parser.report();

            } catch (Exception e) {
                System.err.println("An error occured: " + e);
            }
        } else {
            System.err.println("Only files with extension .osm or .osm.bz2 are supported.");
            System.exit(2);
        }

    }

}
