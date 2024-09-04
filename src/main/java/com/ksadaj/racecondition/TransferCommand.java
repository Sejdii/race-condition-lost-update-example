package com.ksadaj.racecondition;

import java.math.BigDecimal;

record TransferCommand(String fromAccount, String toAccount, BigDecimal amount) {

}
