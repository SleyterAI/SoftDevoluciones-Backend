package CV.SoftDevoluciones.Return.Enum;

public enum ReturnStatus {
    SOLICITADO,
    EN_REVISION,
    APROBADO,
    COMPLETADO,
    RECHAZADO;

    public boolean validateWorkflow(ReturnStatus nuevoEstado) {
        return switch (this) {
            case SOLICITADO -> nuevoEstado == EN_REVISION;
            case EN_REVISION -> nuevoEstado == APROBADO;
            case APROBADO -> nuevoEstado == COMPLETADO;
            case COMPLETADO -> nuevoEstado == RECHAZADO;
            case RECHAZADO -> false;
        };
    }
}
