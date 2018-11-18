package com.polymtl.eracing.cananalyzer.parser.dbc;

/**
 * This class contains the parsed information of a comment line in the DBC format.
 */
public class DBCDescription {
    /**
     * The type of the comment.
     */
    private final DescriptionType fType;

    /**
     * The related node of the comment.
     */
    private final String fNode;

    /**
     * The related message ID of the comment.
     */
    private final Integer fID;

    /**
     * The related signal name of the comment.
     */
    private final String fSignal;

    /**
     * The description itself.
     */
    private final String fDescription;

    /**
     * Constructor.
     *
     * @param type        The type of the comment.
     * @param node        The node if specified.
     * @param id          The message ID if specified.
     * @param signal      The signal name if specified.
     * @param description The comment's description.
     */
    private DBCDescription(DescriptionType type, String node, Integer id, String signal, String description) {
        fType = type;
        fNode = node;
        fID = id;
        fSignal = signal;
        fDescription = description;
    }

    /**
     * This method returns a description of the database.
     *
     * @param description The description of the database.
     * @return The DBC description object.
     */
    public static DBCDescription databaseDescription(String description) {
        return new DBCDescription(DescriptionType.DATABASE, null, null, null, description);
    }

    /**
     * This method returns a description of a node.
     *
     * @param node        The name of the node.
     * @param description The description of the node.
     * @return The DBC description object.
     */
    public static DBCDescription nodeDescription(String node, String description) {
        return new DBCDescription(DescriptionType.NODE, node, null, null, description);
    }

    /**
     * This method returns a description of a message.
     *
     * @param id          The message ID.
     * @param description The description of the message.
     * @return The DBC description object.
     */
    public static DBCDescription messageDescription(Integer id, String description) {
        return new DBCDescription(DescriptionType.MESSAGE, null, id, null, description);
    }

    /**
     * This method returns a description of a signal.
     *
     * @param id          The message ID.
     * @param signal      The name of the signal.
     * @param description The description of the message.
     * @return The DBC description object.
     */
    public static DBCDescription signalDescription(Integer id, String signal, String description) {
        return new DBCDescription(DescriptionType.SIGNAL, null, id, signal, description);
    }

    /**
     * This enumeration defines the type of description possible in a DBC file.
     */
    private enum DescriptionType {
        /**
         * A description of the database.
         */
        DATABASE,
        /**
         * A description of a node.
         */
        NODE,
        /**
         * A description of a message.
         */
        MESSAGE,
        /**
         * A description of a signal.
         */
        SIGNAL
    }
}
