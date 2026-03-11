package career.lld.practice.bookmyshow.my;

import java.util.*;

class Theatre {

    private String theatreId;
    private String name;
    private List<Screen> screens = new ArrayList<>();

    public Theatre(String theatreId, String name) {
        this.theatreId = theatreId;
        this.name = name;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public List<Screen> getScreens() {
        return screens;
    }
}