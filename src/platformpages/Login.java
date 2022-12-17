package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class Login extends PlatformPage {
    private static Login platformPage = null;

    private Login() {
        setPageName("login");
    }

    /** singleton javadoc */
    public static Login getPlatformPage() {
        if (platformPage == null) {
            platformPage = new Login();
        }
        return platformPage;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromLogin(this, action);
    }
}
