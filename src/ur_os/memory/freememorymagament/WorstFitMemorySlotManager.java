/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class WorstFitMemorySlotManager extends FreeMemorySlotManager{
    
    public WorstFitMemorySlotManager(int memSize){
        super(memSize);
    }
    
    @Override
    public MemorySlot getSlot(int size) {
        // Una solicitud debe tener un tamaño positivo.
    if (size <= 0) {
        return null;
    }

    // Guarda el mayor hueco suficiente encontrado hasta el momento.
    MemorySlot selected = null;

    // Revisar todos los huecos antes de asignar memoria.
    for (MemorySlot slot : list) {

        // Considerar únicamente huecos donde quepa la solicitud.
        if (slot.canContain(size)) {

            // Elegir el primer candidato o reemplazarlo por uno mayor.
            // En caso de empate, conservar el primero encontrado.
            if (selected == null
                    || slot.getSize() > selected.getSize()) {
                selected = slot;
            }
        }
    }

    // Si no existe un hueco suficiente, la asignación falla.
    // La lista de huecos libres permanece sin cambios.
    if (selected == null) {
        return null;
    }

    // Si la solicitud ocupa todo el hueco, retirarlo de la lista
    // y entregar ese bloque completo al proceso.
    if (selected.getSize() == size) {
        list.remove(selected);
        return selected;
    }

    // Si sobra espacio, assignMemory crea el bloque solicitado
    // desde la base del hueco y actualiza la base y el tamaño
    // del sobrante, que permanece en la lista de memoria libre.
    return selected.assignMemory(size);
    }
    
}
