package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class Home extends PlatformPage {
    private static Home platformPage = null;

    private Home() {
        setPageName("home");
    }

    /** singleton javadoc */
    public static Home getPlatformPage() {
        if (platformPage == null) {
            platformPage = new Home();
        }
        return platformPage;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromHome(this, action);
    }
}
