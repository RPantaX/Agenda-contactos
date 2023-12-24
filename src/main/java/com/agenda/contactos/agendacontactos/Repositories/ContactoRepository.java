package com.agenda.contactos.agendacontactos.Repositories;

import com.agenda.contactos.agendacontactos.Entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    @Query("SELECT c FROM Contacto c WHERE"
            + " CONCAT(c.id,c.nombre, c.email, c.celular)"
            + " LIKE %?1%")
    public List<Contacto> findAll(String palabraClave);
}
