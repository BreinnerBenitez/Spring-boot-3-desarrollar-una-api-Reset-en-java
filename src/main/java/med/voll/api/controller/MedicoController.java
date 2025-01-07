package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicorepositorio;
    @PostMapping
    public void resgitarMedico( @RequestBody @Valid DatosResgistroMedico datosResgistroMedico){
        medicorepositorio.save(new Medico(datosResgistroMedico ));

    }
    @GetMapping
    public Page<DatosListadoMedico> listadoMedico(@PageableDefault(size = 2) Pageable paginacion){
//    return medicorepositorio.findAll(paginacion).map(DatosListadoMedico::new); // todos los datos ordenado
        return medicorepositorio.findByActivoTrue(paginacion).map(DatosListadoMedico::new);

    }
    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){

        Medico medico = medicorepositorio.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
 // DELETE LOGIICO
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable  Long id){

        Medico medico = medicorepositorio.getReferenceById(id);
        medico.desactivarMedico();

    }
    //ELIMINA POR COMPLETO DE LA BASE DE DATOS-base de datos
//     public void eliminarMedico(@PathVariable  Long id){
//
//        Medico medico = medicorepositorio.getReferenceById(id);
//        medicorepositorio.delete(medico);
//
//     }
}
