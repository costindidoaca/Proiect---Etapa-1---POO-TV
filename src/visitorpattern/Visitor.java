package visitorpattern;

import iofiles.Action;
import platformpages.Login;
import platformpages.Register;
import platformpages.Unauthenticated;
import platformpages.Home;
import platformpages.Movies;
import platformpages.SeeDetails;
import platformpages.Upgrades;

public interface Visitor {

    /** this method does a specific action (change page/on page) from the unauthenticated page */
    boolean doActionFromUnauthenticated(Unauthenticated page, Action action);

    /** this method does a specific action (change page/on page) from the login page */
    boolean doActionFromLogin(Login page, Action action);

    /** this method does a specific action (change page/on page) from the register page */
    boolean doActionFromRegister(Register page, Action action);

    /** this method does a specific action (change page/on page) from the home page */
    boolean doActionFromHome(Home page, Action action);

    /** this method does a specific action (change page/on page) from the movies page */
    boolean doActionFromMovies(Movies page, Action action);

    /** this method does a specific action (change page/on page) from the see details page */
    boolean doActionFromSeeDetails(SeeDetails page, Action action);

    /** this method does a specific action (change page/on page) from the upgrades page */
    boolean doActionFromUpgrades(Upgrades page, Action action);
}
