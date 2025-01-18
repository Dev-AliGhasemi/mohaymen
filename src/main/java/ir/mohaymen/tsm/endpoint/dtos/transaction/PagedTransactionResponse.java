package ir.mohaymen.tsm.endpoint.dtos.transaction;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.Transaction;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagedTransactionResponse {
    private List<Transaction> content;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;

    public PagedTransactionResponse(Page<Transaction> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }
}
