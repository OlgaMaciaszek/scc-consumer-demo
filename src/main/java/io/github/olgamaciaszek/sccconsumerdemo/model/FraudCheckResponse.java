package io.github.olgamaciaszek.sccconsumerdemo.model;

/**
 * @author Olga Maciaszek-Sharma
 */
public class FraudCheckResponse {

	private final FraudCheckStatus fraudCheckStatus;
	private final String rejectionReason;

	public FraudCheckResponse(FraudCheckStatus fraudCheckStatus, String rejectionReason) {
		this.fraudCheckStatus = fraudCheckStatus;
		this.rejectionReason = rejectionReason;
	}

	public FraudCheckStatus getFraudCheckStatus() {
		return fraudCheckStatus;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}
}
