package com.personal.project.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Loans extends BaseEntity{

    @Id
    @Column(name="loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="loan_number")
    private String loanNumber;

    @Column(name="loan_type")
    private String loanType;

    @Column(name="total_loan")
    private int totalLoan;

    @Column(name="amount_paid")
    private int amountPaid;

    @Column(name="outstanding_amount")
    private int outstandingAmount;

}
