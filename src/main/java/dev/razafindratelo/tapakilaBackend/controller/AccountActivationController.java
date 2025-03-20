package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.service.activationAccountService.AccountActivationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class AccountActivationController {
    private final AccountActivationService accountActivationService;

    @GetMapping("account-activations")
    public ResponseEntity<List<AccountActivation>> findAllActivationAccounts(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(accountActivationService.findAll(page, size));
    }

    @GetMapping("account-activation/{userEmail}")
    public ResponseEntity<AccountActivation> findByUserEmail(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity.ok(accountActivationService.findByEmail(userEmail));
    }

    @GetMapping("account-activations/date-filters")
    public ResponseEntity<List<AccountActivation>> findAllDateFilterAccountActivations(
            @RequestParam(value = "starts_at", required = false) LocalDateTime startsAt,
            @RequestParam(value = "ends_at", required = false) LocalDateTime endsAt,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(accountActivationService.findAllBetweenDates(startsAt, endsAt, page, size));
    }

    @PatchMapping("account-activation/activate/{userEmail}")
    public ResponseEntity<AccountActivation> activateAccount(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity.ok(accountActivationService.activateAccount(userEmail));
    }
}
