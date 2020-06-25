package io.github.olgamaciaszek.sccconsumerdemo.model;

import java.util.UUID;

/**
 * @author Olga Maciaszek-Sharma
 */
public class Client {

	private final UUID clientId;

	public Client(UUID clientId) {
		this.clientId = clientId;
	}

	public UUID getClientId() {
		return clientId;
	}
}
