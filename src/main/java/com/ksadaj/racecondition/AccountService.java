package com.ksadaj.racecondition;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void transfer(String fromAccountIdentifier, String toAccountIdentifier,
        BigDecimal amount) {
        BigDecimal fromBalance = accountRepository.getBalance(fromAccountIdentifier);

        if (amount.compareTo(fromBalance) > 0) {
            throw new IllegalStateException("Not enough money to transfer");
        }

        accountRepository.addBalance(fromAccountIdentifier,
            amount.multiply(BigDecimal.valueOf(-1.0)));
        accountRepository.addBalance(toAccountIdentifier, amount);


    }
}
