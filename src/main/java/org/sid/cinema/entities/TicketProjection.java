package org.sid.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketsProj",types=Ticket.class)
public interface TicketProjection {

	public Long getId();
	public String getNomClient();
	public double getPrix();
	public Integer getCodePaimenet();
	public boolean getReservee();
	public Place getPlace();
}
