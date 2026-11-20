
package expense_tracker_api.service;

import expense_tracker_api.entity.Transaction;
import expense_tracker_api.repository.TransactionRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Transaction addTransaction(Transaction transaction) {
        validateType(transaction.getType());
        return repository.save(transaction);
    }

    // READ ALL
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // READ BY ID
    public Transaction getTransactionById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transaction not found with ID: " + id
                ));
    }

    // UPDATE
    public Transaction updateTransaction(
            Integer id, Transaction updatedTransaction) {

        Transaction existing = getTransactionById(id);

        validateType(updatedTransaction.getType());

        existing.setTitle(updatedTransaction.getTitle());
        existing.setAmount(updatedTransaction.getAmount());
        existing.setType(updatedTransaction.getType());
        existing.setCategory(updatedTransaction.getCategory());
        existing.setDescription(updatedTransaction.getDescription());
        existing.setDate(updatedTransaction.getDate());

        return repository.save(existing);
    }

    // DELETE
    public void deleteTransaction(Integer id) {
        Transaction existing = getTransactionById(id);
        repository.delete(existing);
    }

    // BUSINESS VALIDATION
    private void validateType(String type) {
        if (!"INCOME".equalsIgnoreCase(type)
                && !"EXPENSE".equalsIgnoreCase(type)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Type must be INCOME or EXPENSE"
            );
        }
    }
}
