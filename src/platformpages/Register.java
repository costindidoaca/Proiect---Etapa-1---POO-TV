package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;

public final class Register extends PlatformPage {
    private static Register platformPage = null;

    private Register() {
        setPageName("register");
    }

    /** singleton javadoc */
    public static Register getPlatformPage() {
        if (platformPage == null) {
            platformPage = new Register();
        }
        return platformPage;
    }

    @Override
    public boolean tryToDoActionFromThisPage(final Visitor visitor, final Action action) {
        return visitor.doActionFromRegister(this, action);
    }
}
