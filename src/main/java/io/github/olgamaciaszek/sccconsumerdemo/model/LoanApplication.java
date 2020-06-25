package io.github.olgamaciaszek.sccconsumerdemo.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Olga Maciaszek-Sharma
 */
public class LoanApplication {

	private final Client client;
	private final BigDecimal amount;
	private final UUID loanApplicationId;

	public LoanApplication(Client client, BigDecimal amount, UUID loanApplicationId) {
		this.client = client;
		this.amount = amount;
		this.loanApplicationId = loanApplicationId;

	}

	UUID getClientId() {
		return client.getClientId();
	}

	BigDecimal getAmount() {
		return amount;
	}
}
