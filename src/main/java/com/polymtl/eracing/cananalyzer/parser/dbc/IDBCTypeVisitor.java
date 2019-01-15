package com.polymtl.eracing.cananalyzer.parser.dbc;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCMessageParser.DBCMessage;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCNodesParser.DBCNodes;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser.DBCSignal;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCValueTableParser.DBCValueTable;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCVersionParser.DBCVersion;

/**
 * This interface defines a visitor for visiting the various DBC type.
 */
public interface IDBCTypeVisitor {
    /**
     * This method visits a DBC message.
     *
     * @param message The DBC message to visit.
     */
    void visit(DBCMessage message);

    /**
     * This method visits a DBC nodes listing.
     *
     * @param nodes The DBC nodes listing.
     */
    void visit(DBCNodes nodes);

    /**
     * This method visits a DBC signal.
     *
     * @param signal The DBC signal.
     */
    void visit(DBCSignal signal);

    /**
     * This method visits a DBC value table.
     *
     * @param table The DBC value table.
     */
    void visit(DBCValueTable table);

    /**
     * This method visits a DBC version.
     *
     * @param version The DBC version.
     */
    void visit(DBCVersion version);
}
