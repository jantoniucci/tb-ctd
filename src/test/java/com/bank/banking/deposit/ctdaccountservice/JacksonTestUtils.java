package com.bank.banking.deposit.ctdaccountservice;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.Module;

/**
 * Helper to expose custom Jackson modules for unit tests.
 */
public class JacksonTestUtils extends JacksonCustomizations {

	public static Set<Module> getModules(Map<Class<?>, Class<?>> mixins) {
		return Set.of(new MoneyModule());
	}
}