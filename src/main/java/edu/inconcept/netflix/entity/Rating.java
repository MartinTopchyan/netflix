package edu.inconcept.netflix.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "blanaid_rated")
    private Integer blanaidRated;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getBlanaidRated() {
        return blanaidRated;
    }

    public void setBlanaidRated(Integer blanaidRated) {
        this.blanaidRated = blanaidRated;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "createdDate=" + createdDate +
                ", blanaidRated=" + blanaidRated +
                ", account=" + account +
                ", movie=" + movie +
                '}';
    }
}

