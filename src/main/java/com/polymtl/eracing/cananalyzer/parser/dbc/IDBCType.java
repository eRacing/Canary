package com.polymtl.eracing.cananalyzer.parser.dbc;

/**
 * This interface defines a DBC type.
 */
public interface IDBCType {
    /**
     * This method allows the DBC type to visit a visitor.
     *
     * @param visitor The visitor to visit.
     */
    void accept(IDBCTypeVisitor visitor);
}
