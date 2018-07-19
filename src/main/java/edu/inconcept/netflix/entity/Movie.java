package edu.inconcept.netflix.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "const")
    private String constant;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "created",nullable = false)
    private Date createdDate;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "runtime")
    private Integer runtime;
    @Column(name = "number_votes")
    private Long numberVotes;
    @Column(name = "release_date")
    private java.sql.Date releaseDate;
    @Column(name = "url",nullable = false,unique = true)
    private String url;
    @ManyToOne
    @JoinColumn(name = "director_id",referencedColumnName = "id")
    private Director director;
    @ManyToOne
    @JoinColumn(name = "title_type",referencedColumnName = "id")
    private TitleType titleType;
    @ManyToMany
    @JoinTable(
            name = "movies_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Long getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(Long numberVotes) {
        this.numberVotes = numberVotes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public java.sql.Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.sql.Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
