package fractals.fpnumbers;

@FunctionalInterface
public interface RunnableWithException<T extends Exception> {
    void run() throws T;
}
