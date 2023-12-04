package com.b2e.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account Name can't be blank")
    @Size(min = 2, max=50)
    private String accountName;

    @NotBlank(message = "Account Number can't be blank")
    @Size(min = 10, max = 15)
    private String accountNumber;

    @NotBlank(message = "Description can't be blank")
    @Size(max = 100)
    private String description;

    @Min(1)
    @Max(3)
    private Integer priority;    // Max =3, Medium =2, Min =1;

    private Double currentBalance;

    @PrePersist
    public void setCurrentBalance() {
        this.currentBalance = 0.0;
    }
}
