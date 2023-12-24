package com.agenda.contactos.agendacontactos.Controllers;

import com.agenda.contactos.agendacontactos.Entities.Contacto;
import com.agenda.contactos.agendacontactos.Services.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ContactoController {
    @Autowired
    private ContactoService contactoService;
    @GetMapping({"/",""})
    public String verPaginaInicio(Model model, @Param("palabraClave") String palabraClave){
        model.addAttribute("contactos", contactoService.ListarContactos(palabraClave));
        model.addAttribute("palabraClave",palabraClave);
        return "index";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrarContacto(Model model){
        model.addAttribute("contacto", new Contacto());
        return "nuevo";
    }
    @PostMapping("/nuevo")
    public String RegistrarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        //validated valida el objeto contacto, y bindingresult te da los errores de ese objeto/ poner el bindingresult en ese orden al costado del objeto, sino saldrá error.
        if(bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto); //le pasamos un nuevo formulario, no le pasamos una nueva instancia (no confundirse)
            return "nuevo";
        }

        contactoService.registrarContacto(contacto);
        redirectAttributes.addFlashAttribute("msgExitoso","El contacto ha sido regitrado correctamente");
        return "redirect:/";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarContacto(@PathVariable Integer id, Model model){
        model.addAttribute("contacto", contactoService.buscarContactoPorId(id));
        return("/nuevo");
    }
    @PostMapping("/editar/{id}")
    public String actualizarContacto(@PathVariable Integer id, @ModelAttribute("contacto") @Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        //validated valida el objeto contacto, y bindingresult te da los errores de ese objeto/ poner el bindingresult en ese orden al costado del objeto, sino saldrá error.
        Contacto contactoActual = contactoService.buscarContactoPorId(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto); //le pasamos un nuevo formulario, no le pasamos una nueva instancia (no confundirse)
            return "nuevo";
        }
        contactoActual.setNombre(contacto.getNombre());
        contactoActual.setEmail(contacto.getEmail());
        contactoActual.setCelular(contacto.getCelular());
        contactoActual.setFechaNacimiento(contacto.getFechaNacimiento());
        contactoService.actualizarContacto(contacto);
        redirectAttributes.addFlashAttribute("msgExitoso","El contacto ha sido actualizado correctamente");
        return "redirect:/";
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes ){
        redirectAttributes.addFlashAttribute("msgExitoso","El contacto ha sido eliminado correctamente");
        contactoService.EliminarContacto(id);
        return "redirect:/";
    }
}
