package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class SeeDetails extends PlatformPage {
    private static SeeDetails platformPage = null;

    private SeeDetails() {
        setPageName("see details");
    }

    /** singleton javadoc */
    public static SeeDetails getPlatformPage() {
        if (platformPage == null) {
            platformPage = new SeeDetails();
        }
        return platformPage;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromSeeDetails(this, action);
    }
}
