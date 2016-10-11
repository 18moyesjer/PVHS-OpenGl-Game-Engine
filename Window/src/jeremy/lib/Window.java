package jeremy.lib;

import jeremy.lib.input.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * A simple window class that sets up and handles a window
 */

public class Window {

    private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);

    public static long window;

    public static int window_res_x = 640;
    public static int window_res_y = 480;

    public static String window_title = "LWJGL 3 Window";

    public static int window_quit_key = GLFW_KEY_ESCAPE;

    private static double last_time = 0;

    public static float window_color_red = 0.0f;
    public static float window_color_green = 0.0f;
    public static float window_color_blue = 0.0f;
    public static float window_color_alpha = 0.0f;

    public static int window_gl_major = 3;
    public static int window_gl_minor = 3;

    public static void window_init() {

        glfwSetErrorCallback(errorCallback);

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, window_gl_major);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, window_gl_minor);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        window = glfwCreateWindow(window_res_x, window_res_y, window_title, NULL, NULL);

        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glClearColor(window_color_red, window_color_green, window_color_blue, window_color_alpha);

        glfwSwapInterval(1);

        Input.init_input();

        last_time = glfwGetTime();
    }

    public static Boolean window_should_close() {
        return glfwWindowShouldClose(window);
    }

    public static void draw_window() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public static double update_window() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        double tmp = last_time;

        last_time = glfwGetTime();

        if (Keyboard.getKey(window_quit_key) == GLFW_PRESS) glfwSetWindowShouldClose(window, true);

        return last_time - tmp;
    }
}
