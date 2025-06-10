package com.bank.banking.deposit.ctdaccountservice;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

/**
 * Verifying modulithic structure and creating documentation for {@link ApplicationModules}.
 */
class ModularityTest {

	ApplicationModules modules = ApplicationModules.of(CtdAccountService.class);

	@Test
	void verifiesArchitecture() {
		modules.verify();
	}

	@Test
	void createDocumentation() {
		new Documenter(modules).writeDocumentation();
	}
}