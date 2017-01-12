package com.github.jmcampanini.hrm.emulator;

/**
 * Thrown when a tile is expected to contain a {@link Thing} but is empty.
 */
public class TileEmptyException extends ProcessorException {

    public TileEmptyException(int tileNum) {
        super("Tile " + tileNum + " is empty");
    }
}
