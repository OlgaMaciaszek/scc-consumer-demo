package io.github.olgamaciaszek.sccconsumerdemo;

import java.math.BigDecimal;
import java.util.UUID;

import io.github.olgamaciaszek.sccconsumerdemo.model.Client;
import io.github.olgamaciaszek.sccconsumerdemo.model.LoanApplication;
import io.github.olgamaciaszek.sccconsumerdemo.model.LoanApplicationResult;
import io.github.olgamaciaszek.sccconsumerdemo.model.LoanApplicationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith({SpringExtension.class})
@AutoConfigureStubRunner(ids = {"io.github.olgamaciaszek:scc-producer-demo:+:stubs:8080"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class SccConsumerDemoApplicationTests {

	@Autowired
	LoanApplicationService loanApplicationService;

	@Test
	void shouldSuccessfullyApplyForLoan() {
		LoanApplication application = new LoanApplication(new Client(UUID.randomUUID()),
				new BigDecimal("123.123"), UUID.randomUUID());

		Mono<LoanApplicationResult> loanApplication = loanApplicationService
				.loanApplication(application);

		assertThat(loanApplication.block().getLoanApplicationStatus())
				.isEqualTo(LoanApplicationStatus.APPLIED);
		assertThat(loanApplication.block().getRejectionReason()).isNull();
	}

	@Test
	void shouldBeRejectedDueToAbnormalLoanAmount() {
		LoanApplication application = new LoanApplication(new Client(UUID.randomUUID()),
				new BigDecimal("99999"), UUID.randomUUID());

		LoanApplicationResult loanApplication = loanApplicationService
				.loanApplication(application).block();

		assertThat(loanApplication.getLoanApplicationStatus())
				.isEqualTo(LoanApplicationStatus.APPLICATION_REJECTED);
		assertThat(loanApplication.getRejectionReason()).isEqualTo("Amount too high");
	}
}
