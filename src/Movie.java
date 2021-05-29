import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Movie {
    private String title;
    private Director director;
    List<Actor> cast = new ArrayList<>();
    private LocalDate firstScreening;
    private int duration;
    private int budget;

    public Movie(String title, Director director, LocalDate firstScreening, int duration, int budget) {
        this.title = title;
        setDirector(director);
        this.firstScreening = firstScreening;
        this.duration = duration;
        this.budget = budget;
    }

    public Movie(String title, Director director, List<Actor> cast, LocalDate firstScreening, int duration, int budget) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.firstScreening = firstScreening;
        this.duration = duration;
        this.budget = budget;
    }

    public void setDirector(Director director) {
        this.director = director;
        if(!director.getMovies().contains(this))director.addMovie(this);
    }

    public void addActor(Actor actor){
        if(!cast.contains(actor)) cast.add(actor);
        if(!actor.getMovies().contains(this))actor.addMovie(this);
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public LocalDate getFirstScreening() {
        return firstScreening;
    }

    public void setFirstScreening(LocalDate firstScreening) {
        this.firstScreening = firstScreening;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return title + " from Director " + director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return budget == movie.budget && Objects.equals(title, movie.title) && Objects.equals(director, movie.director) && Objects.equals(cast, movie.cast) && Objects.equals(firstScreening, movie.firstScreening) && Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director, cast, firstScreening, duration, budget);
    }
}
