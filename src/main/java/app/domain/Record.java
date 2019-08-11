package app.domain;

import java.io.Serializable;

/**
 * Сущность результирующей записи в json
 */
public class Record implements Serializable {

    String name;
    String country;
    String code;

    public Record(String name, String country, String code) {
        this.name = name;
        this.country = country;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}