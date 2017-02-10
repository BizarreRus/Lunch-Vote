package net.bizare.lunchvoteapp.model;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurant_id"),
        @NamedQuery(name = Menu.DELETE_ALL, query = "DELETE FROM Menu m WHERE m.restaurant.id=:restaurant_id"),
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m FROM Menu m LEFT JOIN FETCH m.restaurant r where r.id=:restaurant_id order by m.name"),
        @NamedQuery(name = Menu.GET, query = "SELECT m FROM Menu m LEFT JOIN FETCH m.restaurant r WHERE m.id=:id AND r.id=:restaurant_id"),
})
@Entity
@Table(name = "menus")
public class Menu extends NamedEntity {
    public static final String DELETE = "Menu.delete";
    public static final String DELETE_ALL = "Menu.deleteAll";
    public static final String GET_ALL = "Menu.getAll";
    public static final String GET = "Menu.get";

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.EAGER)
    private Set<Dish> dishes;

    public Menu() {
    }

    public Menu(Menu menu) {
        super(menu.getId(), menu.getName());
        this.restaurant = menu.getRestaurant();
    }

    public Menu(Integer id, String name) {
        super(id, name);
    }

    public Menu(Integer id, String name, Restaurant restaurant) {
        super(id, name);
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
