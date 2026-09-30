package ur_os.memory.freememorymagament;

public class MemoryFitTest {

    public static void main(String[] args) {

        RepeatFitMemorySlotManager manager =
                new RepeatFitMemorySlotManager(1000);

        System.out.println("=== PRUEBA REPEATFIT ===");

        // Caso 1: escoger el menor hueco que permite repetir.
        manager.list.clear();
        manager.list.add(new MemorySlot(0, 110));
        manager.list.add(new MemorySlot(200, 250));
        manager.list.add(new MemorySlot(500, 400));

        System.out.println("Solicitud de 100:");
        System.out.println(manager.getSlot(100));

        System.out.println("Huecos restantes:");
        System.out.println(manager);

        // Caso 2: priorizar el ajuste exacto.
        manager.list.clear();
        manager.list.add(new MemorySlot(0, 100));
        manager.list.add(new MemorySlot(200, 250));

        System.out.println("Ajuste exacto de 100:");
        System.out.println(manager.getSlot(100));

        System.out.println("Huecos restantes:");
        System.out.println(manager);

        // Caso 3: aplicar BestFit si ninguno permite repetir.
        manager.list.clear();
        manager.list.add(new MemorySlot(0, 110));
        manager.list.add(new MemorySlot(200, 150));

        System.out.println("Solicitud de 100 sin espacio para repetir:");
        System.out.println(manager.getSlot(100));

        System.out.println("Huecos restantes:");
        System.out.println(manager);

        // Caso 4: devolver null cuando ningún hueco alcanza.
        System.out.println("Solicitud de 200:");
        System.out.println(manager.getSlot(200));
    }
}