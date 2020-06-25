package io.github.olgamaciaszek.sccconsumerdemo;

import java.net.URI;

import io.github.olgamaciaszek.sccconsumerdemo.model.FraudCheckRequest;
import io.github.olgamaciaszek.sccconsumerdemo.model.FraudCheckResponse;
import io.github.olgamaciaszek.sccconsumerdemo.model.FraudCheckStatus;
import io.github.olgamaciaszek.sccconsumerdemo.model.LoanApplication;
import io.github.olgamaciaszek.sccconsumerdemo.model.LoanApplicationResult;
import io.github.olgamaciaszek.sccconsumerdemo.model.LoanApplicationStatus;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Olga Maciaszek-Sharma
 */
@Service
class LoanApplicationService {

	private static final String FRAUD_SERVICE_JSON_VERSION_1 = "application/vnd.fraud.v1+json;charset=UTF-8";

	private final WebClient webClient;

	LoanApplicationService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	Mono<LoanApplicationResult> loanApplication(LoanApplication loanApplication) {
		FraudCheckRequest request = new FraudCheckRequest(loanApplication);
		Mono<FraudCheckResponse> response = sendRequestToFraudDetectionService(request);
		return buildResponseFromFraudResult(response);
	}

	private Mono<FraudCheckResponse> sendRequestToFraudDetectionService(FraudCheckRequest request) {
		return webClient.post()
				.uri(URI.create("http://localhost:8080/fraudcheck"))
				.bodyValue(request)
				.header(HttpHeaders.CONTENT_TYPE, FRAUD_SERVICE_JSON_VERSION_1)
				.retrieve()
				.bodyToMono(FraudCheckResponse.class);
	}

	private Mono<LoanApplicationResult> buildResponseFromFraudResult(Mono<FraudCheckResponse> response) {
		return response.map(checkResponse -> {
			if (FraudCheckStatus.FRAUD == checkResponse.getFraudCheckStatus()) {
				return new LoanApplicationResult(LoanApplicationStatus.APPLICATION_REJECTED, checkResponse
						.getRejectionReason());

			}

			return new LoanApplicationResult(LoanApplicationStatus.APPLIED, checkResponse
					.getRejectionReason());
		});
	}
}