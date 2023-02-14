package ru.job4j.cache;

/**
 * Класс Base - базовая модель данных. Описывается двумя полями: id, version.
 */
public class Base {
    /**
     * ID - уникальный идентификатор. В системе будет только один объект с таким ID.
     */
    private final int id;

    /**
     * Поле version - определяет достоверность версии в кеше.
     * Это поле увеличивается на единицу каждый раз, когда модель изменили,
     * то есть вызвали метод update() класса Cache.
     * Даже если все поля остались не измененными, поле version должно увеличиться на 1.
     */
    private final int version;

    /**
     * Поле name - это поля бизнес модели. В нашем примере это одно поле name.
     * Это поле имеет get set методы.
     */
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
