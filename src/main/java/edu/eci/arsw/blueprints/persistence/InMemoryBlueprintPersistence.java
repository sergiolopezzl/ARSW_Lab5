/*
 * Clase que implementa la persistencia de blueprints en memoria.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    // Almacena los blueprints en memoria utilizando una estructura ConcurrentHashMap
    private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

    /**
     * Constructor que carga datos de prueba.
     */
    public InMemoryBlueprintPersistence() {
        // Cargar datos de prueba
        Point[] pts = new Point[]{new Point(120, 120), new Point(135, 135)};
        Blueprint bp = new Blueprint("Sergio", "Sergio Blueprint", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        Point[] pts1 = new Point[]{new Point(64, 114), new Point(125, 85), new Point(20, 66)};
        Blueprint bp1 = new Blueprint("Sergio", "Blueprint", pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        Point[] pts2 = new Point[]{new Point(30, 20), new Point(25, 55), new Point(15, 100), new Point(5, 82)};
        Blueprint bp2 = new Blueprint("Vivas", "Vivas Blueprint", pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
        Point[] pts3 = new Point[]{new Point(50, 30)};
        Blueprint bp3 = new Blueprint("Vivas", "Blueprint", pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        // Guarda un blueprint en la estructura ConcurrentHashMap
        if (blueprints.putIfAbsent(new Tuple<>(bp.getAuthor(), bp.getName()), bp) != null) {
            throw new BlueprintPersistenceException(BlueprintPersistenceException.EXISTING_BLUEPRINT + bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        // Obtiene un blueprint específico por autor y nombre
        Blueprint blueprint = blueprints.get(new Tuple<>(author, bprintname));
        if (blueprint == null) throw new BlueprintNotFoundException(BlueprintNotFoundException.BLUEPRINT_NOT_FOUND);
        return blueprint;
    }

    @Override
    public Set<Blueprint> getBlueprintByAuthor(String author) throws BlueprintNotFoundException {
        // Obtiene todos los blueprints de un autor específico
        Set<Blueprint> bluePrintAuthor = new HashSet<>();
        blueprints.forEach((key, value) -> {
            if (key.getElem1().equals(author)) {
                bluePrintAuthor.add(value);
            }
        });
        if (bluePrintAuthor.isEmpty()) {
            throw new BlueprintNotFoundException(BlueprintNotFoundException.AUTHOR_NOT_FOUND);
        }
        return bluePrintAuthor;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        // Obtiene todos los blueprints almacenados en la estructura ConcurrentHashMap
        return new HashSet<>(blueprints.values());
    }

    @Override
    public void updateBlueprint(String author, String bpname, Blueprint bpUpdate) throws BlueprintNotFoundException {
        // Actualiza un blueprint existente en la estructura ConcurrentHashMap
        Blueprint bpActual = getBlueprint(author, bpname);
        bpActual.setPoints(bpUpdate.getPoints());
    }
}
