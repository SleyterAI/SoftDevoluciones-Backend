package CV.SoftDevoluciones.User.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
/*
    private final CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<CustomerResponseDto> getCustomerByUserEmail(
            Authentication authentication) {

        String userEmail = authentication.getName();
        return ResponseEntity.ok(
                customerService.getCustomerByUserEmail(userEmail)
        );
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
            Authentication authentication,
            @Valid @RequestBody CustomerRequestDto requestDto){
        customerService.createCustomer(authentication.getName(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Customer created correctly");
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            Authentication authentication, @Valid @RequestBody CustomerUpdateRequestDto updateRequest) {
        return ResponseEntity.ok(customerService.updateCustomer(authentication.getName(), updateRequest));
    }*/
}
