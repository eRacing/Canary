package com.polymtl.eracing.cananalyzer.parser.dbc;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCDatabaseDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCNodeDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCMessageDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCSignalDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCMessageParser.DBCMessage;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCNodesParser.DBCNodes;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser.DBCSignal;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSpeedParser.DBCSpeed;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCValueTableParser.DBCValueTable;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCVersionParser.DBCVersion;

/**
 * This interface defines a visitor for visiting the various DBC type.
 */
public interface IDBCTypeVisitor {
    /**
     * This method visits a DBC database description.
     *
     * @param description The DBC database description to visit.
     */
    void visit(DBCDatabaseDescription description);

    /**
     * This method visits a DBC node description.
     *
     * @param description The DBC node description to visit.
     */
    void visit(DBCNodeDescription description);

    /**
     * This method visits a DBC message description.
     *
     * @param description The DBC message description to visit.
     */
    void visit(DBCMessageDescription description);

    /**
     * This method visits a DBC signal description.
     *
     * @param description The DBC signal description to visit.
     */
    void visit(DBCSignalDescription description);

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
     * This method visits a DBC speed.
     *
     * @param speed The DBC speed.
     */
    void visit(DBCSpeed speed);

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
