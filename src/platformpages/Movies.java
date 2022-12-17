package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class Movies extends PlatformPage {
    private static Movies page = null;

    private Movies() {
        setPageName("movies");
    }

    /** singleton javadoc */
    public static Movies getPlatformPage() {
        if (page == null) {
            page = new Movies();
        }
        return page;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromMovies(this, action);
    }
}
