package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.collections.ListadoCarreras;
import ar.edu.unju.fi.collections.ListadoDocentes;
import ar.edu.unju.fi.collections.ListadoMaterias;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {

    @Autowired
    Materia nuevaMateria; // Remove initialization here

    @GetMapping("/formularioMaterias")
    public ModelAndView getFormularioMaterias() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateria);
        modelView.addObject("listadoDocente", ListadoDocentes.listarDocentes());
        modelView.addObject("listadoCarrera", ListadoCarreras.listarCarreras());
        return modelView;
    }

    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaAGuardar) {
        materiaAGuardar.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaAGuardar.getDocente().getLegajo()));
        materiaAGuardar.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaAGuardar.getCarrera().getCodigo()));
        ListadoMaterias.agregarMateria(materiaAGuardar);

        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/borrarMateria/{codigo}")
    public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") String codigo) {
        ListadoMaterias.eliminarMateria(codigo);

        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView editMateria(@PathVariable(name="codigo") String codigo) {
        Materia materiaAModificar = ListadoMaterias.buscarMateriaPorCodigo(codigo);

        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materiaAModificar);
        modelView.addObject("listadoDocente", ListadoDocentes.listarDocentes()); // Corrected name to "listadoDocente"
        modelView.addObject("listadoCarrera", ListadoCarreras.listarCarreras());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView updateMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
        materiaModificada.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaModificada.getDocente().getLegajo()));
        materiaModificada.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaModificada.getCarrera().getCodigo()));
        ListadoMaterias.modificarMateria(materiaModificada);

        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/materias")
    public ModelAndView showMaterias() {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMateria", ListadoMaterias.listarMaterias());
        return modelView;
    }
}

