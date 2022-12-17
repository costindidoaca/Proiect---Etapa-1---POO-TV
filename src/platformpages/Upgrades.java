package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class Upgrades extends PlatformPage {
    private static Upgrades platformPage = null;

    private Upgrades() {
        setPageName("upgrades");
    }

    /** singleton javadoc */
    public static Upgrades getPlatformPage() {
        if (platformPage == null) {
            platformPage = new Upgrades();
        }
        return platformPage;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromUpgrades(this, action);
    }
}
