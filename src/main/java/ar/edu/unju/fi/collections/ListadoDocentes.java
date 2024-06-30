package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.fi.model.Docente;

public class ListadoDocentes {
    public static List<Docente> docentes = new ArrayList<Docente>();
    public static List<Docente> docentesDisponibles = new ArrayList<Docente>();

    public static List<Docente> listarDocentes() {
        return docentesDisponibles;
    }

    public static Docente buscarDocentePorLegajo(String legajo) {
        for (Docente docente : docentesDisponibles) {
            if (docente.getLegajo().equals(legajo)) {
                return docente;
            }
        }
        return null;
    }

    public static void modificarDocente(Docente docenteModificado) {
        for (int i = 0; i < docentes.size(); i++) {
            Docente docente = docentes.get(i);
            if (docente.getLegajo().equals(docenteModificado.getLegajo())) {
                docentes.set(i, docenteModificado);
                actualizarDocentesDisponibles();
                break;
            }
        }
    }

    public static void eliminarDocente(String legajo) {
        for (Docente docente : docentes) {
            if (docente.getLegajo().equals(legajo)) {
                docente.setEstado(false);
                break;
            }
        }
        actualizarDocentesDisponibles();
    }

    public static void agregarDocente(Docente docente) {
        docente.setEstado(true);
        docentes.add(docente);
        docentesDisponibles.add(docente);
    }

    private static void actualizarDocentesDisponibles() {
        docentesDisponibles.clear();
        for (Docente docente : docentes) {
            if (docente.isEstado()) {
                docentesDisponibles.add(docente);
            }
        }
    }
}
