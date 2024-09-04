package com.ksadaj.racecondition;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts/transfer")
    ResponseEntity<Void> transfer(@RequestBody TransferCommand transferCommand) {
        accountService.transfer(transferCommand.fromAccount(), transferCommand.toAccount(),
            transferCommand.amount());
        return ResponseEntity.noContent().build();
    }

}
