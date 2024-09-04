package com.ksadaj.racecondition;

import static org.awaitility.Awaitility.await;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
class AttackSimulationTest {

    private static final int NUMBER_OF_ASYNC_REQUESTS = 10;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    @SneakyThrows
    void transferMoney() {
        for (int i = 1; i <= NUMBER_OF_ASYNC_REQUESTS; i++) {
            log.info("Submit {} request", i);
            Thread.startVirtualThread(this::sendTransferRequest);
        }

        await().atMost(5, TimeUnit.SECONDS)
            .until(() -> atomicInteger.get() == NUMBER_OF_ASYNC_REQUESTS);

        log.info("Bob account balance {}", accountRepository.getBalance("Bob"));
        log.info("Alice account balance {}", accountRepository.getBalance("Alice"));
    }

    private void sendTransferRequest() {
        testRestTemplate.postForEntity("/accounts/transfer",
            new TransferCommand("Alice", "Bob", BigDecimal.valueOf(2)), Void.class);
        log.info("Finished {} rest api call", atomicInteger.getAndIncrement());
    }
}
