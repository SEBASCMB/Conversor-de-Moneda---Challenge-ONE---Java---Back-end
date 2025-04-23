package util;

import java.util.HashMap;
import java.util.Map;

public class ConversionActions {
    public static Map<Integer, Runnable> build(final String[] fromTo) {
        Map<Integer, Runnable> acciones = new HashMap<>();
        acciones.put(1, () -> { fromTo[0] = "USD"; fromTo[1] = "COP"; });
        acciones.put(2, () -> { fromTo[0] = "COP"; fromTo[1] = "USD"; });
        acciones.put(3, () -> { fromTo[0] = "USD"; fromTo[1] = "BRL"; });
        acciones.put(4, () -> { fromTo[0] = "BRL"; fromTo[1] = "USD"; });
        acciones.put(5, () -> { fromTo[0] = "USD"; fromTo[1] = "EUR"; });
        acciones.put(6, () -> { fromTo[0] = "EUR"; fromTo[1] = "USD"; });
        return acciones;
    }
}
