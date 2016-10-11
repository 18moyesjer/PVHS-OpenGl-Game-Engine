package jeremy;

import jeremy.lib.Window;

/**
 * Created by jeremymoyes on 9/9/16.
 */
public class Game {

    public static void main(String[] args) {
        Window.window_init();

        while(!Window.window_should_close()) {
            Window.update_window();
            Window.draw_window();
        }
    }
}
