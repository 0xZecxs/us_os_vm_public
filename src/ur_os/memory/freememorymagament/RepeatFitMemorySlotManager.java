package ur_os.memory.freememorymagament;

public class RepeatFitMemorySlotManager
        extends FreeMemorySlotManager {

    public RepeatFitMemorySlotManager(int memSize) {
        super(memSize);
    }

    @Override
    public MemorySlot getSlot(int size) {

        // Rechazar solicitudes de tamaño no positivo.
        if (size <= 0) {
            return null;
        }

        // Menor hueco suficiente: alternativa si no se puede repetir.
        MemorySlot bestFit = null;

        // Menor hueco que permite otra solicitud del mismo tamaño.
        MemorySlot repeatFit = null;

        // Revisar todos los huecos antes de asignar memoria.
        for (MemorySlot slot : list) {

            // Ignorar los huecos donde no cabe la solicitud.
            if (!slot.canContain(size)) {
                continue;
            }

            // Conservar el menor hueco suficiente.
            if (bestFit == null
                    || slot.getSize() < bestFit.getSize()) {
                bestFit = slot;
            }

            // Comprobar si el sobrante permite repetir la solicitud.
            int remainder = slot.getRemainder(size);

            if (remainder >= size) {

                // Entre los que permiten repetir, escoger el menor.
                // En empates, conservar el primero encontrado.
                if (repeatFit == null
                        || slot.getSize() < repeatFit.getSize()) {
                    repeatFit = slot;
                }
            }
        }

        // Ningún hueco puede atender la solicitud.
        if (bestFit == null) {
            return null;
        }

        // Priorizar el ajuste exacto porque no deja sobrante.
        if (bestFit.getSize() == size) {
            list.remove(bestFit);
            return bestFit;
        }

        MemorySlot selected;

        if (repeatFit != null) {
            // Utilizar el menor hueco que permite repetir.
            selected = repeatFit;
        } else {
            // Si ninguno permite repetir, aplicar BestFit.
            selected = bestFit;
        }

        // Entregar los bytes solicitados y conservar el sobrante.
        return selected.assignMemory(size);
    }
}