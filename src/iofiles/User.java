package iofiles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import static currentinfo.Constants.NUM_FREE_PREMIUM_MOVIES;
import static currentinfo.Constants.MOVIE_PRICE;
import static currentinfo.Constants.PREMIUM_ACCOUNT_PRICE;


public final class User {
    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    public User() {
    }

    public User(final User user) {
        this.credentials = new Credentials();
        this.credentials.setName(user.getCredentials().getName());
        this.credentials.setPassword(user.getCredentials().getPassword());
        this.credentials.setCountry(user.getCredentials().getCountry());
        this.credentials.setAccountType(user.getCredentials().getAccountType());
        this.credentials.setBalance(user.getCredentials().getBalance());
        this.credentials.setAccountType(user.getCredentials().getAccountType());
        this.credentials.setBalance(user.getCredentials().getBalance());
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        this.purchasedMovies = new ArrayList<>();
        for (Movie movie : user.purchasedMovies) {
            Movie deepCopyMovie = new Movie(movie);
            this.purchasedMovies.add(deepCopyMovie);
        }
        this.watchedMovies = new ArrayList<>();
        for (Movie movie : user.watchedMovies) {
            Movie deepCopyMovie = new Movie(movie);
            this.watchedMovies.add(deepCopyMovie);
        }
        this.likedMovies = new ArrayList<>();
        for (Movie movie : user.likedMovies) {
            Movie deepCopyMovie = new Movie(movie);
            this.likedMovies.add(deepCopyMovie);
        }
        this.ratedMovies = new ArrayList<>();
        for (Movie movie : user.ratedMovies) {
            Movie deepCopyMovie = new Movie(movie);
            this.ratedMovies.add(deepCopyMovie);
        }
    }

    public User(final Credentials credentials) {
        this.credentials = new Credentials();
        this.credentials.setName(credentials.getName());
        this.credentials.setPassword(credentials.getPassword());
        this.credentials.setCountry(credentials.getCountry());
        this.credentials.setAccountType(credentials.getAccountType());
        this.credentials.setBalance(credentials.getBalance());
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    /** this method modify the number of free premium movies after we pay a movie with them */
    public void payMovieWithFreePremiumMovies() {
        this.numFreePremiumMovies--;
    }

    /** this method modify the number of tokens after we pay a movie with them */
    public void payMovieWithTokens() {
        this.tokensCount -= MOVIE_PRICE;
    }

    /** this method modify the number of tokens after we buy them with balance */
    public void addTokens(final String amount) {
        int intAmount = Integer.parseInt(amount);
        this.tokensCount += intAmount;
    }

    @JsonIgnore
    public int getIntBalance() {
        return Integer.parseInt(this.credentials.getBalance());
    }

    /** this method modify the balance after we pay tokens with it */
    public void payWithBalance(final String amount) {
        int intAmount = Integer.parseInt(amount);
        int intResult = Integer.parseInt(this.credentials.getBalance()) - intAmount;
        this.credentials.setBalance(Integer.toString(intResult));
    }

    /** this method modify the number of tokens after we buy a premium account with it*/
    public void buyPremiumAccount() {
        this.tokensCount -= PREMIUM_ACCOUNT_PRICE;
        this.credentials.setAccountType("premium");
    }
}
