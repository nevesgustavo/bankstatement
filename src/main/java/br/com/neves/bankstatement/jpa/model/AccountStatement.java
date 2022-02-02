package br.com.neves.bankstatement.jpa.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("accountStatement")
public class AccountStatement {
// ------------------------------ FIELDS ------------------------------

    @Id
    private ObjectId _id;

    @Field("transactionId")
    private Long transactionId;

    @Field("amount")
    private BigDecimal amount;

    @Field("origin")
    private String origin;

    @Field("type")
    private String type;

    @Field("account")
    private Account account;

    @Field("createdAt")
    private LocalDateTime createdAt;
}
