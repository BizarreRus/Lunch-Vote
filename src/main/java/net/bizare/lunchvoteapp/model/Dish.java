package net.bizare.lunchvoteapp.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menu_id"),
        @NamedQuery(name = Dish.DELETE_ALL, query = "DELETE FROM Dish d WHERE d.menu.id=:menu_id"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d LEFT JOIN FETCH d.menu m where m.id=:menu_id order by d.name"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d LEFT JOIN FETCH d.menu m WHERE d.id=:id AND m.id=:menu_id"),
})
@Entity
@Table(name = "dishes")
public class Dish extends NamedEntity {
    public static final String DELETE = "Dish.delete";
    public static final String DELETE_ALL = "Dish.deleteAll";
    public static final String GET_ALL = "Dish.getAll";
    public static final String GET = "Dish.get";

    @Column(name = "price")
    @Digits(fraction = 2, integer = 3)
    @NotNull
    @Range(min = 10, max = 2000)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Dish() {
    }

    public Dish(Dish dish) {
        super(dish.getId(), dish.getName());
        this.price = dish.getPrice();
        this.menu = dish.getMenu();
    }

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Double price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
