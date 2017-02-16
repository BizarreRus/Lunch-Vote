package net.bizare.lunchvoteapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.DELETE_ALL, query = "DELETE FROM Restaurant"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT DISTINCT r FROM Restaurant r order by r.visitDate DESC"),
        @NamedQuery(name = Restaurant.GET_ALL_BY_DATE, query = "SELECT DISTINCT r FROM Restaurant r WHERE r.visitDate=:date"),
        @NamedQuery(name = Restaurant.GET, query = "SELECT r FROM Restaurant r WHERE r.id=:id"),
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    public static final String DELETE = "Restaurant.delete";
    public static final String DELETE_ALL = "Restaurant.deleteAll";
    public static final String GET_ALL = "Restaurant.getAll";
    public static final String GET_ALL_BY_DATE = "Restaurant.getAllOfToday";
    public static final String GET = "Restaurant.get";

    @Column(name = "num_of_votes",nullable = false, columnDefinition = "int default 0")
    private Integer numOfVotes = 0;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate visitDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId(), restaurant.getName());
        this.numOfVotes = restaurant.getNumOfVotes();
        this.visitDate = restaurant.getVisitDate();
        this.menus = restaurant.getMenus();
    }

    public Restaurant(Integer id, String name, Integer numOfVotes, LocalDate date) {
        super(id, name);
        this.numOfVotes = numOfVotes;
        this.visitDate = date;
    }

    public Restaurant(Integer id, String name, Integer numOfVotes, LocalDate date, Set<Menu> menus) {
        super(id, name);
        this.numOfVotes = numOfVotes;
        this.visitDate = date;
        this.menus = menus;
    }

    public Integer getNumOfVotes() {
        return numOfVotes;
    }

    public void setNumOfVotes(Integer numOfVotes) {
        this.numOfVotes = numOfVotes;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "numOfVotes=" + numOfVotes +
                ", visitDate=" + visitDate +
                "}";
    }
}
