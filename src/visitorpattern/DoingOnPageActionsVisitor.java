package visitorpattern;

import currentinfo.Present;
import iofiles.Action;
import iofiles.Movie;
import iofiles.User;
import platformpages.Login;
import platformpages.PlatformPage;
import platformpages.Register;
import platformpages.Unauthenticated;
import platformpages.Home;
import platformpages.Movies;
import platformpages.SeeDetails;
import platformpages.Upgrades;

import java.util.ArrayList;
import java.util.Comparator;

import static currentinfo.Constants.MAX_RATING;
import static currentinfo.Constants.PREMIUM_ACCOUNT_PRICE;

public final class DoingOnPageActionsVisitor implements Visitor {

    /** method that does a specific given on page action from a specific current page*/
    public boolean tryToDoOnPageAction(final PlatformPage currentPage, final Action action) {
        return currentPage.tryToDoActionFromThisPage(this, action);
    }

    /** method that does a specific given on page action from unauthenticated page */
    @Override
    public boolean doActionFromUnauthenticated(final Unauthenticated page, final Action action) {
        return false;
    }

    /** method that does a specific given on page action from login page */
    @Override
    public boolean doActionFromLogin(final Login page, final Action action) {
        if (action.feature().equals("login")) {
            for (User user : Present.getInfo().getUsersDatabase()) {
                String username = user.getCredentials().getName();
                String userPass = user.getCredentials().getPassword();
                if (username.equals(action.credentials().getName())
                        && userPass.equals(action.credentials().getPassword())) {
                    Present.getInfo().setCurrentUser(user);
                    Present.getInfo().setCurrentPage(Home.getPlatformPage());
                    return true;
                }
            }
            Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
        }
        return false;
    }

    /** method that does a specific given on page action from register page */
    @Override
    public boolean doActionFromRegister(final Register page, final Action action) {
        if (action.feature().equals("register")) {
            for (User user : Present.getInfo().getUsersDatabase()) {
                String username = user.getCredentials().getName();
                if (username.equals(action.credentials().getName())) {
                    Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
                    return false;
                }
            }
            User registeredUser = new User(action.credentials());
            Present.getInfo().getUsersDatabase().add(registeredUser);
            Present.getInfo().setCurrentUser(registeredUser);
            Present.getInfo().setCurrentPage(Home.getPlatformPage());
            return true;
        } else {
            return false;
        }
    }

    /** method that does a specific given on page action from home page */
    @Override
    public boolean doActionFromHome(final Home page, final Action action) {
        return false;
    }

