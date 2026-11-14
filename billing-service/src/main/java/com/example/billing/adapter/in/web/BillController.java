package com.example.billing.adapter.in.web;

import com.example.billing.application.BillDetails;
import com.example.billing.application.BillUseCases;
import com.example.billing.domain.BillItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {
    private final BillUseCases bills;

    public BillController(BillUseCases bills) { this.bills = bills; }

    @GetMapping
    public List<BillDetails> findAll() { return bills.findAll(); }

    @GetMapping("/{id}")
    public BillDetails findById(@PathVariable Long id) {
        return bills.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BillDetails create(@RequestBody CreateBillRequest request) {
        List<BillItem> items = request.items().stream()
                .map(item -> new BillItem(null, item.productId(), item.quantity())).toList();
        return bills.create(request.customerId(), items);
    }

    public record CreateBillRequest(Long customerId, List<CreateBillItemRequest> items) { }
    public record CreateBillItemRequest(Long productId, int quantity) { }
}
