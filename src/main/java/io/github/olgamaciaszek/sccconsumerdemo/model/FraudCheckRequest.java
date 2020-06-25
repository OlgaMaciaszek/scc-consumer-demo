package io.github.olgamaciaszek.sccconsumerdemo.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Olga Maciaszek-Sharma
 */
public class FraudCheckRequest {

	private final UUID clientId;
	private final BigDecimal loanAmount;

	public FraudCheckRequest(LoanApplication loanApplication) {
		this.clientId = loanApplication.getClientId();
		this.loanAmount = loanApplication.getAmount();
	}

	public UUID getClientId() {
		return clientId;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
}
