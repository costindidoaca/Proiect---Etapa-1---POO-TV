package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class Unauthenticated extends PlatformPage {
    private static Unauthenticated platformPage = null;

    private Unauthenticated() {
        setPageName("logout");
    }

    /** singleton javadoc */
    public static Unauthenticated getPlatformPage() {
        if (platformPage == null) {
            platformPage = new Unauthenticated();
        }
        return platformPage;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromUnauthenticated(this, action);
    }
}
