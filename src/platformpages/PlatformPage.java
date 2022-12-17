package platformpages;

import iofiles.Action;
import visitorpattern.Visitor;


public abstract class PlatformPage {

    private String pageName;

    public PlatformPage() {
        this.pageName = "logout";
    }

    /** getter for page name */
    public String getPageName() {
        return pageName;
    }

    /** setter for page name */
    public void setPageName(final String pageName) {
        this.pageName = pageName;
    }

    /** method that does action from specific page via visitor */
    public abstract boolean tryToDoActionFromThisPage(Visitor visitor, Action action);
}
