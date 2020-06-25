package io.github.olgamaciaszek.sccconsumerdemo.model;

/**
 * @author Olga Maciaszek-Sharma
 */
public class LoanApplicationResult {

	private final LoanApplicationStatus loanApplicationStatus;
	private final String rejectionReason;

	public LoanApplicationResult(LoanApplicationStatus loanApplicationStatus, String rejectionReason) {
		this.loanApplicationStatus = loanApplicationStatus;
		this.rejectionReason = rejectionReason;
	}

	public LoanApplicationStatus getLoanApplicationStatus() {
		return loanApplicationStatus;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}
}
