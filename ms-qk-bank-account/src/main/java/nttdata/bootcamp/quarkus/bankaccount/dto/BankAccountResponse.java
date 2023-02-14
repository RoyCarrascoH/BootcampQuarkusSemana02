package nttdata.bootcamp.quarkus.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.bankaccount.entity.BankAccount;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountResponse extends ResponseBase {

    private List<BankAccount> bankAccounts;

}
