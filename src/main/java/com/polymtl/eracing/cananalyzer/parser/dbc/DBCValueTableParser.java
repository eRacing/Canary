package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.functional.tuple.Tuple;
import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * This class contains a parser for parsing a table of description.
 * <p><p>
 * The following line contains two elements in the table
 * <pre><code>VAL_TABLE_ TABLE_NAME 1337 "description 1" 420 "description 2"</code></pre>
 */
final public class DBCValueTableParser {
    /**
     * This parser detects and consumes the prefix for the value table.
     */
    private final static Parser<Void> PARSER_PREFIX = Scanners.string("VAL_TABLE_");

    /**
     * This parser detects and returns the content of a double quoted string.
     */
    public final static Parser<String> DOUBLE_QUOTED_STRING = Scanners.DOUBLE_QUOTE_STRING
            .map(s -> s.substring(1, s.length() - 1));

    /**
     * This parser detects and returns a definition.
     */
    private final static Parser<Tuple<Integer, String>> DEFINITION = Parsers.sequence(
            CommonParser.UNSIGNED_INTEGER.followedBy(CommonParser.SPACES), DOUBLE_QUOTED_STRING, Tuple::new
    );

    /**
     * This parser detects and returns a list of definitions.
     */
    private final static Parser<List<Tuple<Integer, String>>> DEFINITIONS = DEFINITION.sepBy(CommonParser.SPACES);

    /**
     * This parser detects and returns a table of descriptions.
     */
    public final static Parser<DBCValueTable> PARSER = Parsers.sequence(
            PARSER_PREFIX, CommonParser.SPACES, Scanners.IDENTIFIER, CommonParser.SPACES, DEFINITIONS
            , (x1, x2, x3, x4, x5) -> new DBCValueTable(x3, createDictionary(x5))
    );

    /**
     * This method convert the list of tuple into a dictionary.
     *
     * @param values The list of integer/string tuple.
     * @return The resulting dictionary.
     */
    private static Dictionary<Integer, String> createDictionary(List<Tuple<Integer, String>> values) {
        Dictionary<Integer, String> dict = new Hashtable<>();
        values.stream().forEach(t -> dict.put(t.getFirst(), t.getSecond()));
        return dict;
    }

    /**
     * This class defines a DBC table of values.
     */
    public static class DBCValueTable implements IDBCType {
        /**
         * The name of the table.
         */
        public final String fName;

        /**
         * The table of value.
         */
        public final Dictionary<Integer, String> fTable;

        /**
         * Constructor.
         *
         * @param table The table of value.
         */
        private DBCValueTable(String name, Dictionary<Integer, String> table) {
            fName = name;
            fTable = table;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }
}
