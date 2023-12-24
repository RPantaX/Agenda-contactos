package com.agenda.contactos.agendacontactos.Repositories;

import com.agenda.contactos.agendacontactos.Entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}
