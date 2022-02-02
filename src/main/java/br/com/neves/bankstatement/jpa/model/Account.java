package br.com.neves.bankstatement.jpa.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("account")
public class Account {
// ------------------------------ FIELDS ------------------------------

    @Id
    private ObjectId _id;
    @Field("agency")
    private String agency;
    @Field("number")
    private String number;
    @Field("balance")
    private BigDecimal balance;
}
