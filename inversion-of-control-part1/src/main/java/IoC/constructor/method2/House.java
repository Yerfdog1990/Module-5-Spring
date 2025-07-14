package IoC.constructor.method2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public final class House {
    private final String model;
    private final String color;

    public House(String model, String color) {
        this.model = model;
        this.color = color;
    }

    @Autowired
    public House(HouseInfo info) {
        this.model = info.model();
        this.color = info.color();
    }

    public String model() {
        return model;
    }

    public String color() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (House) obj;
        return Objects.equals(this.model, that.model) &&
                Objects.equals(this.color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, color);
    }

    @Override
    public String toString() {
        return "House[" +
                "model=" + model + ", " +
                "color=" + color + ']';
    }

}
