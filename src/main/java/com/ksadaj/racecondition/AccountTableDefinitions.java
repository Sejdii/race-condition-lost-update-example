package com.ksadaj.racecondition;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

import java.math.BigDecimal;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

public final class AccountTableDefinitions {

    private AccountTableDefinitions() {
    }

    public static final Table<Record> ACCOUNT = table("ACCOUNT");
    public static final Field<BigDecimal> BALANCE = field("balance", BigDecimal.class);
    public static final Field<String> IDENTIFIER = field("identifier", String.class);
}
