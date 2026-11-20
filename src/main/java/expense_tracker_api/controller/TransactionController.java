
package expense_tracker_api.controller;

import expense_tracker_api.entity.Transaction;
import expense_tracker_api.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Transaction addTransaction(
            @Valid @RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    // READ ALL
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Transaction getTransactionById(
            @PathVariable Integer id) {
        return service.getTransactionById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Transaction updateTransaction(
            @PathVariable Integer id,
            @Valid @RequestBody Transaction transaction) {
        return service.updateTransaction(id, transaction);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteTransaction(
            @PathVariable Integer id) {
        service.deleteTransaction(id);
        return "Transaction deleted successfully";
    }
}
