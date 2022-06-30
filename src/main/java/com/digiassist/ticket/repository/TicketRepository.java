package com.digiassist.ticket.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.digiassist.ticket.domain.Ticket;

//https://docs.spring.io/spring-cloud-gcp/docs/current/reference/html/firestore.html
public interface TicketRepository extends FirestoreReactiveRepository<Ticket> {

}
