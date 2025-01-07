package med.voll.api.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String Documento,
        String Email ) {

    public DatosListadoMedico(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(),medico.getDocumento(), medico.getEmail());
    }
}
