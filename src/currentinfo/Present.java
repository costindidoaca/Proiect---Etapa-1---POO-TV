package currentinfo;

import iofiles.Movie;
import iofiles.User;
import platformpages.PlatformPage;
import platformpages.Unauthenticated;

import java.util.ArrayList;

public final class Present {
    private static Present info = null;
    private PlatformPage currentPage;
    private User currentUser;
    private ArrayList<Movie> currentMovieList;
    private ArrayList<Movie> moviesDatabase;
    private ArrayList<User> usersDatabase;


    private Present() {
        currentPage = Unauthenticated.getPlatformPage();
        currentUser = null;
        currentMovieList = new ArrayList<>();
        moviesDatabase = new ArrayList<>();
        usersDatabase = new ArrayList<>();
    }

    /** singleton javadoc */
    public static Present getInfo() {
        if (info == null) {
            info = new Present();
        }
        return info;
    }

    public PlatformPage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final PlatformPage currentPage) {
        this.currentPage = currentPage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public ArrayList<Movie> getMoviesDatabase() {
        return moviesDatabase;
    }

    public void setMoviesDatabase(final ArrayList<Movie> moviesDatabase) {
        this.moviesDatabase = moviesDatabase;
    }

    public ArrayList<User> getUsersDatabase() {
        return usersDatabase;
    }

    public void setUsersDatabase(final ArrayList<User> usersDatabase) {
        this.usersDatabase = usersDatabase;
    }
}
