import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Actor {
    private String firstName;
    private String lastName;

    List<Movie> movies = new ArrayList<>();

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addMovie(Movie movie){
        if(!movies.contains(movie)) movies.add(movie);
        if(!movie.getCast().contains(this)) movie.addActor(this);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(firstName, actor.firstName) && Objects.equals(lastName, actor.lastName) && Objects.equals(movies, actor.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
