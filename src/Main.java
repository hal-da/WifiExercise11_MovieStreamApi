import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {


        Actor evilHector = new Actor("Hector", "Evil");
        Actor orlandoBloom = new Actor("Orlando", "Bloom");
        Actor alf = new Actor("al", "f");
        Actor alBundy = new Actor("Al", "Bundy");
        Actor arni = new Actor("Arnold", "Schwarzenegger");
        Actor luke = new Actor("Mark", "Hamil");
        Actor han = new Actor("Han", "Solo");
        Actor frodo = new Actor("Elijah", "Wood");
        Actor drHeisenberg = new Actor("Dr", "Heisenberg");
        Actor waltherWhite = new Actor("Walter", "White");
        Actor ryanGosling = new Actor("Ryan", "Gosling");
        Actor gandalf = new Actor("Gan", "Dalf");

        Director ryanBreaker = new Director("Ryan", "Breaker");
        Director georgeLucas = new Director("George", "Lucas");


        Movie breakingRyan = new Movie("Breaking Ryan", ryanBreaker, LocalDate.of(2021,5,10), 120,30000000);
            breakingRyan.addActor(ryanGosling);
            breakingRyan.addActor(evilHector);
            breakingRyan.addActor(alf);

        Movie starWars = new Movie("Star Wars", georgeLucas, LocalDate.of(1971, 2,23), 180, 1000000);
            starWars.addActor(han);
            starWars.addActor(luke);
            starWars.addActor(orlandoBloom);
            starWars.addActor(alBundy);

        Movie lordOfTheRings = new Movie("Lord of the Rings", georgeLucas, LocalDate.of(2015,5,10), 500, 465464132);
            lordOfTheRings.addActor(arni);
            lordOfTheRings.addActor(frodo);
            lordOfTheRings.addActor(drHeisenberg);
            lordOfTheRings.addActor(waltherWhite);
            lordOfTheRings.addActor(gandalf);

        Movie theQuasiology = new Movie("Die Leiden der alten Quasiologen", ryanBreaker, LocalDate.of(1913, 9, 21), 10000, 1);
        theQuasiology.addActor(alf);
        theQuasiology.addActor(alBundy);
        theQuasiology.addActor(frodo);

        List<Movie> allOurMovies = new ArrayList<>(
                List.of(breakingRyan, starWars, lordOfTheRings)
        );

        allOurMovies.add(theQuasiology);

        Movie troll = new Movie("Troll", ryanBreaker,List.of(ryanGosling, waltherWhite, alf, frodo), LocalDate.of(1999,3,3),150, 1506);
        //Versuche mit List.of/Set.of/Map.of den Cast zu erstellen und diesen
        //nachträglich zu ändern. Warum geht das nicht?
        //troll.addActor(gandalf);

        //weil: Exception in thread "main" java.lang.UnsupportedOperationException
        //	at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:71)


        allOurMovies.add(troll);

        // map,filter, reduce, sorting, allMatch

        // Überprüfe ob das Budget aller Filme kleiner als 2.000.000 ist.
        boolean areAllUnder2MioBudget = allOurMovies.stream()
                .allMatch(m -> m.getBudget() < 2000000);
        System.out.println("are all under 2 mio budget?: " + areAllUnder2MioBudget);


        //Gib die Actors aus dem Film mit dem größten Budget aus
        List<Actor> listOfActors = allOurMovies.stream()
                    .max(Comparator.comparing(Movie::getBudget))
                    .orElse(troll)
                    .getCast();

        System.out.println(listOfActors);

        //Erzeuge eine Liste, aller Filme in der ein bestimmter Schauspieler vorkommt

        List<Movie> movieListOfActor = allOurMovies.stream()
                .filter(aMovie -> aMovie.getCast().contains(alf))
                .collect(Collectors.toList());

        System.out.println(movieListOfActor);

        //Gib die ersten 2 Filme aus, die nach 2012 zum ersten Mal im Kino waren

        allOurMovies.stream()
                .filter(movie -> movie.getFirstScreening().isAfter(LocalDate.of(2012,1,1)))
                .limit(2)
                .forEach(System.out::println);


        //! Erzeuge eine Map<Actor, Movie> in der jeweils alle Movies eines Actors zu finden sind

        Map<Actor,List<Movie>> moviesFromActors = allOurMovies
                .stream()
                .map(Movie::getCast)
                .flatMap(List::stream)
                .distinct() // um doppelte keys zu verhindern
                .collect(
                        Collectors.toMap(
                                actor -> actor,
                                Actor::getMovies,
                                (a,b) -> a  //entweder oben schon distinct um keine duplicate keys zu haben, oder so mit diesem lambda ausdruck
                                //oder beides
                        )
                );

        System.out.println("################################");
        moviesFromActors.forEach( (actor,movieList) -> {
            System.out.println(actor+" starring in:");
            System.out.println(movieList+" \n");
        });
        System.out.println("################################");

        //just to learn how to get a List<Actor> from a Stream<List<Actor>>
        List<Actor> allActorsFromAllOurMovies = allOurMovies.stream()
            .map(Movie::getCast)
            .flatMap(List::stream)
            .distinct()
            .peek(System.out::println)
            .collect(Collectors.toList());

/*      Achtung und sorry, die letzte Aufgabe bei Movies ist schwerer als gedacht:Erzeuge eine Map<Actor, Movie> in der jeweils alle Movies eines Actors zu finden sind.
        Einfach stattdessen eine Map<LocalDate, Movie> erzeugen, wo der Key zum Film der Veröffentlichungstermin ist.
   */

        TreeMap<LocalDate, Movie> localDateMovieMap = allOurMovies.stream()
                .collect(Collectors.toMap(
                    Movie::getFirstScreening,
                    movie -> movie,
                    (a,b) -> a,
                    TreeMap::new
                    ));

        System.out.println("\nlocal date movie map");
        localDateMovieMap.forEach((date, movie )-> System.out.println(date +" : " + movie));

        //Bonus: pythagoras
        List<int[]> pythagoreanTriples = IntStream
                .rangeClosed(1,100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a+1,10000)
                        .filter(b -> Math.sqrt(a*a+b*b)%1==0)
                        .sorted()
                        .limit(1)
                        .mapToObj((int b) -> new int[]{a,b, (int) Math.sqrt(a*a+b*b)})

                )
                .collect(Collectors.toList());

        System.out.println("\n Pythagoras Bonus");
        pythagoreanTriples
                .forEach(y -> System.out.println(y[0] + "² + " + y[1] + "² = " + y[2] + "²"));

    }
}