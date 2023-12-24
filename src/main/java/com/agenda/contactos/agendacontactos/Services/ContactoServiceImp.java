package com.agenda.contactos.agendacontactos.Services;

import com.agenda.contactos.agendacontactos.Controllers.ContactoController;
import com.agenda.contactos.agendacontactos.Entities.Contacto;
import com.agenda.contactos.agendacontactos.Repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactoServiceImp implements ContactoService{
    @Autowired
    private ContactoRepository contactoRepository;
    @Override
    public List<Contacto> ListarContactos() {
        return contactoRepository.findAll();
    }

    @Override
    public Contacto registrarContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public Contacto buscarContactoPorId(Integer id) {
        return contactoRepository.findById(id).get();
    }

    @Override
    public Contacto actualizarContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public void EliminarContacto(Integer id) {
        contactoRepository.deleteById(id);
    }
}
