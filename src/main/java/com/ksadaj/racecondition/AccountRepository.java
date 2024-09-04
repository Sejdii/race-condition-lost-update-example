package com.ksadaj.racecondition;

import static com.ksadaj.racecondition.AccountTableDefinitions.ACCOUNT;
import static com.ksadaj.racecondition.AccountTableDefinitions.BALANCE;
import static com.ksadaj.racecondition.AccountTableDefinitions.IDENTIFIER;
import static org.jooq.impl.DSL.field;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final DSLContext dslContext;

    public BigDecimal getBalance(final String identifier) {
        Result<Record1<BigDecimal>> query = dslContext.select(BALANCE)
            .from(ACCOUNT)
            .where(IDENTIFIER.eq(identifier))
            .fetch();

        return query.getFirst().get(field("balance", BigDecimal.class));
    }

    public void addBalance(final String identifier, BigDecimal amount) {
        dslContext.update(ACCOUNT)
            .set(BALANCE, BALANCE.add(amount))
            .where(IDENTIFIER.eq(identifier))
            .execute();
    }
}
