package com.agenda.contactos.agendacontactos.Services;

import com.agenda.contactos.agendacontactos.Entities.Contacto;
import java.util.List;

public interface ContactoService {
    List<Contacto> ListarContactos(String palabraClave);
    Contacto registrarContacto(Contacto contacto);
    Contacto buscarContactoPorId(Integer id);
    Contacto actualizarContacto(Contacto contacto);
    void EliminarContacto(Integer id);
}
