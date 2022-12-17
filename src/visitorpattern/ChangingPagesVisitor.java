package visitorpattern;

import currentinfo.Present;
import iofiles.Action;
import iofiles.Movie;
import platformpages.Login;
import platformpages.PlatformPage;
import platformpages.Register;
import platformpages.Unauthenticated;
import platformpages.Home;
import platformpages.Movies;
import platformpages.SeeDetails;
import platformpages.Upgrades;

import java.util.ArrayList;

public final class ChangingPagesVisitor implements Visitor {

    /** method that does the change page action from any
     * current page to a given destination via visitor*/
    public boolean tryToChangePage(final PlatformPage currentPage, final Action action) {
        return currentPage.tryToDoActionFromThisPage(this, action);
    }

    /** method that does the change page action from unauthenticated page to a given destination */
    @Override
    public boolean doActionFromUnauthenticated(final Unauthenticated page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Register.getPlatformPage().getPageName());
        validPagesToVisit.add(Login.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "register" -> Present.getInfo().setCurrentPage(Register.getPlatformPage());
                case "login" -> Present.getInfo().setCurrentPage(Login.getPlatformPage());
                case "logout" -> {
                    Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
                    Present.getInfo().setCurrentUser(null);
                    Present.getInfo().setCurrentMovieList(new ArrayList<>());
                }
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does the change page action from login page to a given destination */
    @Override
    public boolean doActionFromLogin(final Login page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Login.getPlatformPage().getPageName());
        validPagesToVisit.add(Register.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "login" -> Present.getInfo().setCurrentPage(Login.getPlatformPage());
                case "register" -> Present.getInfo().setCurrentPage(Register.getPlatformPage());
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does the change page action from register page to a given destination */
    @Override
    public boolean doActionFromRegister(final Register page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Register.getPlatformPage().getPageName());
        validPagesToVisit.add(Login.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "register" -> Present.getInfo().setCurrentPage(Register.getPlatformPage());
                case "login" -> Present.getInfo().setCurrentPage(Login.getPlatformPage());
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does the change page action from home page to a given destination */
    @Override
    public boolean doActionFromHome(final Home page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Home.getPlatformPage().getPageName());
        validPagesToVisit.add(Movies.getPlatformPage().getPageName());
        validPagesToVisit.add(Upgrades.getPlatformPage().getPageName());
        validPagesToVisit.add(Unauthenticated.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "home" -> Present.getInfo().setCurrentPage(Home.getPlatformPage());
                case "movies" -> {
                    Present.getInfo().setCurrentPage(Movies.getPlatformPage());
                    Present.getInfo().getCurrentMovieList().clear();
                    for (Movie movie : Present.getInfo().getMoviesDatabase()) {
                        if (!movie.getCountriesBanned().contains(Present.getInfo()
                                .getCurrentUser().getCredentials().getCountry())) {
                            Present.getInfo().getCurrentMovieList().add(movie);
                        }
                    }
                }
                case "upgrades" -> Present.getInfo().setCurrentPage(Upgrades.getPlatformPage());
                case "logout" -> {
                    Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
                    Present.getInfo().setCurrentUser(null);
                    Present.getInfo().setCurrentMovieList(new ArrayList<>());
                }
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does the change page action from movies page to a given destination */
    @Override
    public boolean doActionFromMovies(final Movies page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Home.getPlatformPage().getPageName());
        validPagesToVisit.add(Movies.getPlatformPage().getPageName());
        validPagesToVisit.add(SeeDetails.getPlatformPage().getPageName());
        validPagesToVisit.add(Upgrades.getPlatformPage().getPageName());
        validPagesToVisit.add(Unauthenticated.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "home" -> Present.getInfo().setCurrentPage(Home.getPlatformPage());
                case "movies" -> {
                    Present.getInfo().getCurrentMovieList().clear();
                    for (Movie movie : Present.getInfo().getMoviesDatabase()) {
                        if (!movie.getCountriesBanned().contains(Present.getInfo()
                                .getCurrentUser().getCredentials().getCountry())) {
                            Present.getInfo().getCurrentMovieList().add(movie);
                        }
                    }
                    Present.getInfo().setCurrentPage(Movies.getPlatformPage());
                }
                case "see details" -> {
                    for (Movie movie : Present.getInfo().getCurrentMovieList()) {
                        if (movie.getName().equals(action.movie())) {
                            Present.getInfo().getCurrentMovieList().clear();
                            Present.getInfo().getCurrentMovieList().add(movie);
                            Present.getInfo().setCurrentPage(SeeDetails.getPlatformPage());
                            return true;
                        }
                    }
                    return false;
                }
                case "upgrades" -> Present.getInfo().setCurrentPage(Upgrades.getPlatformPage());
                case "logout" -> {
                    Present.getInfo().setCurrentUser(null);
                    Present.getInfo().setCurrentMovieList(new ArrayList<>());
                    Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
                }
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does the change page action from see details page to a given destination */
    @Override
    public boolean doActionFromSeeDetails(final SeeDetails page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Home.getPlatformPage().getPageName());
        validPagesToVisit.add(Upgrades.getPlatformPage().getPageName());
        validPagesToVisit.add(SeeDetails.getPlatformPage().getPageName());
        validPagesToVisit.add(Movies.getPlatformPage().getPageName());
        validPagesToVisit.add(Unauthenticated.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "home" -> Present.getInfo().setCurrentPage(Home.getPlatformPage());
                case "upgrades" -> Present.getInfo().setCurrentPage(Upgrades.getPlatformPage());
                case "see details" -> {
                    for (Movie movie : Present.getInfo().getCurrentMovieList()) {
                        if (movie.getName().equals(action.movie())) {
                            Present.getInfo().getCurrentMovieList().clear();
                            Present.getInfo().getCurrentMovieList().add(movie);
                            Present.getInfo().setCurrentPage(SeeDetails.getPlatformPage());
                            return true;
                        }
                    }
                    return false;
                }
                case "movies" -> {
                    Present.getInfo().getCurrentMovieList().clear();
                    for (Movie movie : Present.getInfo().getMoviesDatabase()) {
                        if (!movie.getCountriesBanned().contains(Present.getInfo()
                                .getCurrentUser().getCredentials().getCountry())) {
                            Present.getInfo().getCurrentMovieList().add(movie);
                        }
                    }
                    Present.getInfo().setCurrentPage(Movies.getPlatformPage());
                }
                case "logout" -> {
                    Present.getInfo().setCurrentUser(null);
                    Present.getInfo().setCurrentMovieList(new ArrayList<>());
                    Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
                }
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }

    /** method that does the change page action from upgrades page to a given destination */
    @Override
    public boolean doActionFromUpgrades(final Upgrades page, final Action action) {
        ArrayList<String> validPagesToVisit = new ArrayList<>();
        validPagesToVisit.add(Home.getPlatformPage().getPageName());
        validPagesToVisit.add(Unauthenticated.getPlatformPage().getPageName());
        validPagesToVisit.add(Movies.getPlatformPage().getPageName());
        validPagesToVisit.add(Upgrades.getPlatformPage().getPageName());

        if (validPagesToVisit.contains(action.page())) {
            switch (action.page()) {
                case "home" -> Present.getInfo().setCurrentPage(Home.getPlatformPage());
                case "logout" -> {
                    Present.getInfo().setCurrentPage(Unauthenticated.getPlatformPage());
                    Present.getInfo().setCurrentUser(null);
                    Present.getInfo().setCurrentMovieList(new ArrayList<>());
                }
                case "movies" -> {
                    Present.getInfo().setCurrentPage(Movies.getPlatformPage());
                    Present.getInfo().getCurrentMovieList().clear();
                    for (Movie movie : Present.getInfo().getMoviesDatabase()) {
                        if (!movie.getCountriesBanned().contains(Present.getInfo()
                                .getCurrentUser().getCredentials().getCountry())) {
                            Present.getInfo().getCurrentMovieList().add(movie);
                        }
                    }
                }
                case "upgrades" -> Present.getInfo().setCurrentPage(Upgrades.getPlatformPage());
                default -> { }
            }
            return true;
        } else {
            return false;
        }
    }
}
