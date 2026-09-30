/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class BestFitMemorySlotManager extends FreeMemorySlotManager{
    
    public BestFitMemorySlotManager(int memSize){
        super(memSize);
    }
    
    @Override
    public MemorySlot getSlot(int size) {
        if (size <= 0) {
        return null;
    }

    MemorySlot selected = null;

    // Buscar el hueco más pequeño donde quepa la solicitud.
    for (MemorySlot slot : list) {
        if (slot.canContain(size)) {
            if (selected == null
                    || slot.getSize() < selected.getSize()) {
                selected = slot;
            }
        }
    }

    // Ningún hueco tiene suficiente espacio.
    if (selected == null) {
        return null;
    }

    // Ajuste exacto: el hueco deja de estar libre.
    if (selected.getSize() == size) {
        list.remove(selected);
        return selected;
    }

    // Ajuste parcial: entregar lo solicitado y conservar el sobrante.
    return selected.assignMemory(size);
}

}
    