    /** method that does a specific given on page action from movies page */
    @Override
    public boolean doActionFromMovies(final Movies page, final Action action) {
        if (action.feature().equals("search") || action.feature().equals("filter")) {
            User currentUser = Present.getInfo().getCurrentUser();
            ArrayList<Movie> currentMovieList = Present.getInfo().getCurrentMovieList();
            switch (action.feature()) {
                case "search" -> {
                    currentMovieList.clear();
                    for (Movie movie : Present.getInfo().getMoviesDatabase()) {
                        String userCountry = currentUser.getCredentials().getCountry();
                        if (!movie.getCountriesBanned().contains(userCountry)
                                && movie.getName().startsWith(action.startsWith())) {
                            currentMovieList.add(movie);
                        }
                    }
                }
                case "filter" -> {
                    currentMovieList.clear();
                    for (Movie movie : Present.getInfo().getMoviesDatabase()) {
                        String userCountry = currentUser.getCredentials().getCountry();
                        if (!movie.getCountriesBanned().contains(userCountry)) {
                            currentMovieList.add(movie);
                        }
                    }
                    if (action.filters().sort() != null) {
                        if (action.filters().sort().rating() != null) {
                            Comparator<Movie> incSort = Comparator.comparing(Movie::getRating);
                            Comparator<Movie> decSort =
                                    Comparator.comparing(Movie::getRating).reversed();
                            if (action.filters().sort().rating().equals("increasing")) {
                                currentMovieList.sort(incSort);
                            } else {
                                currentMovieList.sort(decSort);
                            }
                        }
                        if (action.filters().sort().duration() != null) {
                            Comparator<Movie> incSort = Comparator.comparing(Movie::getDuration);
                            Comparator<Movie> decSort =
                                    Comparator.comparing(Movie::getDuration).reversed();
                            if (action.filters().sort().duration().equals("increasing")) {
                                currentMovieList.sort(incSort);
                            } else {
                                currentMovieList.sort(decSort);
                            }
                        }
                    }
                    if (action.filters().contains() != null) {
                        if (action.filters().contains().genre() != null) {
                            currentMovieList.removeIf(movie -> !movie.getGenres()
                                    .containsAll(action.filters().contains().genre()));
                        }
                        if (action.filters().contains().actors() != null) {
                            currentMovieList.removeIf(movie -> !movie.getActors()
                                    .containsAll(action.filters().contains().actors()));
                        }
                    }
                }
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does a specific given on page action from see details page */
    @Override
    public boolean doActionFromSeeDetails(final SeeDetails page, final Action action) {
        if (action.feature().equals("purchase") || action.feature().equals("watch")
                || action.feature().equals("like") || action.feature().equals("rate")) {
            User currentUser = Present.getInfo().getCurrentUser();
            Movie currentMovie = Present.getInfo().getCurrentMovieList().get(0);
            switch (action.feature()) {
                case "purchase" -> {
                    if (!currentUser.getPurchasedMovies().contains(currentMovie)) {
                        switch (currentUser.getCredentials().getAccountType()) {
                            case "premium" -> {
                                if (currentUser.getNumFreePremiumMovies() != 0) {
                                    currentUser.payMovieWithFreePremiumMovies();
                                    currentUser.getPurchasedMovies().add(currentMovie);
                                    return true;
                                } else {
                                    if (currentUser.getTokensCount() > 1) {
                                        currentUser.payMovieWithTokens();
                                        currentUser.getPurchasedMovies().add(currentMovie);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            }
                            case "standard" -> {
                                if (currentUser.getTokensCount() > 1) {
                                    currentUser.payMovieWithTokens();
                                    currentUser.getPurchasedMovies().add(currentMovie);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                            default -> { }
                        }
                    }
                    return false;
                }
                case "watch" -> {
                    for (Movie pMovie : currentUser.getPurchasedMovies()) {
                        if (pMovie.getName().equals(currentMovie.getName())) {
                            for (Movie wMovie : currentUser.getWatchedMovies()) {
                                if (wMovie.getName().equals(currentMovie.getName())) {
                                    return false;
                                }
                            }
                            currentUser.getWatchedMovies().add(currentMovie);
                            return true;
                        }
                    }
                    return false;
                }
                case "like" -> {
                    for (Movie wMovie : currentUser.getWatchedMovies()) {
                        if (wMovie.getName().equals(currentMovie.getName())) {
                            for (Movie lMovie : currentUser.getLikedMovies()) {
                                if (lMovie.getName().equals(currentMovie.getName())) {
                                    return false;
                                }
                            }
                            currentMovie.like();
                            currentUser.getLikedMovies().add(currentMovie);
                            return true;
                        }
                    }
                    return false;
                }
                case "rate" -> {
                    if (action.rate() > MAX_RATING) {
                        return false;
                    }
                    for (Movie wMovie : currentUser.getWatchedMovies()) {
                        if (wMovie.getName().equals(currentMovie.getName())) {
                            for (Movie rMovie : currentUser.getRatedMovies()) {
                                if (rMovie.getName().equals(currentMovie.getName())) {
                                    return false;
                                }
                            }
                            currentMovie.rate(action.rate());
                            currentUser.getRatedMovies().add(currentMovie);
                            return true;
                        }
                    }
                    return false;
                }
                default -> { }
            }
        }
        return false;
    }

    /** method that does a specific given on page action from upgrades page */
    @Override
    public boolean doActionFromUpgrades(final Upgrades page, final Action action) {
        if (action.feature().equals("buy tokens")
                || action.feature().equals("buy premium account")) {
            User currentUser = Present.getInfo().getCurrentUser();
            switch (action.feature()) {
                case "buy tokens" -> {
                    if (Integer.parseInt(action.count()) <= currentUser.getIntBalance()) {
                        currentUser.payWithBalance(action.count());
                        currentUser.addTokens(action.count());
                        return true;
                    } else {
                        return false;
                    }
                }
                case "buy premium account" -> {
                    switch (currentUser.getCredentials().getAccountType()) {
                        case "standard" -> {
                            if (currentUser.getTokensCount() >= PREMIUM_ACCOUNT_PRICE) {
                                currentUser.buyPremiumAccount();
                                return true;
                            } else {
                                return false;
                            }
                        }
                        case "premium" -> {
                            return false;
                        }
                        default -> { }
                    }
                }
                default -> { }
            }
            return false;
        }
        return false;
    }
}
