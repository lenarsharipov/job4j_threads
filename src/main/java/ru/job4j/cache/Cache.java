package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    /**
     * Добавление в карту модели по id, если id отсутствует.
     * @param model - модель данных типа Base.
     * @return true если операция выполнена успешно, иначе false;
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * Перед обновлением данных проверяется поле version.
     * Если version у модели и в кеше одинаковы, то можно обновить.
     * Если нет, то выбросить OptimisticException.
     * Перед обновлением данных необходимо проверять текущую версию
     * и ту что обновляем и увеличивать на единицу каждый раз,
     * когда произошло обновление. Если версии не равны - кидать исключение.
     *
     * @param model -
     * @return true, если модель обновилась, иначе false;
     */
    public boolean update(Base model) {
        return memory.computeIfPresent(
                model.getId(), (key, value) -> {
                    int id = model.getId();
                    int version = model.getVersion();
                    Base stored = memory.get(id);
                    if (stored.getVersion() != version) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    Base updated = new Base(id, version + 1);
                    updated.setName(model.getName());
                    return updated;
        }) != null;
    }

    /**
     * Удаление модели по id.
     * @param model - объект типа Base
     */
    public void delete(Base model) {
        memory.remove(model.getId());
    }
}
