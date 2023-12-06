package com.b2e.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @NotNull(message ="Amount can't be blank")
    private  Double amount;

//    @Size(min = 3, max = 40)
//    @NotBlank(message ="Description can't be blank")
    private String description;

//    @NotNull(message ="Transaction Type can't be blank")
    @Min(1) @Max(4)
    private int transactionType;    // 1. Income,  2. Expenses   3. GivingLoan  4. Taking Loan

//    @NotNull(message="Date can't be blank")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", nullable = false)
    @JsonIgnore
    private Wallet wallet;
    @PrePersist
    public void setTransactionDate() {
        this.transactionDate = new Date();
    }

}
