package com.luxoft.bankapp.domain;

import java.text.DateFormat;
import java.util.*;

import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.EmailException;
import com.luxoft.bankapp.utils.ClientRegistrationListener;

public class Bank {

	private final Set<Client> clients = new HashSet<>();
	private final List<ClientRegistrationListener> listeners = new ArrayList<ClientRegistrationListener>();

	private EmailService emailService;
	private int printedClients = 0;
	private int emailedClients = 0;
	private int debuggedClients = 0;
	
	public Bank() {
		listeners.add(new PrintClientListener());
		listeners.add(new EmailNotificationListener());
		listeners.add(new DebugListener());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bank bank = (Bank) o;
		return printedClients == bank.printedClients && emailedClients == bank.emailedClients && debuggedClients == bank.debuggedClients && Objects.equals(clients, bank.clients) && Objects.equals(listeners, bank.listeners);
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clients, listeners, printedClients, emailedClients, debuggedClients);
	}

	public int getPrintedClients() {
		return printedClients;
	}

	public int getEmailedClients() {
		return emailedClients;
	}

	public int getDebuggedClients() {
		return debuggedClients;
	}
	
	public void addClient(final Client client) throws ClientExistsException {
		boolean clientAdded;

		clientAdded = clients.add(client);
		if (!clientAdded) {
			throw new ClientExistsException("Client already exists into the bank");
		} else {
			notify(client);
		}
	}
	
	private void notify(Client client) {
        for (ClientRegistrationListener listener: listeners) {
            listener.onClientAdded(client);
        }
    }
	
	public Set<Client> getClients() {
		return Collections.unmodifiableSet(clients);
	}
	
	class PrintClientListener implements ClientRegistrationListener {
		@Override 
		public void onClientAdded(Client client) {
	        System.out.println("Client added: " + client.getName());
	        printedClients++;
	    }

	}
	
	class EmailNotificationListener implements ClientRegistrationListener {
		@Override
		public void onClientAdded(Client client) {
			System.out.println("Notification email for client " + client.getName() + " to be sent");
			emailedClients++;
			if (emailService != null) {
				try {
					emailService.sendNotificationEmail(
							new Email()
									.setFrom("Admin")
									.setTo("Manager")
									.setTitle("Client Added Notification")
									.setBody("Client added: " + client)
					);
				} catch (EmailException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}
	
	class DebugListener implements ClientRegistrationListener {
        @Override 
        public void onClientAdded(Client client) {
            System.out.println("Client " + client.getName() + " added on: " + DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
            debuggedClients++;
        }
    }

}




