package net.bizare.lunchvoteapp.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v order by v.voteDate"),
        @NamedQuery(name = Vote.GET_COUNT, query = "SELECT COUNT(v.id) FROM  Vote v WHERE v.restaurant=:restaurantId AND v.voteDate=:voteDate"),
        @NamedQuery(name = Vote.GET_VOTE_OF_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDate=:voteDate"),
})
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {
    public static final String DELETE = "Vote.delete";
    public static final String GET_ALL = "Vote.getAll";
    public static final String GET_COUNT = "Vote.getCount";
    public static final String GET_VOTE_OF_USER = "Vote.getVoteOfUser";

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "vote_date")
    private LocalDate voteDate;

    public Vote() {
    }

    public Vote(Restaurant restaurant, User user, LocalDate voteDate) {
        this.restaurant = restaurant;
        this.user = user;
        this.voteDate = voteDate;
    }

    public Vote(Integer id, Restaurant restaurant, User user, LocalDate voteDate) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.voteDate = voteDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "restaurant=" + restaurant +
                ", user=" + user +
                ", voteDate=" + voteDate +
                "} " + super.toString();
    }

}
