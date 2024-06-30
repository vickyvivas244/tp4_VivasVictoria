package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.collections.ListadoAlumnos;
import ar.edu.unju.fi.model.Alumno;

@Controller
public class AlumnoController {

    @Autowired
    Alumno nuevoAlumno = new Alumno();

    @GetMapping("/formularioAlumnos")
    public ModelAndView getFormularioAlumnos() {
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", nuevoAlumno);
        return modelView;
    }

    @PostMapping("/guardarAlumno")
    public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoAGuardar) {
        ListadoAlumnos.agregarAlumno(alumnoAGuardar);
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumno", ListadoAlumnos.listarAlumnos());
        return modelView;
    }

    @GetMapping("/borrarAlumno/{dni}")
    public ModelAndView deleteAlumnoDelListado(@PathVariable(name="dni") String dni) {
        ListadoAlumnos.eliminarAlumno(dni);
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumno", ListadoAlumnos.listarAlumnos());
        return modelView;
    }

    @GetMapping("/modificarAlumno/{dni}")
    public ModelAndView editAlumno(@PathVariable(name="dni") String dni) {
        Alumno alumnoAModificar = ListadoAlumnos.buscarAlumnoPorDni(dni);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", alumnoAModificar);
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarAlumno")
    public ModelAndView updateAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoModificado) {
        ListadoAlumnos.modificarAlumno(alumnoModificado);
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumno", ListadoAlumnos.listarAlumnos());
        return modelView;
    }

    @GetMapping("/alumnos")
    public ModelAndView showAlumnos() {
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumno", ListadoAlumnos.listarAlumnos());
        return modelView;
    }
}
